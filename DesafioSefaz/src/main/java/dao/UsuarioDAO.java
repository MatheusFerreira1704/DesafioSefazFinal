package dao;

import java.util.List;

import entidade.Usuario;

/*
 * @author Matheus F.Silva
 * Classe abstrada criada para chamada de metodo não implementados,
 * possibilitando um baixo acoplamento.
 */
public interface UsuarioDAO {

public boolean inserir(Usuario usuario);
	
	public void remover(Usuario usuario);
	
	public void alterar(Usuario usuario);
	
	public Usuario pesquisar (String email);
	
	public List<Usuario> listarTodos();
	
	
	
	
}
