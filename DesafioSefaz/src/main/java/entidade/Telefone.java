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
 *         informacoes referente ao telefone cadastrado de cada usuario.
 *
 */
@Entity
@Table (name="TELEFONE")
public class Telefone {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;

	@Column(name = "ddd")
	private int ddd;

	@Column(name = "numero")
	private String numero;

	@Column(name = "tipo")
	private String tipo;

	/*
	 * Aqui utilizamos o ManyToOne, para identificar que vários registros dessa
	 * entidade estao relacionado com um registro da outra, neste caso o Usuario. na
	 * hora de solicitar as informações sobre o usuario, caso esse possoa mais de um
	 * telefone todos serao mostrados ao mesmo tempo.
	 */

	@ManyToOne
	@JoinColumn(name = "email_usuario", referencedColumnName = "email", nullable = false)
	private Usuario usuario;


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
