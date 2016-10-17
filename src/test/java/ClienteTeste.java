import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.com.pdv.domain.Cliente;

public class ClienteTeste {
	
	private static EntityManagerFactory factory;

	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("PDV");
	}

	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}

	@After
	public void tearDown() {
		this.manager.close();
	}
	
	@Test
	@Ignore
	public void salvarCascata(){
		
//		Cliente cliente = new Cliente();
//		
//		
//		Pessoa pessoa = new Pessoa();
//		pessoa.setNome("PEDRO MIGUEL");
//		pessoa.setBairro("ELDORADO");
//		pessoa.setTelefone("8143-4529");
//		
//		this.manager.getTransaction().begin();
//		this.manager.merge(cliente);
//		this.manager.getTransaction().commit();
		
	
	}
	
	@Test
	public void salvarCliente (){
		
	Cliente cliente = new Cliente();
	cliente.setNome("PEDRO MIGUEL");
	
	this.manager.getTransaction().begin();
	this.manager.merge(cliente);
	this.manager.getTransaction().commit();
	
	System.out.println("salvo com sucesso");
	
	}
	

}
