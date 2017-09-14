if (typeof(Storage) !== undefined) {
    if (!sessionStorage.username) {
        window.location = '/login.html';
    }
} else {
    window.location = '/login.html';
}

var app = angular.module('gradingApp', ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "index.html"
    })
    .when("/login", {
        templateUrl : "login.html"
    });
});

app.controller('tabController', function() {
    
    this.tab = 1;

    this.setTab = function(newTab) {
        this.tab = newTab;
    };

    this.isSet = function(tabNum) {
        return this.tab === tabNum;
    };
});

app.controller('appController', function($scope, $http, $location) {

    $scope.allTasks = [];
    $scope.studentTasks = [];
    $scope.submissions = [];
    $scope.selectedTask = null;
    $scope.selectedStudentTask = null;
    $scope.selectedSubmission = null;
    $scope.studentTaskProperty = 'uniid';
    $scope.studentTaskReverse = false;
    
    $scope.getAllTasks = function() {
        $http({
            method: 'GET',
            url: 'http://codereview.ee:8090/task/get'
        }).then(function(response) {
            $scope.allTasks = response.data;
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.getAllStudentTasks = function() {
        var task = JSON.parse($scope.currentTask);
        $scope.selectedTask = task;
        var taskName = task.name;
        var url = 'http://codereview.ee:8090/task/' + taskName;
        console.log(url);
        
        $http({
            method: 'GET',
            url: url
        }).then(function(response) {
            $scope.studentTasks = response.data;
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.getSubmissions = function(studentTask) {
        $scope.selectedStudentTask = studentTask;
        var id = studentTask.id;
        var url = 'http://codereview.ee:8090/studenttask/' + id;
        console.log(url);
        
        $http({
            method: 'GET',
            url: url
        }).then(function(response) {
            $scope.submissions = response.data;
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.setGrade = function(gr) {
        var url = "http://codereview.ee:8090/grade/update";
        var submission = $scope.selectedSubmission;
        var id = $scope.selectedStudentTask.grade.id;
        var grade = {
            id: id,
            grade: gr
        };
        
        $http({
            method: 'POST',
            url: url,
            data: grade
        }).then(function(response) {
            $scope.getAllStudentTasks();
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.sendMail = function() {
        var uniid = $scope.selectedStudentTask.uniid;
        var reviewId = $scope.selectedStudentTask.review.reviewId;
        var url = "http://codereview.ee:8090/mail/send/${uniid}/" + reviewId;
        
        $http({
            method: 'GET',
            url: url
        }).then(function(response) {
            
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.runPlagiarism = function() {
        if ($scope.selectedTask != null) {
            var id = $scope.selectedTask.id;
            console.log(id);
            var plagiarism = {
                id: id
            };
            var url = "http://codereview.ee:8090/plagiarism/run";

            $http({
                method: 'POST',
                url: url,
                data: plagiarism
            }).then(function(response) {
                $scope.selectedTask.plagiarism = response.data;
            }, function(error) {
                console.log(error);
            });
        }
    }
    
    $scope.updateReview = function() {
        var id = $scope.selectedStudentTask.review.id;
        var review = {
            id: id
        };
        var url = "http://codereview.ee:8090/review/update";
        
        $http({
            method: 'POST',
            url: url,
            data: review
        }).then(function(response) {
            $scope.selectedStudentTask.review = response.data;
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.updateSandBox = function(stdout, stderr) {
        var id = $scope.selectedSubmission.sandBox.id;
        var sandBox = {
            id: id,
            stdout: stdout,
            stderr: stderr
        };
        
        var url = "http://codereview.ee:8090/sandbox/update";
        
        $http({
            method: 'POST',
            url: url,
            data: sandBox
        }).then(function(response) {
            $scope.getSubmissionFromQueue();
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.getSubmissionFromQueue = function() {
        var url = "http://codereview.ee:8090/sandbox/get";
        
        $http({
            method: 'GET',
            url: url
        }).then(function(response) {
            if (response.data.length == 0) {
                setTimeout($scope.getSubmissionFromQueue, 1000);
            } else {
                $scope.embeddablSendFiles(response.data);
            }
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.embeddablSendFiles = function(submission) {
        var name = submission.date;
        var location = submission.sandBox.location;

        $http({
            method: 'POST',
            url: 'http://api.embeddabl.com/save-files',
            headers: {
                "Content-Type": "application/json"
            },
            data: {
                type: "ZIP",
                name: name,
                url: "http://codereview.ee:8090" + location,
                user: "JOOP"
            }
        }).then(function(response) {
            console.log(response);
            if (response.data.state) {
                $scope.embeddablRun(submission);
            } else {
                setTimeout($scope.embeddablSendFiles(submission), 1000);
            }
        }, function(error) {
            setTimeout($scope.embeddablSendFiles(submission), 1000);
        });
    }

    $scope.embeddablRun = function(submission) {
        var path = submission.sandBox.location;
        var package = submission.sandBox.packagePath;
        var classPath = submission.sandBox.classPath;
        
        $http({
            method: 'POST',
            url: "http://api.embeddabl.com/run",
            headers: {
                "Content-Type": "application/json"
            },
            data: {
                "file": {
                    "path": path
                },
                "classpath": classPath,
                "package": package,
                "user": "JOOP"
            }
        }).then(function(response) {
            if (response.data.error) {
                console.log(response);
                setTimeout($scope.embeddablRun(submission), 1000);
            } else {
                $scope.updateSandBox(response.data.stdout, response.data.stderr);
            }
        }, function(error) {
            console.log(error);
            setTimeout($scope.embeddablRun(submission), 1000);
        });
    }
    
    $scope.setSelectedSubmission = function(submission) {
        $scope.selectedSubmission = submission;
    }
    
    $scope.isDisabled = function(variable, element) {
        if (variable == null) {
            $(element).addClass("disabled");
            return true;
        }
        $(element).removeClass("disabled");
        return false;
    }

    $scope.studentTaskSort = function(propertyName) {
        $scope.studentTaskReverse = ($scope.studentTaskProperty === propertyName) ? !$scope.studentTaskReverse : false;
        $scope.studentTaskProperty = propertyName;
    };

    $scope.getAllTasks();
    $scope.getSubmissionFromQueue();
});
