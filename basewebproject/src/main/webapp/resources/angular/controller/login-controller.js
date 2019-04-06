var app = angular.module('baseWebProjectApp', ['loginModule']);
var scope = {};

app.controller('loginController',function($scope,loginFunctions){
	
	scope = $scope;
	
	$scope.loginRequest = {
			username:'',
			password:''
	}
	
	$scope.login = function(loginRequest){
		loginFunctions.login(loginRequest,function(response){
			console.log(response);
		})
	}
	
})