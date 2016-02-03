(function () {
    'use strict';

    angular.module('app.product').factory('ProductService', ProductService);

    /* @ngInject */
    function ProductService($http) {
        var service = {
        		getProductsbyCategoryPagination : getProductsbyCategoryPagination,
        		getProductsbySubCategoryPagination : getProductsbySubCategoryPagination
        };

        return service;

        function getProductsbyCategoryPagination(postData)
        {
            return $http.post('getProductsbyCategory', postData)
 		   .then(function(response) {
 	            return response.data;
 	        })
 	        .catch(function(error) {
 	            /*$log.error('ERROR:', error);
 	            throw error;*/
 	        	throw httpError.status + " : " + httpError.data;
 	        });
        }
        
        function getProductsbySubCategoryPagination(postData)
        {
            return $http.post('getProductsbySubcategory', postData)
 		   .then(function(response) {
 	            return response.data;
 	        })
 	        .catch(function(error) {
 	            /*$log.error('ERROR:', error);
 	            throw error;*/
 	        	throw httpError.status + " : " + httpError.data;
 	        });
        }
    }

})();