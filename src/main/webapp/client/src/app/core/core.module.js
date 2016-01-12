(function() {
    'use strict';

    angular.module('app.core', [
        /*
         * Angular modules
         */
        'ngAnimate', 'ui.router', 'ngSanitize','ngCookies',
        /*
         * Our reusable cross app code modules
         */
		
		'blocks.router'
    ]);
})();
