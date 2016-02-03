(function () {
    'use strict';
    angular.module('app.mainSearch', []).controller('MainSearchController', MainSearchController);
    		
    	function MainSearchController($scope, $http, MenuService, mybookSearchFilter, filterFilter) {
    		
    		var vm = this;
    		var categoriesArray = [];
    		MenuService.getAllCategories().then(function(data){
    			angular.forEach(data, function(category) {
    				categoriesArray.push({"searchLabel": category.categoryLabel, "categoryName": category.categoryName, "type": "category"});
    				angular.forEach(category.listofsubcategories, function(subcategory) {
    					categoriesArray.push({"searchLabel": subcategory.subcategoryLabel, "subcategoryName": subcategory.subcategoryName, "categoryName": category.categoryName, "type": "sub-category"});	
    				});
    		    });
    		});
    	
	    	vm.productSearch = function() {
	    			vm.variable = [];
		    		if(typeof vm.keywordsearch!="undefined" && vm.keywordsearch!="")
		            {
		    			if(vm.keywordsearch.length==1)
		    			{
		    				vm.variable = mybookSearchFilter(categoriesArray, vm.keywordsearch);
		    			}
		    			else if(vm.keywordsearch.length>1)
		    			{
		    				vm.variable = filterFilter(categoriesArray, vm.keywordsearch);
		    				$http.post('search', $scope.keywordsearch).then(function (response) {
				    			if(response.data != null)
				    			{
				    				angular.forEach(response.data, function(products) {
				    					vm.variable.push({"searchLabel": products.productLabel, "productName": products.productName, "type": "product"});	
				    				});
				    			}
				            });
		    			}
		                if(vm.variable.length == 0)
		                {
		                    vm.variable = [{"categoryName": "", "searchLabel": "Search for '"+vm.keywordsearch+"'", "type": "other"}];
		                }
		            }
	    	}
    	}
})();