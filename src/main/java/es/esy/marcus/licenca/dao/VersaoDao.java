package es.esy.marcus.licenca.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import es.esy.marcus.licenca.domain.Versao;
import es.esy.marcus.licenca.util.HibernateUtil;

public class VersaoDao extends GenericDAO<Versao> {
	@SuppressWarnings("unchecked")
	
	public List<Versao> buscarPorSoftware(long softwareCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Versao.class);
			consulta.add(Restrictions.eq("software.codigo", softwareCodigo));
			consulta.addOrder(Order.asc("versao"));
			List<Versao> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
