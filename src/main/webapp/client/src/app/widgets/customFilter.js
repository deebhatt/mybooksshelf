(function () {
	'use strict';

    angular.module('app.widgets').filter('mybookSearch', function() {
    	return function(inputArray, keySearch)
        {
    		var outputArray = [];
        	for(var i=0; i<inputArray.length; i++) {
        		if(inputArray[i].searchLabel.toLowerCase().startsWith(keySearch.toLowerCase()))
        		{
        			outputArray.push(inputArray[i]);
        		}
        	}
        	return outputArray;
        }
    	
    });
    
})();