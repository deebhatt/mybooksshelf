(function () {
    'use strict';

    angular.module('app.cart').factory('CartService', CartService);

    /* @ngInject */
    function CartService($cookies) {
        var service = {
        		addToCart : addToCart,
        		getfromCart : getfromCart,
        		removefromCart : removefromCart,
        		checkforDuplicateproduct : checkforDuplicateproduct,
        		clearCartData : clearCartData
        };
        return service;

        function addToCart(product){
			var myapp = [];
			var cartData = {
			"productName": product.productName,
			"productLabel": product.productLabel,
			"image": product.productImages[0].imageUrl,
			"price": product.usedBookSellingPrice,
			"quantity": 1,
			"p_id": product.id
			};
			myapp.push(cartData);
			var data = $cookies.get('mybookscart');
			if(typeof data !== 'undefined')
			{
				data = JSON.parse(data);
				angular.forEach(data, function(product) {
			        myapp.push(product);
			    });
			}
			$cookies.put('mybookscart', JSON.stringify(myapp));
        }
        
        function getfromCart(){
			var data = $cookies.get('mybookscart');
			var myapp = [];
			myapp.push(data);
			if(typeof data !== 'undefined')
			{
				myapp = JSON.parse(myapp);
				return myapp;
			}
			return null;
		}
        
        function removefromCart(productId){
        	var data = $cookies.get('mybookscart');
        	var myapp = [];
			if(typeof data !== 'undefined')
			{
				data = JSON.parse(data);
				angular.forEach(data, function(product) {
			        if (product.p_id !== productId) myapp.push(product);
			    });
			}
			$cookies.put('mybookscart', JSON.stringify(myapp));
        }
        
        function clearCartData(){
        	$cookies.remove('mybookscart');
        }
        
        function checkforDuplicateproduct(productId){
        	var data = $cookies.get('mybookscart');
        	var found = false;
        	if(typeof data !== 'undefined')
			{
				data = JSON.parse(data);
				angular.forEach(data, function(product) {
			        if (product.p_id === productId) found = true;
			    });
			}
        	return found;
        }
        
    }
})();