(function () {
	'use strict';

	angular
		.module('app.cart')
		.run(appRun);

	/* @ngInject */
	function appRun(routerHelper) {
		routerHelper.configureStates(getStates());
	}

	function getStates() {
		return [
			{
				state: 'cart-details',
				config: {
					url: '/viewcart',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/cart/cart.html',
							controller : 'cartController as vm'
						}
					}

				}
            },
            {

				state: 'cart-checkout-0',
				config: {
					url: '/checkoutinit',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/cart/one-page-checkout.html',
							controller : 'cartController as vm'
						}
					}

				}
            
            }
        ];
	}
})();