(function () {
    'use strict';
    angular.module('app.category').controller('categoryController', categoryController);
    /*ng-inject*/
    function categoryController($http, $stateParams) {
    	var vm = this;
    	console.log('Category Name '+$stateParams.categoryName);
    	  /*$http.get('getProducts')
        .then(function (response) {
        	vm.products = response.data;
        });*/
    	
    }
})();