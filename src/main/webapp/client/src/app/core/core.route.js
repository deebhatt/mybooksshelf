(function () {
	'use strict';

	angular
		.module('app.core')
		.run(appRun);

	/* @ngInject */
	function appRun(routerHelper) {
		var otherwise = '/dashboard';
		routerHelper.configureStates(getStates(), otherwise);
	}

	function getStates() {
		return [
			/*{
				state: 'app',
				config: {
					url: '/app',
					abstract: true,
					views : {
						'topMenu' : {
							templateUrl: "client/src/app/layout/menu.html",
							resolve: {
								 @ngInject 
								preProcessData: function ($http, $rootScope) {
									return $http.get("getCategories")
										.then(function (response) {
											$rootScope.categoriesArray = response.data;
										}).catch(function (msg) {
	
										});
								}
							}
						}
					}
				}
            }*/
        ];
	}
})();