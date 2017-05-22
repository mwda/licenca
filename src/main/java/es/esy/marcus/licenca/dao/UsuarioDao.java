package es.esy.marcus.licenca.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import es.esy.marcus.licenca.domain.Usuario;
import es.esy.marcus.licenca.util.HibernateUtil;

public class UsuarioDao extends GenericDAO<Usuario> {
	
	public Usuario autenticar(String usuario, String senha) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			
			SimpleHash hash = new SimpleHash("md5", senha);
			
			consulta.add(Restrictions.eq("usuario", usuario));
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
