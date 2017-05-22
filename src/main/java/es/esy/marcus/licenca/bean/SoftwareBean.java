package es.esy.marcus.licenca.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import es.esy.marcus.licenca.dao.SoftwareDao;
import es.esy.marcus.licenca.domain.Software;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SoftwareBean implements Serializable {

	private Software software;
	private List<Software> softwares;

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}

	@PostConstruct
	public void Listar() {
		try {
			SoftwareDao softwareDao = new SoftwareDao();
			softwares = softwareDao.listar();

		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao preencher a lista de softwares");
		}
	}

	public void novo() {
		software = new Software();
	}

	public void Salvar() {
		try {
			SoftwareDao softwareDao = new SoftwareDao();
			softwareDao.salvar(software);
			novo();
			softwares = softwareDao.listar();
			Messages.addGlobalInfo("Software Salvo com Sucesso!");
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao salvar o software");
		}
	}

	public void Excluir(ActionEvent evento){
		try{
			software = (Software) evento.getComponent().getAttributes().get("softwareSelecionado");
			SoftwareDao softwareDao = new SoftwareDao();
			softwareDao.excluir(software);
			Messages.addGlobalInfo("Software Removido com Sucesso!");
			softwares = softwareDao.listar();
			
		}catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao excluir o software");
		}
	}
	
	
	public void Editar(ActionEvent evento) {
		try {
			software = (Software) evento.getComponent().getAttributes().get("softwareSelecionado");

		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao editar o software");
		}
	}
}
