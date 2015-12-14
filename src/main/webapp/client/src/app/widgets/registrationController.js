(function () {
    'use strict';
    var myapp = angular.module('app.reg', []);
    myapp.controller('registrationController', ['$scope', function($scope){
    	
    	$scope.Registration = function(){
    		console.log($scope.user);
    	};
    	
    }]);
})();