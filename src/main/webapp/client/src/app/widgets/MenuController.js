(function () {
    'use strict';
    angular.module('app.widgets').controller('MenuController', MenuController);
    /*ng-inject*/
    function MenuController($http, MenuService) {
    		var vm = this;
    		
    		MenuService.getAllCategories().then(function(data){
    			vm.categoriesArray = data;
    		});
    }
})();