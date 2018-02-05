'use strict';

angular.module('myApp').factory('KataUserService', ['$http', '$q','$window','CompteService', function($http, $q, $window,CompteService){

	var REST_SERVICE_URI = 'http://localhost:8080/kata/';
	var factory = {
			loginUser:loginUser
	};

	return factory;

	function loginUser(kataUser) {
		console.error('before send post '+kataUser);
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI+"kataUser/login/", kataUser)
		.then(
				function (response) {
					deferred.resolve(response.data);
					if(response.data.id>0){
						CompteService.fetchCompte(response.data);
						$window.location.href = 'kataCompte/kataUser/';
					}
				},
				function(errResponse){
					console.error('Error while login User');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}

}]);
