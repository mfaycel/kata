'use strict';

angular.module('myApp').controller('KataUserController', ['$scope', 'KataUserService', function($scope, KataUserService) {
	
	var self = this;
	self.kataUser={id:null,name:'',password:''};

	self.login = login;
	var scope = $scope;
	
	function loginUser(kataUser){
		console.log('login  Userr', kataUser);
		KataUserService.loginUser(kataUser)
		.then(
				function(d) {
					self.kataUser = d;
					$scope.kataUser=d;
				},
				function(errResponse){
					console.error('Error while login User');
				}
		);
	}
	function login() {
		console.log('login  User', self.kataUser);
		loginUser(self.kataUser);
	}

}]);
