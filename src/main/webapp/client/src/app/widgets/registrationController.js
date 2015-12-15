(function () {
    'use strict';
    var myapp = angular.module('app.reg', []);
    myapp.controller('registrationController', ['$scope', '$http', function($scope, $http){
    	
    	$scope.Registration = function(){
    		console.log($scope.user);
    		
    		$http.post('registeruser', JSON.stringify($scope.user)).then(function (response) {
            	console.log(response.data);
            	vm.registrationMessage = response.data;
            });
    	};
    	
    }]);
})();