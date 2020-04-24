package controle;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidade.Usuario;
import util1.JpaUtil;
 



@ManagedBean(name = "LoginB")
@RequestScoped
public class LoginBean {
	
	// Essas variaveis são responsaveis para o acesso geral, o admin
		private String usarioAdmin = "admin";
		private String senhaAdmin = "admin";

		private String usuarioTXT;
		private String senhaTXT;

		private UsuarioDAO usuarioDAO;
		
		private Usuario usuario;
		
		private static final String PESQUISAR = "paginas/usuario/pesquisarUsuario.xhtml";
		//private UsuarioDAO usuarioDAO;
		private String mensagem;
		
// let's go!
		// e se der um maven install?
		// acho que o erro esta na classe ve,
		public LoginBean() {
			this.usuarioDAO = new UsuarioDAOImpl(JpaUtil.getEntityManager());
			this.usuario = new Usuario();

		}
		
		
		public void entrar() throws IOException {
			if(this.usuarioTXT.equals(this.usarioAdmin)
			  && this.senhaTXT.equals(this.senhaAdmin)) {
				FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
			}else { 
				Usuario usuarioPesquisa = this.usuarioDAO.pesquisar(this.usuarioTXT);
				if(usuarioPesquisa != null) {
					if(usuarioPesquisa.getSenha().equals(this.senhaTXT)) {
						FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
					}else {
						this.mensagem = "Usuario ou senha errada";
					}
				}else {
					this.mensagem = "Usuario inexistente.";
				}
				
			}
					
		}
		
		public void salvar() throws IOException{
			this.usuarioDAO.inserir(usuario);
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

		

}
