package com.dao;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.boot.Database;

public abstract class Dao <O> {
	
	protected Database base;
	protected Session activeSession;
	
	abstract Class<O> getDaoClass();
	
	protected Dao(){
		base = Database.getDatabase();
	}
	
	public O getById(long id){
		activeSession = base.getSession();
		activeSession.beginTransaction();
		CriteriaBuilder builder = activeSession.getCriteriaBuilder();
		CriteriaQuery<O> criteria = builder.createQuery(getDaoClass());
		Root<O> root = criteria.from(getDaoClass());
		criteria.select(root);
		criteria.where(builder.equal(root.get("id"), id));
		List<O> list = activeSession.createQuery(criteria).getResultList(); //  return null ??
		activeSession.getTransaction().commit();
		if(list.size() > 0){
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public O getById(String id){
		activeSession = base.getSession();
		activeSession.beginTransaction();
		CriteriaBuilder builder = activeSession.getCriteriaBuilder();
		CriteriaQuery<O> criteria = builder.createQuery(getDaoClass());
		Root<O> root = criteria.from(getDaoClass());
		criteria.select(root);
		criteria.where(builder.equal(root.get("id"), id));
		List<O> list = activeSession.createQuery(criteria).getResultList(); //  return null ??
		activeSession.getTransaction().commit();
		if(list.size() > 0){
			return list.get(0);
		} else {
			return null;
		}
	}
	

	public void deleteByDate(Date smaller) {
		activeSession = base.getSession();
		activeSession.beginTransaction();
		CriteriaBuilder cb = base.getSession().getCriteriaBuilder();
	    CriteriaDelete<O> delete = cb.createCriteriaDelete(this.getDaoClass());
	    Root<O> e = delete.from(this.getDaoClass());
	    delete.where(cb.lessThan(e.get("id"), smaller));
		activeSession.getTransaction().commit();
		closeSession();		
	}
	
	
	public List<O> getAll(){
		activeSession = base.getSession();
		activeSession.beginTransaction();
		// Deprecated in Favour of JPA Query
		CriteriaBuilder builder = activeSession.getCriteriaBuilder();
		CriteriaQuery<O> criteria = builder.createQuery(getDaoClass());
		Root<O> root = criteria.from(getDaoClass());
		criteria.select(root);
		List<O> list = activeSession.createQuery(criteria).getResultList();
		activeSession.getTransaction().commit();
		return list;
	}

	public void batchSave(List<O> os) {	
		activeSession = base.getSession();
		activeSession.beginTransaction();
		for(O o : os) {
			activeSession.saveOrUpdate(o);
		}
		activeSession.getTransaction().commit();
		closeSession();
	}
	
	public void save (O o){
		activeSession = base.getSession();
		activeSession.beginTransaction();
		activeSession.saveOrUpdate(o);
		activeSession.getTransaction().commit();
		closeSession();
	}
	
	public void delete(O o){
		activeSession = base.getSession();
		activeSession.beginTransaction();
		activeSession.delete(o);
		activeSession.getTransaction().commit();
		closeSession();
	}
	
	public void closeSession(){
		if(activeSession.isConnected()){
			activeSession.close();
		}
	}

}
