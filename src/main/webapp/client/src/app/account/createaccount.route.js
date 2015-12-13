(function () {
	'use strict';

	angular
		.module('app.createaccount')
		.run(appRun);

	/* @ngInject */
	function appRun(routerHelper) {
		routerHelper.configureStates(getStates());
	}

	function getStates() {
		return [
			{
				state: 'createaccount',
				config: {
					url: '/ModalSignup',
					views: {
						'mainContent': {
							templateUrl: 'client/src/app/account/createaccount.html',
							controller : 'CreateAccountController as vm'
						}
					}

				}
            }
        ];
	}
})();