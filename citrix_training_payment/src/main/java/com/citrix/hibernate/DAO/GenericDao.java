package com.citrix.hibernate.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

public interface GenericDao<E, K> {

	void add(E entity);
	
	void update(E entity);
	
	void remove(E entity);
	
	E find(K key);
    
	List<E> list();
	
	List<E> executeQuery(String query,int page,int pageSize);
	
	Long count(String query);
	
	Long count();

	List<E> list(List<Criterion> list);
	
}

