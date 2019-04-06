/**
 * Creo el modulo de usuarios
 */
var app = angular.module('usuariosModule', []);

app.factory('usuariosFunctions',['$http',function($http){
	
	var addUsuario = function(usuario,callback){
		$http({
			method: 'POST',
			url: '/basewebproject/usuarios',
			data: JSON.stringify(usuario)
		}).then(function(response){
			callback(response);
		})
	}
	
	var getUsuarios = function(callback){
		$http({
			method: 'GET',
			url: '/basewebproject/usuarios'
		}).then(function(response){
			callback(response);
		})
	}
	
	var getUsuario = function(username,callback){
		$http({
			method: 'GET',
			url: '/basewebproject/usuarios/'+username
		}).then(function(response){
			callback(response);
		})
	}
	
	var deleteUsuario = function(username,callback){
		$http({
			method: 'DELETE',
			url: '/basewebproject/usuarios/'+username
		}).then(function(response){
			callback(response);
		})
	}
	
	var updateUsuario = function(usernameViejo,usuario,callback){
		$http({
			method: 'PUT',
			url: '/basewebproject/usuarios/'+usernameViejo,
			data: JSON.stringify(usuario)
		}).then(function(response){
			callback(response);
		})
	}
	
	return{
		addUsuario:addUsuario,
		getUsuarios:getUsuarios,
		getUsuario:getUsuario,
		deleteUsuario:deleteUsuario,
		updateUsuario:updateUsuario
	}
}]);