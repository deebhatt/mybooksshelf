(function () {
    'use strict';
    angular.module('app.product').controller('productController', productController);
    /*ng-inject*/
    function productController($http, $stateParams) {
    	var vm = this;
    	vm.productName = $stateParams.productName;
    	var url = 'product/'+vm.productName;
    	  $http.get(url)
        .then(function (response) {
        	vm.product = response.data;
        });
    	
    }
})();