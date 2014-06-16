package br.com.fazendola.facade;

import java.io.Serializable;

import br.com.fazendola.dao.UsuarioDAO;
import br.com.fazendola.model.Usuario;

public class UsuarioFacade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public void createUsuario(Usuario usuario) {
		usuarioDAO.beginTransaction();
		usuarioDAO.save(usuario);
		usuarioDAO.commitAndCloseTransaction();
	}
	
	public void updateUsuario(Usuario usuario) {
		usuarioDAO.beginTransaction();
		Usuario persistedUsuario = usuarioDAO.find((int)usuario.getId());
		
		persistedUsuario.setNome(usuario.getNome());
		persistedUsuario.setEmail(usuario.getEmail());
		persistedUsuario.setMatricula(usuario.getMatricula());
		persistedUsuario.setGrupo(usuario.getGrupo());
		persistedUsuario.setSenha(usuario.getSenha());
		
		usuarioDAO.update(persistedUsuario);
		usuarioDAO.commitAndCloseTransaction();
	}
	
	public Usuario loginEValido(String email, String senha) {
		usuarioDAO.beginTransaction();
		Usuario usuario = usuarioDAO.buscarUsuario(email);
		
		if (usuario == null || !usuario.getSenha().equals(senha)) {
			return null;
		}
		
		return usuario;
	}

}
