package br.edu.ifpb.si.pd.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistenceUtil {

    private static EntityManagerFactory emf;

    static {
        try {
    		emf = Persistence.createEntityManagerFactory("blog");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    public static EntityManager getEntityManager() {
    	return emf.createEntityManager();
    }
    

}