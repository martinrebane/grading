var app = angular.module('gradingApp', []);

app.controller('tabController', function() {
    this.tab = 1;

    this.setTab = function(newTab) {
        this.tab = newTab;
    };

    this.isSet = function(tabNum) {
        return this.tab === tabNum;
    };
});

app.controller('appController', function($scope, $http) {

    $scope.data = [];
    $scope.allTasks = [];
    $scope.studentTasks = [];
    $scope.submissions = [];
    $scope.selectedTask = null;
    $scope.selectedStudentTask = null;
    $scope.selectedSubmission = null;
    
    $scope.getAllTasks = function() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/task/get'
        }).then(function(response) {
            console.log(response);
            $scope.allTasks = response.data;
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.getAllStudentTasks = function() {
        var task = JSON.parse($scope.currentTask);
        $scope.selectedTask = task;
        var taskName = task.name;
        var url = 'http://localhost:8080/task/' + taskName;
        console.log(url);
        
        $http({
            method: 'GET',
            url: url
        }).then(function(response) {
            console.log(response);
            $scope.studentTasks = response.data;
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.getSubmissions = function(index) {
        var studentTask = $scope.studentTasks[index];
        $scope.selectedStudentTask = studentTask;
        var id = studentTask.id;
        var url = 'http://localhost:8080/studenttask/' + id;
        console.log(url);
        
        $http({
            method: 'GET',
            url: url
        }).then(function(response) {
            console.log(response);
            $scope.submissions = response.data;
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.setGrade = function(gr) {
        var url = "http://localhost:8080/grade/update";
        var submission = $scope.selectedSubmission;
        var id = submission.id;
        var grade = {
            id: id,
            grade: gr
        };
        
        $http({
            method: 'POST',
            url: url,
            data: grade
        }).then(function(response) {
            console.log(response);
            $scope.getAllStudentTasks();
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.sendMail = function() {
        var uniid = $scope.selectedStudentTask.uniid;
        var reviewId = $scope.selectedStudentTask.review.reviewId;
        var url = "http://localhost:8080/mail/send/${uniid}/" + reviewId;
        
        $http({
            method: 'GET',
            url: url
        }).then(function(response) {
            console.log(response);
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
            var url = "http://localhost:8080/plagiarism/run";

            $http({
                method: 'POST',
                url: url,
                data: plagiarism
            }).then(function(response) {
                console.log(response);
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
        var url = "http://localhost:8080/review/update";
        
        $http({
            method: 'POST',
            url: url,
            data: review
        }).then(function(response) {
            console.log(response);
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
        
        var url = "http://localhost:8080/sandbox/update";
        
        $http({
            method: 'POST',
            url: url,
            data: sandBox
        }).then(function(response) {
            console.log(response);
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.embeddablSendFiles = function() {
        var name = $scope.selectedSubmission.date;
        var location = $scope.selectedSubmission.sandBox.location;

        $http({
            method: 'POST',
            url: 'http://api.embeddabl.com/save-files',
            headers: {
                "Content-Type": "application/json"
            },
            data: {
                type: "ZIP",
                name: name,
                url: "http://localhost:8080" + location,
                user: "JOOP"
            }
        }).then(function(response) {
            console.log(response);
            if (response.data.state) {
                $scope.embeddablRun();
            } else {
                $scope.embeddablSendFiles();
            }
        }, function(error) {
            console.log(error);
        });
    }

    $scope.embeddablRun = function() {
        var path = $scope.selectedSubmission.sandBox.location;
        var package = $scope.selectedSubmission.sandBox.packagePath;
        var classPath = $scope.selectedSubmission.sandBox.classPath;
        
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
            console.log(response.data.stdout);
            $scope.updateSandBox(response.data.stdout, response.data.stderr);
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.setSelectedSubmission = function(submission) {
        $scope.selectedSubmission = submission;
    }

    $scope.getAllTasks();
});
