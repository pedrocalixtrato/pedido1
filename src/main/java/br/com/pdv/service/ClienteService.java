package br.com.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.omnifaces.util.Messages;

import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.domain.Cliente;
import br.com.pdv.domain.Pessoa;
import br.com.pdv.util.jpa.Transactional;

@SuppressWarnings("serial")
public class ClienteService implements Serializable {

	@Inject
	private ClienteDAO clienteDAO;

	//@Transactional
	public void salvar(Cliente cliente, Pessoa pessoa) {

		try {
			
			cliente.setPessoa(pessoa);
			clienteDAO.salvar(cliente);
			Messages.addGlobalInfo("Salvo com sucesso!");
		} catch (RuntimeException e) {

			Messages.addGlobalError("Não foi possivel salvar este cadastro");
			e.printStackTrace();

		}

	}

	@Transactional
	public void excluir(Cliente cliente) {
		try {
			this.clienteDAO.excluir(cliente.getCodigo());
			Messages.addGlobalInfo("Removido com sucesso!");
		} catch (RuntimeException e) {

			Messages.addGlobalError("Não foi possivel remover este cadastro");
			e.printStackTrace();

		}
	}

	public List<Cliente> listar(Cliente cliente) {

		return clienteDAO.filtrar(cliente, "nome");

	}

}
