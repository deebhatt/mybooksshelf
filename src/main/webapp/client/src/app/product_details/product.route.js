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
				state: 'product-detail',
				config: {
					url: '/product/:productName',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/product_details/product-details.html',
							controller : 'productController as vm'
						}
					}

				}
            }
        ];
	}
})();