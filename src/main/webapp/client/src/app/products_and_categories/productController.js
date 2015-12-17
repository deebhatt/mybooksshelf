(function () {
    'use strict';
    angular.module('app.product').controller('productController', productController);
    /*ng-inject*/
    function productController($http, $routeParams) {
    	var vm = this;
    	console.log('Product Name '+$routeParams.productName);
    	  /*$http.get('getProducts')
        .then(function (response) {
        	vm.products = response.data;
        });*/
    	
    }
})();