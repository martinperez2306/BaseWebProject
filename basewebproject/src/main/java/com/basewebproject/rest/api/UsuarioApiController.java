package com.basewebproject.rest.api;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basewebproject.model.Usuario;
import com.basewebproject.rest.model.GeneralResponse;
import com.basewebproject.rest.model.HeaderResponse;
import com.basewebproject.rest.model.HeaderResponse.StatusHeader;
import com.basewebproject.service.UsuarioService;
import com.google.gson.Gson;

@Controller
public class UsuarioApiController implements InitializingBean{
	
	private Gson gson;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	@ResponseBody
	public String getUsuarios() {
		List<Usuario> usuarios = usuarioService.getAll();
		return this.gson.toJson(usuarios);
	}
	
	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	@ResponseBody
	public String addUsuario(@RequestBody Usuario nuevoUsuario) {
		GeneralResponse response = new GeneralResponse();
		usuarioService.addUsuario(nuevoUsuario);
		HeaderResponse header = new HeaderResponse();
		header.setStatus(StatusHeader.OK);
		response.setHeaderResponse(header);
		response.setMensaje("Usuario creado con exito!");
		return this.gson.toJson(response);
	}
	
	@RequestMapping(value = "/usuarios/{username}", method = RequestMethod.GET)
	@ResponseBody
	public String getUsuario() {
		Usuario usuario = usuarioService.getUsuario("");
		return this.gson.toJson(usuario);
	}
	
	@RequestMapping(value = "/usuarios/{username}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUsuario(@PathVariable String username) {
		GeneralResponse response = new GeneralResponse();
		usuarioService.deleteUsuario(username);
		HeaderResponse header = new HeaderResponse();
		header.setStatus(StatusHeader.OK);
		response.setHeaderResponse(header);
		response.setMensaje("Usuario borrado con exito!");
		return this.gson.toJson(response);
	}
	
	@RequestMapping(value = "/usuarios/{username}", method = RequestMethod.PUT)
	@ResponseBody
	public String updateUsuario(@PathVariable String username, @RequestBody Usuario usuarioActualizado) {
		GeneralResponse response = new GeneralResponse();
		usuarioService.updateUsuario(username,usuarioActualizado);
		HeaderResponse header = new HeaderResponse();
		header.setStatus(StatusHeader.OK);
		response.setHeaderResponse(header);
		response.setMensaje("Usuario actualizado con exito!");
		return this.gson.toJson(response);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.gson = new Gson();
	}

}
