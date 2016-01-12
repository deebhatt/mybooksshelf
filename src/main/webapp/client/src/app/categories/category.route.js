(function () {
	'use strict';

	angular
		.module('app.category')
		.run(appRun);

	/* @ngInject */
	function appRun(routerHelper) {
		routerHelper.configureStates(getStates());
	}

	function getStates() {
		return [
			{
				state: 'category-detail',
				config: {
					url: '/category/:categoryName/',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/categories/category.html',
							controller : 'categoryController as vm'
						}
					}

				}
            }
        ];
	}
})();