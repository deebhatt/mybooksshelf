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
				state: 'subcategory-detail',
				config: {
					url: '/:categoryName/:subcategoryName',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/subcategories/sub_category.html',
							controller : 'subcategoryController as vm'
						}
					}

				}
            }
        ];
	}
})();