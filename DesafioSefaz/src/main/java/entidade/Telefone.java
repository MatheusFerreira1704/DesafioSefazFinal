package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * @author Matheus F.Silva SubClasse da aplicação, utilizada para trazer as
 *         informações referênte ao telefone cadastrado de cada usuário.
 *
 */
@Entity
@Table (name="TELEFONE")
public class Telefone {

	@Id
	@Column(name = "id")
	@GeneratedValue
	//@GeneratedValue(generator = "S_TELEFONE")
	//@SequenceGenerator(name = "S_TELEFONE", sequenceName = "S_TELEFONE", allocationSize = 1)
	private long id;

	@Column(name = "ddd")
	private int ddd;

	@Column(name = "numero")
	private String numero;

	@Column(name = "tipo")
	private String tipo;

	/*
	 * Aqui utilizamos o ManyToOne, para identificar que vários registros dessa
	 * entidade está relacionado com um registro da outra, neste caso o Usuario. na
	 * hora de solicitar as informações sobre o usuario, caso esse possoa mais de um
	 * telefone todos serão mostrados ao mesmo tempo.
	 */

	@ManyToOne
	@JoinColumn(name = "email_usuario", referencedColumnName = "email", nullable = false)
	private Usuario usuario;

	// metodos.

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
