if (typeof(Storage) !== undefined) {
    if (sessionStorage.username) {
        window.location = '/index.html';
    }
} else {
    $("#alert-div").addClass("alert");
    $("#alert-div").addClass("alert-danger");
    $("#alert-div").html("Sorry, your browser does not support web storage...");
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

app.controller('loginController', function($http, $location) {
    
    this.login = function() {
        var username = $("#username").val();
        var password = $("#password").val();
        var url = 'http://codereview.ee:8090/app/login';
        var user = {
            username: username,
            password: password
        }
        console.log(url);
        $http({
            method: 'POST',
            url: url,
            data: user
        }).then(function(response) {
            if (response.data === true) {
                if(typeof(Storage) !== "undefined") {
                    if (!sessionStorage.username) {
                        sessionStorage.username = username;
                        $("#password").val("");
                        password = null;
                        $("#alert-div").removeClass("alert");
                        $("#alert-div").removeClass("alert-danger");
                        $("#alert-div").html("");
                        window.location = '/index.html';
                    }
                }
            } else {
                $("#alert-div").addClass("alert");
                $("#alert-div").addClass("alert-danger");
                $("#alert-div").html("Wrong username or password");
            }
        }, function(error) {
            console.log(error);
        });
    }
});