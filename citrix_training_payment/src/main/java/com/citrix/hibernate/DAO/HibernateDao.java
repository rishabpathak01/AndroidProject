package com.citrix.hibernate.dao;
 
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * @see SessionFactory
 */
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class HibernateDao<E, K extends Serializable> implements GenericDao<E, K> {
 
    private SessionFactory sessionFactory;
    protected Class<? extends E> daoType;
 
    @SuppressWarnings("unchecked")
	public HibernateDao() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }
 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }
 
    @Override
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }
 
    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
	@Override
    public E find(K key) {
        return (E) currentSession().get(daoType, key);
    }
 
    @SuppressWarnings("unchecked")
	@Override
    public List<E> list() {
        return currentSession().createCriteria(daoType).list();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<E> executeQuery(String query,int page,int pageSize) {
		Query q= currentSession().createQuery(query);
		if(page>=0 && pageSize > 0){
			int lastPage = (page-1)*pageSize;
			q.setMaxResults(pageSize).setFirstResult(lastPage);
			
		}
		return q.list();
	}

	@Override
	public Long count(String query) {
		Query q= currentSession().createQuery(query);
		Long cnt = (Long)q.uniqueResult();
		return cnt;
	}

	@Override
	public Long count() {
		Criteria criteriaCount = currentSession().createCriteria(daoType);
		criteriaCount.setProjection(Projections.rowCount());
		Long count = (Long) criteriaCount.uniqueResult();
		return count;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<E> list(List<Criterion> list){
		Criteria criteria = currentSession().createCriteria(daoType);
		for (Criterion criterion : list) {
			criteria.add(criterion);
		}
		return criteria.list();
	}
}