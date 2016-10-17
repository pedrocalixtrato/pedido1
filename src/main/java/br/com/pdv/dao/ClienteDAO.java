package br.com.pdv.dao;

import java.io.Serializable;


import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Cliente;

@SuppressWarnings("serial")
public class ClienteDAO extends HibernateGenericDAO<Cliente, Long> implements Serializable{
 	
		@Inject
		private EntityManager em;
		
		
	
	public Cliente buscarCliente(Long codigo){
		
		return this.em.createQuery("from Cliente c inner join fetch  c.pessoa where c.codigo = :codigo" , Cliente.class)
				  .setParameter("codigo",codigo) .getSingleResult();
	
	}

	
	
//public List<Cliente> listar(Cliente cliente){
//		
//		Session session = em.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(Cliente.class);
//		
//					
//						
//		return  criteria.list();
//	}

}
