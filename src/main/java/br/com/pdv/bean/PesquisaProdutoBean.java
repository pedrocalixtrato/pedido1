package br.com.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pdv.dao.ProdutoDAO;
import br.com.pdv.domain.Pedido;
import br.com.pdv.domain.Produto;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class PesquisaProdutoBean implements Serializable {
	
	private List<Produto> produtos;
	private Produto produto;
	private Pedido pedido;
	
	@Inject
	ProdutoDAO produtoDAO;
	
	//@PostConstruct
	public void init(){
		
		this.produtos =  produtoDAO.filtrar(produto, "descricao");
		
		
	}
	@PostConstruct
	public void listar(){
		
		this.produtos = produtoDAO.listar(produto);
		
	}
	
	public List<Produto> completar(String descricao){
		 
		return this.produtoDAO.porDesc(descricao);
	}


	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}


	public Produto getProduto() {if(produto == null){
		produto = new Produto();
	}
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	

}
