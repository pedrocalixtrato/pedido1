package br.com.pdv.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.omnifaces.util.Messages;

import br.com.pdv.Enum.TipoPessoa;
import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.dao.PessoaDAO;
import br.com.pdv.domain.Cliente;
import br.com.pdv.domain.Pessoa;
import br.com.pdv.service.ClienteService;
import br.com.pdv.util.jsf.FacesUtil;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private Pessoa pessoa;
	@Inject
	private ClienteService clienteService;

	private List<TipoPessoa> tipoPessoas;
	@SuppressWarnings("unused")
	private ClienteDAO clienteDAO;
	@SuppressWarnings("unused")
	private PessoaDAO pessoaDAO;
	
	@Inject
	private EntityManager em;
	
	
	
	
	

	public void salvar() {

		this.clienteService.salvar(cliente, pessoa);

	}
	
	public void editar(ActionEvent evento){
		
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");		
//		this.clienteService.salvar(cliente, pessoa);
		
	}
	
	public void excluir(ActionEvent evento){
		
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		this.clienteService.excluir(cliente);
		
	}

	@PostConstruct
	public void init() {		
		
		this.cliente = new Cliente();
		this.pessoa = new Pessoa();		
		this.tipoPessoas = Arrays.asList(TipoPessoa.values());

	}
	
	public void carregarCadastro(){
		
		try{
			
			String valor = FacesUtil.getParam("clienteCod");
			String valorP = FacesUtil.getParam("pessoaCod");
			if(valor != null || valorP != null){
				
				Long codigo = Long.parseLong(valor);
				Long codigoP = Long.parseLong(valorP);

				this.cliente = em.find(Cliente.class, codigo);
				this.pessoa = em.find(Pessoa.class, codigoP);
				
			}
			
		}catch(RuntimeException e){
			Messages.addGlobalError("Erro ao carregar os dados do cliente");
			e.printStackTrace();
		}
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<TipoPessoa> getTipoPessoas() {	
	
		return tipoPessoas;
	}

	public void setTipoPessoas(List<TipoPessoa> tipoPessoas) {
		this.tipoPessoas = tipoPessoas;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

		
}
