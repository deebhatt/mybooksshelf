(function () {
    'use strict';
    angular.module('app.category').controller('categoryController', categoryController);
    /*ng-inject*/
    function categoryController($http, $stateParams, $location, ProductService) {
    	var vm = this;
    	
    	vm.categoryName = $stateParams.categoryName;
    	vm.numPerPage = 3;
    	vm.currentPage = 1;
    	vm.maxSize = vm.totalItems/vm.numPerPage;
    	
    	var postData = vm.categoryName+":"+vm.currentPage;
    	ProductService.getProductsbyCategoryPagination(postData).then(function(response){
	    	if(typeof response == "string" && response == "")
    		{
    			$location.url('/404');
    		}
    		else
    		{
    			vm.products = response;
    		}
	    });

    	vm.pageChanged = function() {
    	    var postData = vm.categoryName+":"+vm.currentPage;
    	    ProductService.getProductsbyCategoryPagination(postData).then(function(response){
    	    	if(typeof response == "string" && response == "")
        		{
        			$location.url('/404');
        		}
        		else
        		{
        			vm.products = response;
        		}
    	    });
    	};
    	
    	$http.post('countAll', 'Products').then(function (response) {
    			vm.totalItems = response.data;
    	});

    }
})();