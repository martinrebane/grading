var app = angular.module('gradingApp', []);

app.controller('tabController', function() {
    this.tab = 1;

    this.setTab = function(newTab){
      this.tab = newTab;
    };

    this.isSet = function(tabNum){
      return this.tab === tabNum;
    };
});

app.controller('appController', function($scope, $http) {

    $scope.data = [];
    $scope.allTasks = [];
    $scope.studentTasks = [];
    
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
        var taskName = $("#allTasks").val();
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

    $scope.allTasks = $scope.getAllTasks();
});
