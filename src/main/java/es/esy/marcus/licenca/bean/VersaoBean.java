package es.esy.marcus.licenca.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import es.esy.marcus.licenca.dao.SoftwareDao;
import es.esy.marcus.licenca.dao.VersaoDao;
import es.esy.marcus.licenca.domain.Software;
import es.esy.marcus.licenca.domain.Versao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VersaoBean implements Serializable {

	private Versao versao;
	private List<Versao> versoes;
	private List<Software> softwares;

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public List<Versao> getVersoes() {
		return versoes;
	}

	public void setVersoes(List<Versao> versoes) {
		this.versoes = versoes;
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
			VersaoDao versaoDao = new VersaoDao();
			versoes = versaoDao.listar();

		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao preencher a lista de versões");

		}
	}

	public void novo() {
		try {
			versao = new Versao();

			VersaoDao versaoDao = new VersaoDao();
			versoes = versaoDao.listar();
			SoftwareDao softwareDao = new SoftwareDao();
			softwares = softwareDao.listar("nome");
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao gerar uma nova versao");
		}

	}

	public void Salvar() {
		try {
			VersaoDao versaoDao = new VersaoDao();
			versaoDao.salvar(versao);
			novo();
			Messages.addGlobalInfo("Versão Salva com Sucesso!");

		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao salvar a versão");
		}
	}

	public void Excluir(ActionEvent evento) {
		try {
			versao = (Versao) evento.getComponent().getAttributes().get("versaoSelecionada");
			VersaoDao versaoDao = new VersaoDao();
			versaoDao.excluir(versao);
			Messages.addGlobalInfo("Versão Removida com Sucesso!");
			versoes = versaoDao.listar();

		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao excluir a versão");
		}
	}

	public void Editar(ActionEvent evento) {
		try {
			versao = (Versao) evento.getComponent().getAttributes().get("versaoSelecionada");

			VersaoDao versaoDao = new VersaoDao();
			versoes = versaoDao.listar();
			SoftwareDao softwareDao = new SoftwareDao();
			softwares = softwareDao.listar("nome");
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ops, Ocorreu um erro ao editar a versão");
		}
	}
}