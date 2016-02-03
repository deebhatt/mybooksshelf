(function () {
    'use strict';
    angular.module('app.product').controller('productController', productController);
    /*ng-inject*/
    function productController($http, $stateParams, CartService, $location) {
    	var vm = this;
    	
    	vm.addTocartbutton = "Add to Cart";
    	
    	
    	var productName = $stateParams.productName;
    	var url = 'product/'+productName;
    	$http.get(url).then(function (response) {
    		if(response.data != "")
    		{
        		vm.product = response.data;
        		if(CartService.checkforDuplicateproduct(vm.product.id))
        		{
        			vm.addTocartbutton = "Already Added";
            		vm.setdisabled = true;
        		}
    		}
    		else
    		{
    			$location.url('/404');
    		}
        });
    	
    	vm.addToCart = function(product) {
    		CartService.addToCart(product);
    		vm.addTocartbutton = "Product Added";
    		vm.setdisabled = true;
    	};
    }
})();