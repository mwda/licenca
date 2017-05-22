package es.esy.marcus.licenca.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Versao extends GenericDomain{
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Software software;
	
	@Column(length = 32, nullable = false)
	private String versao;

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

}
