package com.globoforce.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDao {

	EntityManager em;

	public EntityManager getEntityManager() {
		if(em == null ){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			em = emf.createEntityManager();
			
		}
		return em;

	}

	public void commitEntityManager() {
		if (em != null) {
			em.getTransaction().commit();
		}
	}
	
	public void closeEntityManager() {
		if (em != null) {
			em.getTransaction().commit();
			em.close();
		}
	}
}
