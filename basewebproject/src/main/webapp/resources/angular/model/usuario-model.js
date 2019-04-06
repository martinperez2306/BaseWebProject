function Usuario(usuario) {
	var self = this;
	if (usuario != undefined && usuario != null) {
		self.username = usuario.username;
		self.nombre = usuario.nombre;
	}

}

function NuevoUsuario() {
	var self = this;
	self.username = "";
	self.password = "";
	self.nombre = "";
}