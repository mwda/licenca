package es.esy.marcus.licenca.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Licenca extends GenericDomain {

	@Column(length = 10, unique = true)
	private String patrimonio;

	@Column(length = 15)
	private String numSeriePc;

	@OneToOne
	@JoinColumn(nullable = false)
	private Versao versao;

	@Column(nullable = false)
	private String chave;

	private String observacoes;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public String getNumSeriePc() {
		return numSeriePc;
	}

	public void setNumSeriePc(String numSeriePc) {
		this.numSeriePc = numSeriePc;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
