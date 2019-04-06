var app = angular.module('loginModule', []);

app.factory('loginFunctions',function($http){
	
	var login = function(loginRequest,callback){
		$http({
			method: 'POST',
			url: '/login'
		}).then(function(response){
			callback(response);
		})
	}
	
	return{
		login:login
	}
});