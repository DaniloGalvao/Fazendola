package br.com.fazendola.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.fazendola.facade.UsuarioFacade;
import br.com.fazendola.mb.UsuarioMB;
import br.com.fazendola.model.Usuario;

@RequestScoped
@ManagedBean
public class LoginMB extends AbstractMB {
	@ManagedProperty(value = UsuarioMB.INJECTION_NAME)
	private UsuarioMB usuarioMB;
	private UsuarioFacade usuarioFacade;

	private String email;
	private String senha;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setUsuarioMB(UsuarioMB usuarioMB) {
		this.usuarioMB = usuarioMB;
	}
	
	public UsuarioFacade getUsuarioFacade() {
		if (usuarioFacade == null) {
			usuarioFacade = new UsuarioFacade();
		}
		return usuarioFacade;
	}
	
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario usuario = getUsuarioFacade().loginEValido(email, senha);
		
		if (usuario != null){
			usuarioMB.setUsuario(usuario);
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().setAttribute("user", usuario);
			if (usuario.eProfessor())
				return "/pages/protected/prof/profIndex.xhtml";
			if (usuario.eAluno())
				return "/pages/protected/aluno/alunoIndex.xhtml";
		}

		context.addMessage("mensagemLoginInvalido", new FacesMessage(
				"Usuario ou Senha inválidos."));
		
		return null;
	}
}
