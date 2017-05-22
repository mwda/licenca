package es.esy.marcus.licenca.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import es.esy.marcus.licenca.dao.UsuarioDao;
import es.esy.marcus.licenca.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarios = usuarioDao.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao preencher a lista de usuários");
		}
	}

	public void novo() {
		usuario = new Usuario();
	}

	public void salvar() {
		try {
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.salvar(usuario);
			novo();
			usuarios = usuarioDao.listar();
			Messages.addGlobalInfo("Usuário Salvo com Sucesso!");
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao salvar o usuário");

		}
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.excluir(usuario);
			Messages.addGlobalInfo("Usuário Removido com Sucesso!");
			usuarios = usuarioDao.listar();

		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao excluir o usuário");
		}
	}

	public void editar(ActionEvent evento) {
		try{
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		
		
		}catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao editar o usuário");
		}
	}
}
