package br.com.pdv.test;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.pdv.dao.ItemDAO;
import br.com.pdv.domain.ItemPedido;
import br.com.pdv.domain.Pedido;
import br.com.pdv.domain.Produto;

public class Testes {
	
	@Test
	@Ignore
	public void salvar (){
		
		Pedido p = new Pedido();
		p.setValorTotal(new BigDecimal(10));
		
		ItemPedido iten = new ItemPedido();		
		iten.setPedido(p);
		
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.salvar(iten);	
	}
	
}
