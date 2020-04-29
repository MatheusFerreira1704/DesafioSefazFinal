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

@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean {

	/**
	 * @author Matheus F. Silva, classe responsável pela regra de negócio do
	 *         Usuario.
	 * 
	 */
	private Usuario usuario;
	private Telefone telefone;
	private List<Usuario> listaUsuario;
	private String emailSelecionado;

	// Interface, onde encontra-se a descrição dos métodos.
	private UsuarioDAO usuarioDAO;
	private long idTelefoneSelecionado;

	private static final String MANTER = "addTelefone.xhtml";
	private static final String PESQUISAR = "pesquisarUsuario.xhtml";
	private static final String SAIR = "sair.xhtml";

	public UsuarioBean() {

		this.usuario = new Usuario();
		this.usuario.setTelefones(new ArrayList<Telefone>());

		this.telefone = new Telefone();
		this.listaUsuario = new ArrayList<Usuario>();

		// Instanciando a interface com a implementaÃ§Ã£o, passando a conexÃ£o
		this.usuarioDAO = new UsuarioDAOImpl();

		// Recupera a lista de todos as pessoas.

		this.listaUsuario = this.usuarioDAO.listarTodos();

		System.out.println(this.listaUsuario);
	}

	public void pesquisar() {
		// Recupera a lista usuarios e mostra 1 telefone o primeiro do array.

		this.listaUsuario = this.usuarioDAO.listarTodos();
		System.out.println("VocÃª estÃ¡ na tela de pesquisa.");
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

	public void abrirTelefone() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect(MANTER);
	}

	public void abrirPesquisarUsuario() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
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
		Usuario usuarioEdicao = this.usuarioDAO.pesquisar(this.emailSelecionado);
		this.usuario = usuarioEdicao;
		abrirTelefone();
	}

	public void limpar() {
		this.listaUsuario = new ArrayList<Usuario>();
		this.usuario = new Usuario();
		this.usuario.setTelefones(new ArrayList<Telefone>());
		this.telefone = new Telefone();
	}

	public void removerTel() {
		this.usuarioDAO.removerTelefone(this.idTelefoneSelecionado);
		this.usuario = this.usuarioDAO.pesquisar(emailSelecionado);

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
