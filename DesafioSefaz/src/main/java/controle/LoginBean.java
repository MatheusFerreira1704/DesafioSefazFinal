package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidade.Telefone;
import entidade.Usuario;

@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean {

	/**
	 * @author Matheus F.Silva
	 **/
	 
	
	// Essas variaveis são responsaveis para o acesso geral, o admin
	private String usarioAdmin = "admin";
	private String senhaAdmin = "admin";

	//variáveis de login usuario.
	private String usuarioTXT;
	private String senhaTXT;

	private UsuarioDAO usuarioDAO;

	private Usuario usuario;
	private Telefone telefone;
	
	// Atributo de redirecionamento de tela de acordo com o login.
	private static final String ADMIN = "paginas/admin/pesquisarUsuario.xhtml";
	private static final String USER = "paginas/usuario/inicioUsuario.xhtml";
	
	private String mensagem;

	public LoginBean() {
		this.usuarioDAO = new UsuarioDAOImpl();
		this.usuario = new Usuario();
		this.usuario.setTelefones(new ArrayList<Telefone>());
		this.telefone = new Telefone();
	}

	public void entrar() throws IOException {
		if (this.usuarioTXT.equals(this.usarioAdmin) && this.senhaTXT.equals(this.senhaAdmin)) {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ADMIN);
		} else {
			Usuario usuarioPesquisa = this.usuarioDAO.pesquisar(this.usuarioTXT);
			if (usuarioPesquisa != null) {
				if (usuarioPesquisa.getSenha().equals(this.senhaTXT)) {
					FacesContext.getCurrentInstance().getExternalContext().redirect(USER);
				} else {
					this.mensagem = "Erro ao inserir.";
				}
			} else {
				this.mensagem = "Erro ao inserir.";
			}

		}

	}

	public void salvar() throws IOException {
		this.telefone.setUsuario(this.usuario);
		this.usuario.getTelefones().add(this.telefone);

		if (this.usuarioDAO.salvar(this.usuario)) {
			System.out.println("salvou");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sucesso !!!"));
		} else {
			System.out.println("nao salvou");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao inserir !!!"));
		}
	}

	public String getUsuarioTXT() {
		return usuarioTXT;
	}

	public void setUsuarioTXT(String usuarioTXT) {
		this.usuarioTXT = usuarioTXT;
	}

	public String getSenhaTXT() {
		return senhaTXT;
	}

	public void setSenhaTXT(String senhaTXT) {
		this.senhaTXT = senhaTXT;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
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

}
