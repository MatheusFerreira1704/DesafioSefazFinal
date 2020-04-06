package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Usuario;
/*
 * @Author Matheus F. Silva
 * 
 * Classe criada para implementar a interface
 * onde será passado todos os metodos.
 */

public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManager ent;

	// Construtor vai receber a conexão para executar
	public UsuarioDAOImpl(EntityManager ent) {
		this.ent = ent;
	}

	@Override
	public boolean inserir(Usuario usuario) {

		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		/*
		 * Foi utilizado o merge no inserir, pq se verificar que o objeto não existe ele
		 * inseri, e caso exista, ele vai editar.
		 */
		ent.merge(usuario);
		tx.commit();

		return true;
	}

	@Override
	public void remover(Usuario usuario) {
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.remove(usuario);
		tx.commit();

	}

	@Override
	public void alterar(Usuario usuario) {
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.merge(usuario);
		tx.commit();

	}

	@Override
	public Usuario pesquisar(String email) {
		Usuario usuario = ent.find(Usuario.class, email);

		return usuario;
	}

	/**
	 * O metodo listar todos, faz um select * from, porém com o JPA, vc faz a consulta pelo objeto direto
	 * assim from Usuario, diz que isso é o objeto usuario e não a tabela
	 */
	@Override
	public List<Usuario> listarTodos() {
		Query query = ent.createQuery("from Usuario u");

		List<Usuario> usuarios = query.getResultList();

		return usuarios;
	}

}
