package br.com.pdv.bean;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.omnifaces.util.Messages;

import br.com.pdv.dao.UsuarioDAO;
import br.com.pdv.domain.Cliente;
import br.com.pdv.domain.Pessoa;
import br.com.pdv.domain.Usuario;
import br.com.pdv.util.jsf.FacesUtil;

@Named
@SuppressWarnings("serial")
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private EntityManager em;

	
	
	public void salvar(){
		
		try{
			
			this.usuarioDAO.salvar(usuario);
			Messages.addGlobalInfo("Salvo com sucesso!");
		}catch(RuntimeException e){
			Messages.addGlobalError("Não foi possivel salvar este cadastro");
			e.printStackTrace();
		}
		
	}
	
	public void excluir(ActionEvent evento){
		
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		
		try{
			usuarioDAO.excluir(usuario.getCodigo());
			
			Messages.addGlobalInfo("Cadastro removido com sucesso!");
		}catch(RuntimeException e){			
			Messages.addGlobalError("Não foi possivel remover este cadastro");
			e.printStackTrace();
		}
		
	}
	
	public void carregarCadastro(){
		try{
			String valor = FacesUtil.getParam("usuarioCod");			
			if(valor != null ){
				
				Long codigo = Long.parseLong(valor);				

				this.usuario = em.find(Usuario.class, codigo);
				
				
			}
			
		}catch(RuntimeException e){
			Messages.addGlobalError("Erro ao carregar os dados do usuario");
			e.printStackTrace();
		}
		
	}
	
	
	public Usuario getUsuario() {if(usuario == null){
		usuario = new Usuario();
	}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
	

}
