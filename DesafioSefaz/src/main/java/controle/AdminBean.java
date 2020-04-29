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

@ManagedBean(name = "AdminBean")
@SessionScoped
public class AdminBean {

	/*
	 * @Author Matheus F. Silva Classe respons·vel pela regra de nÈgÛcio do login
	 * admin.
	 * 
	 * a onde os atributos s√£o repassado com os mesmo nomes.
	 */

	private Usuario usuario;
	private Telefone telefone;
	private List<Usuario> listaUsuario;
	private String emailSelecionado;

	// Chamada do UsuarioDAO.
	private UsuarioDAO usuarioDAO;

	private static final String MANTER = "manterUsuario.xhtml";
	private static final String PESQUISAR = "pesquisarUsuario.xhtml";
	private static final String TELEFONES = "verTelefoneUsuario.xhtml";
	private static final String SAIR = "sair.xhtml";

	public AdminBean() {

		this.usuario = new Usuario();
		this.usuario.setTelefones(new ArrayList<Telefone>());

		this.telefone = new Telefone();
		this.listaUsuario = new ArrayList<Usuario>();

		// Instanciando a interface com a implementa√ß√£o, passando a conex√£o
		this.usuarioDAO = new UsuarioDAOImpl();

		// Recupera a lista de todos as pessoas.

		this.listaUsuario = this.usuarioDAO.listarTodos();

		System.out.println(this.listaUsuario);
	}

	public void pesquisar() {
		/*
		 * Recupera a lista usuarios e mostra 1 telefone o primeiro do array.
		 */
		this.listaUsuario = this.usuarioDAO.listarTodos();
	}

	public void salvar() throws IOException {

		if (this.usuarioDAO.salvar(this.usuario)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sucesso !!!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao inserir !!!"));
		}
	}

	public void abrirManterUsuario() throws IOException {

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
		Telefone telefoneNovo = new Telefone();

		telefoneNovo.setDdd(this.telefone.getDdd());
		telefoneNovo.setNumero(this.telefone.getNumero());
		telefoneNovo.setTipo(this.telefone.getTipo());
		telefoneNovo.setUsuario(this.usuario);

		this.usuario.getTelefones().add(telefoneNovo);

		this.telefone = new Telefone();

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

	public void remover() {
		this.usuarioDAO.remover(emailSelecionado);
		this.listaUsuario = this.usuarioDAO.listarTodos();

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
