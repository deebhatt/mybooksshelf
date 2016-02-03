(function () {
	'use strict';

    angular.module('app.widgets').factory('MenuService', MenuService);
    
    function MenuService($http)
    {
    	var service = {
        		getAllCategories : getAllCategories
        };
        return service;
        
        function getAllCategories()
        {
        	return $http.get('getCategoriesandSubCategories')
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