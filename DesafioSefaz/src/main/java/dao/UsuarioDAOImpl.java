package dao;

import java.util.ArrayList;
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
import util1.JpaUtil;

public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManager ent;
	private EntityTransaction tx;

	@Override
	public boolean salvar(Usuario usuario) {
		try {
			this.ent = JpaUtil.getEntityManager();
			tx = ent.getTransaction();
			tx.begin();
			ent.merge(usuario);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
				System.out.println(e.getMessage());
			}
		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}
		return false;
	}

	@Override
	public void remover(String emailUsuario) {
		try {
			this.ent = JpaUtil.getEntityManager();
			Usuario user = ent.find(Usuario.class, emailUsuario);
			tx = ent.getTransaction();
			tx.begin();
			ent.remove(user);
			tx.commit();

		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
			}

		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}

	}

	// N�o utilizado, devido ao merge
	@Override
	public void alterar(Usuario usuario) {

	}

	@Override
	public Usuario pesquisar(String email) {
		this.ent = JpaUtil.getEntityManager();
		Usuario usuarioPesquisar = ent.find(Usuario.class, email);
		return usuarioPesquisar;
	}

	/**
	 * O metodo listar todos, faz um select * from, porém com o JPA, vc faz a
	 * consulta pelo objeto direto assim from Usuario, diz que isso é o objeto
	 * usuario e não a tabela
	 */
	@Override
	public List<Usuario> listarTodos() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		this.ent = JpaUtil.getEntityManager();

		Query query = ent.createQuery("from Usuario u");

		usuarios = query.getResultList();

		return usuarios;
	}

}
