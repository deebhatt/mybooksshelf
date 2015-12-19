(function () {
    'use strict';
    angular.module('app.widgets').controller('MenuController', MenuController);
    /*ng-inject*/
    function MenuController($scope , $http) {
    		$http.get("getCategoriesandSubCategories")
				.then(function (response) {
					$scope.categoriesArray = response.data;
				}).catch(function (msg) {

				});
    }
})();