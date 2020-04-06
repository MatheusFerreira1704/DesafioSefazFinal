package entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Matheus F.Silva Classe principal da aplicação, onde será armazenada
 *         as informações de cada usuario.
 */

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Column(name = "nome")
	private String nome;

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	/*
	 * @OneToMany, esta sendo utilizado para informar que um registro desta
	 * entidade, está relacionada com varios outros registros da outra entidade, que
	 * no neste caso é a entenidade Telefone.
	 */
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Telefone> telefones;

	// metodos
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

}
