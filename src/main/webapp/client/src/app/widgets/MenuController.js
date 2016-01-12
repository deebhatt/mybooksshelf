(function () {
    'use strict';
    angular.module('app.widgets').controller('MenuController', MenuController);
    /*ng-inject*/
    function MenuController($http) {
    		var vm = this;
    	
    		$http.get("getCategoriesandSubCategories")
				.then(function (response) {
					vm.categoriesArray = response.data;
				}).catch(function (msg) {

				});
    }
})();