(function () {
    'use strict';
    angular.module('app.cart').controller('cartController', cartController);
    /*ng-inject*/
    function cartController($scope, $rootScope, CartService, $anchorScroll, $location, $uiViewScroll, $http) {
    	var vm = this;
    	
    	vm.quantity = 1;
    	
    	vm.isShippingEnable = false;
    	vm.isordersummaryEnable = false;
    	vm.isPayementMethodEnable = false;
    	
    	
    	vm.cartData = [];
    	vm.cartData = CartService.getfromCart();
    	
    	
    	vm.removeProduct = function(productId){
    		CartService.removefromCart(productId);
    		vm.cartData = CartService.getfromCart();
    	}
    	
    	vm.enableShipping = function(){
    		vm.isShippingEnable = true;
    	}
    	
    	vm.addGuestUserAddress = function(){
    		console.log(JSON.stringify(vm.user));
    		vm.isordersummaryEnable = true;
    	}
    	
    	vm.enablePayement = function(){
    		vm.isPayementMethodEnable = true;
    	}
    	
    	vm.sendCodetoEmail = function(){
    		console.log(vm.cod_email);
    		$http.post('sendToken', vm.cod_email).then(function (response) {
            	console.log(response.data);
            });
    	}
    	
    	vm.verifyCode = function(){
    		$http.post('verifyToken', vm.verify_code).then(function (response) {
            	console.log(response.data);
            });
    	}
    	
    	vm.placeOrder = function(){
    		var productData = [];
			angular.forEach(vm.cartData, function(product) {
				productData.push({ p_id: product.p_id,
								   quantity: product.quantity });
		    });
			var orderForm = { orderProducts: productData,
							  address: vm.user };
			console.log(JSON.stringify(orderForm));
    		$http.post('placeOrder', JSON.stringify(orderForm)).then(function (response) {
            	console.log(response.data);
            	if(response.data.type === "success")
            	{
            		
            		//Clear Cart Data
            		CartService.clearCartData();
            		
            	}
            });
    	}
    	
    	vm.getallOrders = function() {
    		$http.get('getallOrders').then(function (response) {
    			console.log(JSON.stringify(response.data));
    		});
    	}
    	
    }
})();