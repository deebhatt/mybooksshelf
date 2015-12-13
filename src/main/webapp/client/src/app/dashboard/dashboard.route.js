(function () {
	'use strict';

	angular
		.module('app.dashboard')
		.run(appRun);

	/* @ngInject */
	function appRun(routerHelper) {
		routerHelper.configureStates(getStates());
	}

	function getStates() {
		return [
			{
				state: 'dashboard',
				config: {
					url: '/dashboard',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/dashboard/dashboard.html',
							controller : 'DashBoardController as vm'
						}
					}

				}
            }
        ];
	}
})();