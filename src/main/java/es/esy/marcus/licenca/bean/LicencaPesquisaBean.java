package es.esy.marcus.licenca.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import es.esy.marcus.licenca.dao.LicencaDao;
import es.esy.marcus.licenca.domain.Licenca;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LicencaPesquisaBean implements Serializable{
	
	private Licenca licenca;
	private Boolean exibePainelDados;
	
	public Licenca getLicenca() {
		return licenca;
	}
	
	public void setLicenca(Licenca licenca) {
		this.licenca = licenca;
	}
	
	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}
	
	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}
	
	@PostConstruct
	public void novo(){
		licenca = new Licenca();
		exibePainelDados = false;
	}
	
	public void buscar(){
		try {
			LicencaDao licencaDao = new LicencaDao();
			Licenca resultado = licencaDao.buscar(licenca.getPatrimonio());
			
			if(resultado == null){
				exibePainelDados = false;
				Messages.addGlobalInfo("Patrimônio informado não encontrado");
			}else{
				exibePainelDados = true;
				licenca = resultado;
			}
			
			
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar a liceça");
		}
	}
}
