(function () {
	'use strict';

	var core = angular.module('app.core');

	//Toaster Config for messages.
	core.config(toastrConfig);
	/* @ngInject */
	function toastrConfig(toastr) {
		toastr.options.timeOut = 4000;
		toastr.options.positionClass = 'toast-top-center';
	}

	//Other configuration
	core.config(configure);
	/* @ngInject */
	function configure($logProvider, routerHelperProvider) {
		if ($logProvider.debugEnabled) {
			$logProvider.debugEnabled(true);
		}
		/*exceptionHandlerProvider.configure(config.appErrorPrefix);*/
		routerHelperProvider.configure({
			docTitle: '' + ': '
		});
	}
})();