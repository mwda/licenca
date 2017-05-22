package es.esy.marcus.licenca.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import es.esy.marcus.licenca.dao.LicencaDao;
import es.esy.marcus.licenca.dao.SoftwareDao;
import es.esy.marcus.licenca.dao.UsuarioDao;
import es.esy.marcus.licenca.dao.VersaoDao;
import es.esy.marcus.licenca.domain.Licenca;
import es.esy.marcus.licenca.domain.Software;
import es.esy.marcus.licenca.domain.Usuario;
import es.esy.marcus.licenca.domain.Versao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LicencaBean implements Serializable {

	private Licenca licenca;
	private Software software;
	private Usuario usuarioLogado;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	private List<Licenca> licencas;
	private List<Licenca> licencasDisponiveis;
	private List<Software> softwares;
	private List<Versao> versoes;
	private List<Usuario> usuarios;

	public Licenca getLicenca() {
		return licenca;
	}

	public void setLicenca(Licenca licenca) {
		this.licenca = licenca;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Licenca> getLicencas() {
		return licencas;
	}

	public void setLicencas(List<Licenca> licencas) {
		this.licencas = licencas;
	}
	
	public List<Licenca> getLicencasDisponiveis() {
		return licencasDisponiveis;
	}
	
	public void setLicencasDisponiveis(List<Licenca> licencasDisponiveis) {
		this.licencasDisponiveis = licencasDisponiveis;
	}

	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}

	public List<Versao> getVersoes() {
		return versoes;
	}

	public void setVersoes(List<Versao> versoes) {
		this.versoes = versoes;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void Listar() {
		try {
			LicencaDao licencaDao = new LicencaDao();
			licencas = licencaDao.listarpatrimonio();
			licencasDisponiveis = licencaDao.listarLicencaDisponivel();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao preencher a lista de licenças");
		}

	}

	public void novo() {

		try {
			licenca = new Licenca();

			LicencaDao licencaDao = new LicencaDao();
			licencas = licencaDao.listarpatrimonio();
			licencasDisponiveis = licencaDao.listarLicencaDisponivel();
			
			SoftwareDao softwareDao = new SoftwareDao();
			softwares = softwareDao.listar("nome");

			versoes = new ArrayList<Versao>();

			UsuarioDao usuarioDao = new UsuarioDao();
			usuarios = usuarioDao.listar("usuario");
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao gerar uma nova licença");
		}
	}

	public void Salvar() {
		try {
			usuarioLogado = autenticacaoBean.getUsuarioLogin();
			
			LicencaDao licencaDao = new LicencaDao();
			licenca.setUsuario(usuarioLogado);
			licencaDao.salvar(licenca);
			novo();
			Messages.addGlobalInfo("Licença Salva com Sucesso!");

		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao salvar uma nova licença");
		}

	}

	public void Excluir(ActionEvent evento) {
		try {
			licenca = (Licenca) evento.getComponent().getAttributes().get("licencaSelecionada");
			LicencaDao licencaDao = new LicencaDao();
			licencaDao.excluir(licenca);
			Messages.addGlobalInfo("Licença removida com sucesso!");
			licencas = licencaDao.listarpatrimonio();
			licencasDisponiveis = licencaDao.listarLicencaDisponivel();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao excluir uma licença");
		}
	}

	public void Editar(ActionEvent evento) {
		try {
			licenca = (Licenca) evento.getComponent().getAttributes().get("licencaSelecionada");

			LicencaDao licencaDao = new LicencaDao();
			licencas = licencaDao.listarpatrimonio();
			licencasDisponiveis = licencaDao.listarLicencaDisponivel();

			VersaoDao versaoDao = new VersaoDao();
			versoes = versaoDao.listar();

			UsuarioDao usuarioDao = new UsuarioDao();
			usuarios = usuarioDao.listar("usuario");
			
			SoftwareDao softwareDao = new SoftwareDao();
			softwares = softwareDao.listar("nome");


		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro editar uma licença");
		}
		
	}
	
	public void Popular(){
		try {
			if(software != null){
				VersaoDao versaoDao = new VersaoDao();
				versoes = versaoDao.buscarPorSoftware(software.getCodigo());
				
			}else{
				versoes = new ArrayList<>(); 
			}
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao filtrar as versões");
		}
	}
}
