(function () {
    'use strict';
    var myapp = angular.module('app.cart', []);
    myapp.controller('cartController', ['$scope', '$http', function($scope, $http){
    	
    	$scope.cartData = { items: [{
    		label: "Concept of Physics",
    		image: "http://res.cloudinary.com/mybooksshelf/image/upload/v1443953433/disxelr7redgzmoflqmk.jpg",
    		price: "125.5"
    	}] };
    	
    	$scope.addToCart = function(product){
    		$scope.cartData.items.push({
    			label: product.productLabel,
    			image: product.images,
    			price: product.price
    		});
    		console.log($scope.cartData.items);
    	};
    	
    }]);
})();