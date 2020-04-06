package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/*
 * @author Matheus F.Silva
 * 
 * Classe responsavel por ser a classe fabrica do entitymanager
 * que será responsavel pelas operações de busca ou de alteração.
 * 
 */

public class JpaUtil {

	private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("DesafioSefaz");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static void close() {
        factory.close();
    }
	
}
