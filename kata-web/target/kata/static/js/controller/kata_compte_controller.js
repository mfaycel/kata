'use strict';

angular.module('myApp').controller('CompteController', ['$scope', 'CompteService', function($scope, CompteService) {
    var self = this;
    self.kataCompte={bic:'',balance:'',dateCreation:''};
    self.kataUser={id:'',name:'',password:''};
    self.liKataOperations=[];
    self.fetchCompte=fetchCompte;
    var scope = $scope;
    
       
       function fetchCompte(kataUser){
    	   CompteService.fetchCompte(kataUser)
               .then(
               function(d) {
                   self.kataCompte = d;
                   self.kataUser = d.kataUser;
                   self.liKataOperations = d.liKataOperations;
               },
               function(errResponse){
                   console.error('Error while fetching Compte');
               }
           );
       }
}]);
