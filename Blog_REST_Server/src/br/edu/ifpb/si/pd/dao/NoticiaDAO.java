package br.edu.ifpb.si.pd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.edu.ifpb.si.pd.model.Noticia;

public class NoticiaDAO {
	private EntityManager em;
	
	public NoticiaDAO(){
		this.em = PersistenceUtil.getEntityManager();
	}
	
	public void begin(){
		this.em.getTransaction().begin();
	}
	
	public void commit(){
		this.em.getTransaction().commit();
	}

	public void persist(Noticia n){
		this.em.persist(n);
	}
	
	public void update(Noticia n){
		this.em.merge(n);
	}
	
	public void remove(Noticia n){
		this.em.remove(n);
	}
	
	public Noticia findById(int id) throws NoResultException{
		Noticia n = null;
		try{
			n = (Noticia) this.em.createQuery("FROM Noticia n WHERE n.id = '"+id+"' ").getSingleResult();
		}catch(NoResultException e){
			return n;
		}
		return n;
	}
	
	public List<Noticia> findAll(){
		return (List<Noticia>) this.em.createQuery("FROM Noticia n").getResultList();
	}
}
