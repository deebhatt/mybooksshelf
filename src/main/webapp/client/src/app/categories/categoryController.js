(function () {
    'use strict';
    angular.module('app.category').controller('categoryController', categoryController);
    /*ng-inject*/
    function categoryController($http, $stateParams) {
    	var vm = this;
    	vm.categoryName = $stateParams.categoryName;
    	  $http.get('getProducts')
        .then(function (response) {
        	//var products = response.data;
        	vm.products = response.data;
        });
    	
    }
})();