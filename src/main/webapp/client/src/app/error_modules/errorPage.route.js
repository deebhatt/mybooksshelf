(function () {
	'use strict';

	angular
		.module('app.errorPage')
		.run(appRun);

	/* @ngInject */
	function appRun(routerHelper) {
		routerHelper.configureStates(getStates());
	}

	function getStates() {
		return [
			{
				state: 'error-page',
				config: {
					url: '/404',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/error_modules/error-page.html',
							controller : 'errorPageController as vm'
						}
					}

				}
            }
        ];
	}
})();