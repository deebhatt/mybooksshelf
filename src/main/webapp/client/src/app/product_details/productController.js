(function () {
    'use strict';
    angular.module('app.product').controller('productController', productController);
    /*ng-inject*/
    function productController($http, $stateParams, CartService) {
    	var vm = this;
    	
    	vm.addTocartbutton = "Add to Cart";
    	
    	
    	var productName = $stateParams.productName;
    	var url = 'product/'+productName;
    	$http.get(url)
        	.then(function (response) {
        		vm.product = response.data;
        		if(CartService.checkforDuplicateproduct(vm.product.id))
        		{
        			vm.addTocartbutton = "Already Added";
            		vm.setdisabled = true;
        		}
        	});
    	
    	vm.addToCart = function(product) {
    		CartService.addToCart(product);
    		vm.addTocartbutton = "Product Added";
    		vm.setdisabled = true;
    	};
    }
})();