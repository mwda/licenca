package es.esy.marcus.licenca.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import es.esy.marcus.licenca.domain.Licenca;
import es.esy.marcus.licenca.domain.Usuario;
import es.esy.marcus.licenca.domain.Versao;

public class LicencaDaoTeste {

	@Test
	@Ignore
	public void salvar() {
		Long codigoUsuario = 2L;
		Long codigoVersao = 2L;

		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.buscar(codigoUsuario);

		VersaoDao versaoDao = new VersaoDao();
		Versao versao = versaoDao.buscar(codigoVersao);

		if (usuario == null && versao == null) {
			System.out.println("Não foram encontrados usuario ou versão");
		} else {

			Licenca licenca = new Licenca();
			LicencaDao licencaDao = new LicencaDao();

			licenca.setPatrimonio("24046-4555");
			licenca.setNumSeriePc("");
			licenca.setVersao(versao);
			licenca.setChave("987654321");
			licenca.setObservacoes("");
			licenca.setUsuario(usuario);
			licencaDao.salvar(licenca);

			System.out.println("Licença Cadastrada: " + licenca.getCodigo() + " - Software: "
					+ licenca.getVersao().getSoftware().getNome() + " - " + licenca.getVersao().getVersao()
					+ " - Chave: " + licenca.getChave());
		}
	}

	@Test
	@Ignore
	public void listar() {
		LicencaDao licencaDao = new LicencaDao();
		List<Licenca> resultado = licencaDao.listar();

		System.out.println("Total de Registros encontrados: " + resultado.size());

		for (Licenca licenca : resultado) {
			System.out.println("Licença Cadastrada: " + licenca.getCodigo() + " - Software: "
					+ licenca.getVersao().getSoftware().getNome() + " - " + licenca.getVersao().getVersao()
					+ " - Chave: " + licenca.getChave());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;

		LicencaDao licencaDao = new LicencaDao();
		Licenca licenca = licencaDao.buscar(codigo);

		if (licenca == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado");
			System.out.println("Licença Cadastrada: " + licenca.getCodigo() + " - Software: "
					+ licenca.getVersao().getSoftware().getNome() + " - " + licenca.getVersao().getVersao()
					+ " - Chave: " + licenca.getChave());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;

		LicencaDao licencaDao = new LicencaDao();
		Licenca licenca = licencaDao.buscar(codigo);

		if (licenca == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			licencaDao.excluir(licenca);
			System.out.println("Registro excluído");
			System.out.println("Licença Cadastrada: " + licenca.getCodigo() + " - Software: "
					+ licenca.getVersao().getSoftware().getNome() + " - " + licenca.getVersao().getVersao()
					+ " - Chave: " + licenca.getChave());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoLicenca = 2L;

				
		LicencaDao licencaDao = new LicencaDao();
		Licenca licenca = licencaDao.buscar(codigoLicenca);

		if (licenca == null) {
			System.out.println("Não foram encontrados usuario ou versão");
		} else {
			System.out.println("Registro antes da Edição");
			System.out.println("Licença Cadastrada: " + licenca.getCodigo() + " - Software: "
					+ licenca.getVersao().getSoftware().getNome() + " - " + licenca.getVersao().getVersao()
					+ " - Chave: " + licenca.getChave());
			
			licenca.setChave("Editada");
			licencaDao.editar(licenca);
			
			System.out.println("Registro após a Edição");
			System.out.println("Licença Cadastrada: " + licenca.getCodigo() + " - Software: "
					+ licenca.getVersao().getSoftware().getNome() + " - " + licenca.getVersao().getVersao()
					+ " - Chave: " + licenca.getChave());
		}
	}
}
