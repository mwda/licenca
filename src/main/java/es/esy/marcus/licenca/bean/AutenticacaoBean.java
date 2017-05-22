package es.esy.marcus.licenca.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import es.esy.marcus.licenca.dao.UsuarioDao;
import es.esy.marcus.licenca.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable{
	private Usuario usuario;
	private Usuario usuarioLogin;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}
	
	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
	
	@PostConstruct
	public void iniciar(){
		usuario = new Usuario();
	}
	
	public void autenticar(){
		try {
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioLogin = usuarioDao.autenticar(usuario.getUsuario(), usuario.getSenha());
			
			if(usuarioLogin == null){
				Messages.addGlobalError("Usuario ou Senha Incorretos");
				return;
			}
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			Messages.addGlobalError(erro.getMessage());
			erro.printStackTrace();
		}
		
	}
	
	public void sair(){
		try {
			usuarioLogin = null;
			Faces.redirect("./pages/autenticacao.xhtml");
			Messages.addGlobalInfo("Usuario Deslogado com Sucesso!");			
		} catch (IOException erro) {
			Messages.addGlobalError(erro.getMessage());
			erro.printStackTrace();
		}
	}
	
	public boolean temPermissoes(List<String> permissoes){
		for(String permissao : permissoes){
			if(usuarioLogin.getNivel() == permissao.charAt(0)){
				return true;
			}	
		}
		
		return false;
	}
}
