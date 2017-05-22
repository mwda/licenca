package es.esy.marcus.licenca.util;

import org.hibernate.Session;
import org.junit.Test;

import es.esy.marcus.licenca.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void conectar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
