package es.esy.marcus.licenca.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import es.esy.marcus.licenca.domain.Usuario;

public class UsuarioDaoTeste {
	@Test
	@Ignore
	public void salvar() {
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDao();

		usuario.setCodigo(1L);
		usuario.setAtivo(true);
		usuario.setEmail("mw.alves@gmail.com");
		usuario.setNivel('A');
		usuario.setNome("Marcus Winicius");
		usuario.setSobrenome("Duque Alves");
		usuario.setSenhaSemCriptografia("ricardo");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		
		usuario.setSenha(hash.toHex());
		usuario.setUsuario("mw.alves");
		usuarioDao.salvar(usuario);
		System.out.println("Usuário Cadastrado: " + usuario.getNome()+" Senha: "+usuario.getSenha());
	}

	@Test
	@Ignore
	public void listar() {
		UsuarioDao usuarioDao = new UsuarioDao();
		List<Usuario> resultado = usuarioDao.listar();

		System.out.println("Total de Registros encontrados: " + resultado.size());

		for (Usuario usuario : resultado) {
			System.out.println("Código: " + usuario.getCodigo() + ", Nome: " + usuario.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;

		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado");
			System.out.println("Código: " + usuario.getCodigo() + ", Nome: " + usuario.getNome());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			usuarioDao.excluir(usuario);
			System.out.println("Registro excluído");
			System.out.println("Código: " + usuario.getCodigo() + ", Nome: " + usuario.getNome());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;

		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro antes da Edição");
			System.out.println("Código: " + usuario.getCodigo() + ", Sobrenome: " + usuario.getSobrenome());
			
			usuario.setSobrenome("Edição 01");
			usuarioDao.editar(usuario);
			
			System.out.println("Registro antes da Edição");
			System.out.println("Código: " + usuario.getCodigo() + ", Sobrenome: " + usuario.getSobrenome());
		}
	}
	
	@Test
	public void autenticar(){
		String usuario1 = "mw.alvess";
		String senha = "ricardo";
		
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.autenticar(usuario1, senha);
				
		System.out.println("Usuario autenticado: "+ usuario.getNome());
	}
}
