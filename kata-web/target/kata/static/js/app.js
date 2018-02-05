'use strict';

var App = angular.module('myApp',['ngRoute'])
.config(
        ['$routeProvider', function ($routeProvider) {

            $routeProvider.when('/kataCompte/kataUser/', {
                templateUrl: 'views/compte.jsp'
            });


        }]
    );

