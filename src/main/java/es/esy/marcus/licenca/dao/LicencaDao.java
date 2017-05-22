package es.esy.marcus.licenca.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import es.esy.marcus.licenca.domain.Licenca;
import es.esy.marcus.licenca.util.HibernateUtil;

public class LicencaDao extends GenericDAO<Licenca> {

	public void salvar(Licenca entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		if (entidade.getPatrimonio().trim().isEmpty()) {
			entidade.setPatrimonio(null);
		}

		try {
			transacao = sessao.beginTransaction();
			sessao.merge(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Licenca> listarpatrimonio() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Licenca.class);
			consulta.add(Restrictions.isNotNull("patrimonio"));
			consulta.addOrder(Order.asc("patrimonio"));
			List<Licenca> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Licenca> listarLicencaDisponivel() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Licenca.class);
			consulta.add(Restrictions.isNull("patrimonio"));
			consulta.addOrder(Order.asc("versao"));
			List<Licenca> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
