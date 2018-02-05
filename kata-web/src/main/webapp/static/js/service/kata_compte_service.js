'use strict';

angular.module('myApp').factory('CompteService', ['$http', '$q', function($http, $q){

	var REST_SERVICE_URI = 'http://localhost:8080/kata/kataCompte/kataUser/';
	var factory = {
			fetchCompte: fetchCompte
	};

	return factory;

	function fetchCompte(kataUser) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI,kataUser.id)
		.then(
				function (response) {
					console.error('compte service '+response.data);
					deferred.resolve(response.data);
					
				},
				function(errResponse){
					console.error('Error while fetching Compte');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}

	
}]);
