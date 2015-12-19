(function () {
    'use strict';
    angular.module('app.category').controller('subcategoryController', subcategoryController);
    /*ng-inject*/
    function subcategoryController($http, $stateParams) {
    	var vm = this;
    	vm.categoryName = $stateParams.categoryName;
    	vm.subcategoryName = $stateParams.subcategoryName;
    	  $http.get('getProducts')
        .then(function (response) {
        	//var products = response.data;
        	vm.products = response.data;
        });
    	
    }
})();