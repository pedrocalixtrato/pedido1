package br.com.pdv.dao;

import java.io.Serializable;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Usuario;

@SuppressWarnings("serial")
public class UsuarioDAO extends HibernateGenericDAO<Usuario, Long> implements Serializable{
	

}
