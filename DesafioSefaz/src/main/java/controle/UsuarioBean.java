package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidade.Telefone;
import entidade.Usuario;
import util1.JpaUtil;

@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean {

	/*
	 * @Author Matheus F. Silva
	 * Classe responsável por controlar as telas de manter e pesquisar.
	 * 
	 * É onde os atributos são repassado com os mesmo nomes.
	 */

	private Usuario usuario;
	private Telefone telefone;
	private List<Usuario> listaUsuario;
	private String emailSelecionado;

	// Interface do UsuarioDAO, onde estão os metodos.
	private UsuarioDAO usuarioDAO;
	

	private static final String MANTER = "manterUsuario.xhtml";
	private static final String PESQUISAR = "pesquisarUsuario.xhtml";
	private static final String TELEFONES = "verTelefoneUsuario.xhtml";
	private static final String SAIR = "sair.xhtml";

	public UsuarioBean() {

		this.usuario = new Usuario();
		this.usuario.setTelefones(new ArrayList<Telefone>());

		this.telefone = new Telefone();
		this.listaUsuario = new ArrayList<Usuario>();

		// Instanciando a interface com a implementação, passando a conexão
		this.usuarioDAO = new UsuarioDAOImpl(JpaUtil.getEntityManager());

		// Recupera a lista de todos as pessoas.

		this.listaUsuario = this.usuarioDAO.listarTodos();

		System.out.println(this.listaUsuario);
	}

	public void pesquisar() {
		/*
		 *  Recupera a lista usuarios e mostra 1 telefone
		 *  o primeiro do array.
		 */
		this.listaUsuario = this.usuarioDAO.listarTodos();
		System.out.println("Você está na tela de pesquisa.");
	}

	public void salvar() throws IOException {

		if (this.usuarioDAO.inserir(this.usuario)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sucesso !!!"));

			abrirPesquisarUsuario();

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao inserir !!!"));
		}
	}

	public void abrirManterUsuario() throws IOException {
		//this.limpar();
		FacesContext.getCurrentInstance().getExternalContext().redirect(MANTER);
	}

	public void abrirPesquisarUsuario() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
	}

	public void abrirVerTelefoneUsuario() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(TELEFONES);
	}
	
	public void abrirTelaSair() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(SAIR);
	}

	public void adicionarTelefone() {

		if(!this.existeTelefone(telefone)) {
			
			Telefone telefoneNovo = new Telefone();
			
			telefoneNovo.setDdd(this.telefone.getDdd());
			telefoneNovo.setNumero(this.telefone.getNumero());
			telefoneNovo.setTipo(this.telefone.getTipo());
			telefoneNovo.setUsuario(this.usuario);
			
			this.usuario.getTelefones().add(telefoneNovo);
			
			this.telefone = new Telefone();

		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Telefone já existe !!!"));
		}
		
	}

	private boolean existeTelefone(Telefone telefone) {
		boolean retorno = false;
		
		for (Telefone telLista : this.usuario.getTelefones()) {
			if(telLista.getDdd() == telefone.getDdd() && 
			   telLista.getNumero().equals(telefone.getNumero())) {
				retorno = true;
			}
		}
		
		return retorno;
	}

	public void editar() throws IOException {
		Usuario usuarioEdicao = this.usuarioDAO.pesquisar(emailSelecionado);
		this.usuario = usuarioEdicao;
		abrirManterUsuario();
	}

	public void verTelefone() throws IOException {
		Usuario usuarioTelefone = this.usuarioDAO.pesquisar(emailSelecionado);
		this.usuario = usuarioTelefone;
		abrirVerTelefoneUsuario();
	}

	public String remover() {
		Usuario usuarioRemocao = this.usuarioDAO.pesquisar(emailSelecionado);
		this.usuarioDAO.remover(usuarioRemocao);
		this.listaUsuario = this.usuarioDAO.listarTodos();
		return "";
	}

	public void limpar() {
		this.listaUsuario = new ArrayList<Usuario>();
		this.usuario = new Usuario();
		this.usuario.setTelefones(new ArrayList<Telefone>());
		this.telefone = new Telefone();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getEmailSelecionado() {
		return emailSelecionado;
	}

	public void setEmailSelecionado(String emailSelecionado) {
		this.emailSelecionado = emailSelecionado;
	}
	
}
