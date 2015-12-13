(function () {
    'use strict';

    angular.module('app.dashboard').factory('DashBoardService', DashBoardService);

    /* @ngInject */
    function DashBoardService($http) {
        var service = {
        		getProducts : getProducts 
        };

        return service;

        function getProducts() {
            return $http.get('getProducts')
                .then(function (response) {
                    return response.data;
                });
        }
    }

})();