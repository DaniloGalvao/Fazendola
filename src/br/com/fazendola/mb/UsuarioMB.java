package br.com.fazendola.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.fazendola.facade.UsuarioFacade;
import br.com.fazendola.mb.AbstractMB;
import br.com.fazendola.model.Usuario;

@SessionScoped
@ManagedBean(name="usuarioMB")
public class UsuarioMB extends AbstractMB implements Serializable {
	public static final String INJECTION_NAME = "#{usuarioMB}";
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioFacade usuarioFacade;
	
	public UsuarioMB() {
		usuario = new Usuario();
	}
	
	public UsuarioFacade getUsuarioFacade() {
		if (usuarioFacade == null) {
			usuarioFacade = new UsuarioFacade();
		}
		return usuarioFacade;
	}
	
	public void cadastraUsuario() {
		try {
			getUsuarioFacade().createUsuario(usuario);
			closeDialog();
			displayInfoMessageToUser("Cadastro realizado com sucesso!");
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Desculpe, não foi possível realizar o cadastro. Tente novamente mais tarde.");
			e.printStackTrace();
		}
	}
	
	public void atualizaUsuario() {
		getUsuarioFacade().updateUsuario(usuario);
	}
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "/pages/public/index.xhtml?faces-redirect=true";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
