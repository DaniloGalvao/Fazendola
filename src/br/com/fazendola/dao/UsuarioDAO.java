package br.com.fazendola.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.fazendola.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public Usuario buscarUsuario(String email) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		
		return super.findOneResult(Usuario.BUSCAR_USUARIO, parameters);
	}
	
}
