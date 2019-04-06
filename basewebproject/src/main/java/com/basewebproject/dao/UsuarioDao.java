package com.basewebproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.basewebproject.model.Usuario;

@Component
public class UsuarioDao implements InitializingBean{
	
	@Autowired
	private DriverManagerDataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	
	private static String SELECT_USUARIO = "SELECT * FROM USUARIO";
	
	private static String SELECT_USUARIO_KEY = SELECT_USUARIO + " WHERE USERNAME = :username";
	
	private static String INSERT_USUARIO = "INSERT INTO USUARIO (USERNAME, PASSWORD, NOMBRE) VALUES (:username, :password, :nombre)";
	
	private static String DELETE_USUARIO = "DELETE FROM USUARIO WHERE USERNAME = :username";
	
	private static String UPDATE_USUARIO = "UPDATE USUARIO SET USERNAME = :usernameNuevo, PASSWORD = :passwordNuevo, NOMBRE = :nombreNuevo WHERE USERNAME = :username";
	
	private class UsuarioRowMapper implements RowMapper<Usuario>{

		@Override
		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setUsername(rs.getString("username"));
			usuario.setPassword(rs.getString("password"));
			usuario.setNombre(rs.getString("nombre"));
			return usuario;
		}
		
	}
	
	/**
	 * Obtener todos los usuarios
	 * @return
	 */
	public List<Usuario> selectUsuarios(){  
		 return jdbcTemplate.query(SELECT_USUARIO,new UsuarioRowMapper());  
	}
	
	
	/**
	 * Inserta un nuevo usuario.
	 * @param usuario
	 */
	public void insertUsuario(Usuario usuario) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", usuario.getUsername());
		parameters.addValue("password", usuario.getPassword());
		parameters.addValue("nombre", usuario.getNombre());
		jdbcTemplate.update(INSERT_USUARIO, parameters);
	}
	
	/**
	 * Obtiene un usuario correspondiente a su username.
	 * @param username
	 * @return
	 */
	public Usuario selectUsuarioByKey(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		List<Usuario> usuarios = jdbcTemplate.query(SELECT_USUARIO_KEY,parameters,new UsuarioRowMapper()); 
		return usuarios.get(0);
	}
	
	/**
	 * Borra un usuario segun su username
	 * @param username
	 */
	public void deleteUsuario(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		jdbcTemplate.update(DELETE_USUARIO, parameters);
	}

	/**
	 * Actualiza un usuario segun su username
	 * @param username
	 */
	public void updateUsuario(String username,Usuario usuarioActualizado) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("usernameNuevo", usuarioActualizado.getUsername());
		parameters.addValue("nombreNuevo", usuarioActualizado.getNombre());
		parameters.addValue("passwordNuevo", usuarioActualizado.getPassword());
		jdbcTemplate.update(UPDATE_USUARIO, parameters);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

}
