(function () {
    'use strict';
    angular.module('app.dashboard').controller('DashBoardController', DashBoardController);
    /*ng-inject*/
    function DashBoardController(DashBoardService, $http, CartService) {
    	var vm = this;
    	
    	//console.log(DashBoardService.getProducts());
    	vm.addTocartbutton = "Add to Cart";
    	
    	$http.get('getProducts')
        	.then(function (response) {
        		vm.products = response.data;
        	});
    	
    	vm.getModalProducts = function(product) {
    		if(CartService.checkforDuplicateproduct(product.id))
    		{
    			vm.addTocartbutton = "Already Added";
        		vm.setdisabled = true;
    		}
    		else
    		{
    			vm.addTocartbutton = "Add to Cart";
        		vm.setdisabled = false;
    		}
    		vm.modalProduct = product;
    	}
    	
    	vm.addToCart = function(product) {
    		CartService.addToCart(product);
    		vm.addTocartbutton = "Product Added";
    		vm.setdisabled = true;
    	};
    }
})();