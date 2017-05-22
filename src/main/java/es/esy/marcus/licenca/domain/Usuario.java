package es.esy.marcus.licenca.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@Column(length = 30, nullable = false, unique = true)
	private String usuario;

	@Column(length = 32, nullable = false)
	private String nome;

	@Column(length = 32)
	private String sobrenome;

	@Column(length = 32, nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private Character nivel;

	@Column(nullable = false)
	private Boolean ativo;

	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Character getNivel() {
		return nivel;
	}
	
	@Transient
	public String getNivelFormatado(){
		String nivelFormatado = null;
		
		if(nivel == 'A'){
			nivelFormatado = "Administrador";
		}else if(nivel == 'U'){
			nivelFormatado = "Usuário";
		}
		
		return nivelFormatado;
	}

	public void setNivel(Character nivel) {
		this.nivel = nivel;
	}

	public Boolean getAtivo() {
		return ativo;
	}
	
	public String getAtivoFormatado(){
		String ativoFormatado = null;
		
		if(ativo == true){
			ativoFormatado = "Ativo";
		}else{
			ativoFormatado = "Inativo";
		}
		return ativoFormatado;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}
	
	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}
	
	

}
