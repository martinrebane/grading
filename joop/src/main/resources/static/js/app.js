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
    $scope.selectedTask = {};
    
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
        var taskName = JSON.parse($scope.selectedTask).name;
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
        var id = studentTask.id;
        var url = 'http://localhost:8080/studenttask/' + id;
        console.log(url);
        $http({
            method: 'GET',
            url: url
        }).then(function(response) {
            console.log(response);
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.setGrade = function() {
        var url = "http://localhost:8080/grade/update";
        var id = 0;
        var gr = 0;
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
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.sendMail = function() {
        var uniid = null;
        var reviewId = null;
        var subject = null;
        var url = "http://localhost:8080/mail/send/${uniid}/${reviewId}/${subject}";
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
        var id = JSON.parse($scope.selectedTask).id;
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
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.updateReview = function() {
        var uniid = null;
        var taskName = null;
        var review = null;
        var url = "http://localhost:8080/review/update/${uniid}/${taskName}";
        $http({
            method: 'POST',
            url: url,
            data: review
        }).then(function(response) {
            console.log(response);
        }, function(error) {
            console.log(error);
        });
    }
    
    $scope.updateSandBox = function() {
        var sandBox = null;
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

        $http({
            method: 'POST',
            url: 'http://api.embeddabl.com/save-files',
            headers: {
                "Content-Type": "application/json"
            },
            data: {
                type: "ZIP",
                name: "maria.kert",
                url: "http://dijkstra.cs.ttu.ee/~Maria.Kert/loputoo/maria.kert.zip",
                user: "JOOP"
            }
        }).then(function(response) {
            console.log(response);
        }, function(error) {
            console.log(error);
        });
    }

    $scope.embeddablRun = function() {
        
        $http({
            method: 'POST',
            url: "http://api.embeddabl.com/run",
            headers: {
                "Content-Type": "application/json"
            },
            data: {
                "file": {
                    "path": "maria.kert/EX05/ee/ttu/java/albumcreation/Band.java"
                },
                "classpath": "maria.kert/EX05",
                "package": "ee.ttu.java.albumcreation",
                "user": "JOOP"
            }
        }).then(function(response) {
            console.log(response.data.stdout);
        }, function(error) {
            console.log(error);
        });
    }

    $scope.allTasks = $scope.getAllTasks();
});
