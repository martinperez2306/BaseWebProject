package com.basewebproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basewebproject.dao.UsuarioDao;
import com.basewebproject.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public List<Usuario> getAll() {
		return usuarioDao.selectUsuarios();
	}
	
	public void addUsuario(Usuario usuario) {
		usuarioDao.insertUsuario(usuario);
	}
	
	public Usuario getUsuario(String username){
		return usuarioDao.selectUsuarioByKey(username);
	}
	
	public void deleteUsuario(String username) {
		usuarioDao.deleteUsuario(username);
	}
	
	public void updateUsuario(String username,Usuario usuarioActualizado) {
		usuarioDao.updateUsuario(username,usuarioActualizado);
	}
	
}
