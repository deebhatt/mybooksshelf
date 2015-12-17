(function () {
	'use strict';

	angular
		.module('app.product')
		.run(appRun);

	/* @ngInject */
	function appRun(routerHelper) {
		routerHelper.configureStates(getStates());
	}

	function getStates() {
		return [
			{
				state: '/:productName',
				config: {
					url: '/:productName',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/products_and_categories/product-details.html',
							controller : 'productController as vm'
						}
					}

				}
            }
        ];
	}
})();