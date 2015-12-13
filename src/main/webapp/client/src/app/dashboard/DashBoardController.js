(function () {
    'use strict';
    angular.module('app.dashboard').controller('DashBoardController', DashBoardController);
    /*ng-inject*/
    function DashBoardController(DashBoardService,$http) {
    	var vm = this;
    	console.log(DashBoardService.getProducts());
    	  $http.get('getProducts')
        .then(function (response) {
        	vm.products = response.data;
        });
    	
    }
})();