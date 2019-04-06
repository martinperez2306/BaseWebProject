/**
 * Creo el modulo Gestion Hogar y le agrego el modulo de usuarios para la pantalla de usuarios.
 */
var app = angular.module('baseWebProjectApp', ['usuariosModule']);
var scope = {};

app.controller('usuariosController',['$scope','usuariosFunctions',function($scope,usuariosFunctions){
	
	scope = $scope;
	
	$scope.usuarios = [];
	
	$scope.init = function(){
		usuariosFunctions.getUsuarios(function(response){
			angular.forEach(response.data,function(usuario){
				$scope.usuarios.push(new Usuario(usuario));
			})
		})
	}
	
	function validarUsuario(usuario,modalUsuario){
		var usuarioValido = true;
		$scope.modalUsuario.faltaNombre = false;
		$scope.modalUsuario.faltaUsername = false;
		$scope.modalUsuario.faltaNombre = false;
		if(usuario.nombre == undefined || usuario.nombre == null || usuario.nombre.length == 0){
			$scope.modalUsuario.faltaNombre = true;
			usuarioValido = false;
		}
		if(usuario.username == undefined || usuario.username == null || usuario.username.length == 0){
			$scope.modalUsuario.faltaUsername = true;
			usuarioValido = false;
		}
		if(usuario.password == undefined || usuario.password == null || usuario.password.length == 0){
			$scope.modalUsuario.faltaPassword = true;
			usuarioValido = false;
		}
		return usuarioValido;
	}
	
	$scope.addUsuario = function(){
		$scope.nuevoUsuario = new NuevoUsuario();
		$scope.modalUsuario = {
				title:"Agregar nuevo usuario",
				mensajeNombre:"Se requiere un nombre",
				mensajeUsername:"Se requiere nombre de usuario",
				mensajePassword:"Se requiere contraseña",
				funcion:$scope.sendAddUsuarioRequest
		}
		$("#modalUsuario").modal();
	}
	
	$scope.sendAddUsuarioRequest = function(nuevoUsuario){
		if(validarUsuario(nuevoUsuario,$scope.modalUsuario)){
			usuariosFunctions.addUsuario(nuevoUsuario,function(response){
				console.log(response);
				$("#modalUsuario").modal('hide');
				$scope.actualizarListaUsuarios();
			})
		}
	}
	
	$scope.deleteUsuario = function(usuario){
		$scope.modalConfirmacion = {
				funcion:$scope.sendDeleteUsuarioRequest,
				parametro:usuario
		}
		$("#modalConfirmacion").modal();
	}
	
	$scope.sendDeleteUsuarioRequest = function(usuario){
		usuariosFunctions.deleteUsuario(usuario.username,function(response){
			console.log(response);
			$scope.actualizarListaUsuarios();
		})
	}
	
	$scope.udpateUsuario = function(usuario){
		$scope.nuevoUsuario = angular.copy(usuario);
		$scope.nuevoUsuario.usernameViejo = usuario.username;
		$scope.modalUsuario = {
				title:"Modificar usuario",
				mensajeNombre:"Se requiere un nombre",
				mensajeUsername:"Se requiere nombre de usuario",
				mensajePassword:"Ingrese su contraseña",
				funcion:$scope.sendUpdateUsuarioRequest,
		}
		$("#modalUsuario").modal();
	}
	
	$scope.sendUpdateUsuarioRequest = function(usuario){
		if(validarUsuario(usuario,$scope.modalUsuario)){
			usuariosFunctions.updateUsuario(usuario.usernameViejo,usuario,function(response){
				console.log(response);
				$("#modalUsuario").modal('hide');
				$scope.actualizarListaUsuarios();
			})
		}
	}
	
	$scope.actualizarListaUsuarios = function(){
		$scope.usuarios = [];
		usuariosFunctions.getUsuarios(function(response){
			angular.forEach(response.data,function(usuario){
				$scope.usuarios.push(new Usuario(usuario));
			})
		})
	}
	
}]);