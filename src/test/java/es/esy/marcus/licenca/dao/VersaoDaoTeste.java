package es.esy.marcus.licenca.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import es.esy.marcus.licenca.domain.Software;
import es.esy.marcus.licenca.domain.Versao;

public class VersaoDaoTeste {
	@Test
	@Ignore
	public void salvar() {
		Long codigo = 1L;

		SoftwareDao softwareDao = new SoftwareDao();
		Software software = softwareDao.buscar(codigo);

		if (software == null) {
			System.out.println("Não foram encontrados softwares");
		} else {
			Versao versao = new Versao();
			VersaoDao versaoDao = new VersaoDao();

			versao.setSoftware(software);
			versao.setVersao("10 Pro 64 bits");
			versaoDao.salvar(versao);
			System.out.print("Cadastrado com Sucesso! - ");
			System.out.println("Código: " + versao.getCodigo() + " Software: " + versao.getSoftware().getNome()
					+ " - Versão: " + versao.getVersao());
		}
	}

	@Test
	@Ignore
	public void listar() {
		VersaoDao versaoDao = new VersaoDao();
		List<Versao> resultado = versaoDao.listar();

		System.out.println("Total de Registros encontrados: " + resultado.size());

		for (Versao versao : resultado) {
			System.out.println("Código: " + versao.getCodigo() + " Software: " + versao.getSoftware().getNome()
					+ " - Versão: " + versao.getVersao());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;

		VersaoDao versaoDao = new VersaoDao();
		Versao versao = versaoDao.buscar(codigo);

		if (versao == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado");
			System.out.println("Código: " + versao.getCodigo() + " Software: " + versao.getSoftware().getNome()
					+ " - Versão: " + versao.getVersao());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;

		VersaoDao versaoDao = new VersaoDao();
		Versao versao = versaoDao.buscar(codigo);

		if (versao == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			versaoDao.excluir(versao);
			System.out.println("Registro Excluído");
			System.out.println("Código: " + versao.getCodigo() + " Software: " + versao.getSoftware().getNome()
					+ " - Versão: " + versao.getVersao());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 1L;

		VersaoDao versaoDao = new VersaoDao();
		Versao versao = versaoDao.buscar(codigo);

		if (versao == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro antes da Edição");
			System.out.println("Código: " + versao.getCodigo() + " Software: " + versao.getSoftware().getNome()
					+ " - Versão: " + versao.getVersao());
			
			versao.setVersao("7 Pro 32 Bits");
			versaoDao.editar(versao);
			
			System.out.println("Registro após Edição");
			System.out.println("Código: " + versao.getCodigo() + " Software: " + versao.getSoftware().getNome()
					+ " - Versão: " + versao.getVersao());
		}
	}
	
	@Test
	//@Ignore
	public void buscarPorSoftware() {
		Long softwareCodigo = 1l;
		VersaoDao versaoDao = new VersaoDao();
		List<Versao> resultado = versaoDao.buscarPorSoftware(softwareCodigo);

		System.out.println("Total de Registros encontrados: " + resultado.size());

		for (Versao versao : resultado) {
			System.out.println("Código: " + versao.getCodigo() + " Software: " + versao.getSoftware().getNome()
					+ " - Versão: " + versao.getVersao());
		}
	}
}
