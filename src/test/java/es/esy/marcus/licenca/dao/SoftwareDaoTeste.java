package es.esy.marcus.licenca.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import es.esy.marcus.licenca.domain.Software;

public class SoftwareDaoTeste {
	@Test
	@Ignore
	public void salvar() {
		Software software = new Software();
		SoftwareDao softwareDao = new SoftwareDao();

		software.setNome("Autocad");
		software.setDescricao("Software para criação de projetos");
		softwareDao.salvar(software);
		System.out.println("Software Cadastrado: " + software.getNome());
	}

	@Test
	@Ignore
	public void listar() {
		SoftwareDao softwareDao = new SoftwareDao();
		List<Software> resultado = softwareDao.listar();

		System.out.println("Total de Registros encontrados: " + resultado.size());

		for (Software software : resultado) {
			System.out.println("Código: " + software.getCodigo() + ", Nome: " + software.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;

		SoftwareDao softwareDao = new SoftwareDao();
		Software software = softwareDao.buscar(codigo);

		if (software == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado");
			System.out.println("Código: " + software.getCodigo() + ", Nome: " + software.getNome());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		SoftwareDao softwareDao = new SoftwareDao();
		Software software = softwareDao.buscar(codigo);

		if (software == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			softwareDao.excluir(software);
			System.out.println("Registro Excluído");
			System.out.println("Código: " + software.getCodigo() + ", Nome: " + software.getNome());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;

		SoftwareDao softwareDao = new SoftwareDao();
		Software software = softwareDao.buscar(codigo);

		if (software == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro antes da Edição");
			System.out.println("Código: " + software.getCodigo() + ", Nome: " + software.getDescricao());
			
			software.setDescricao("Edição 001");
			softwareDao.editar(software);
			
			System.out.println("Registro após da Edição");
			System.out.println("Código: " + software.getCodigo() + ", Nome: " + software.getDescricao());
		}
	}
}
