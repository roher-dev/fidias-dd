package com.fidias.database.modeler.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

import com.fidias.database.modeler.model.Model;

@Transactional
public abstract class BasicDaoImpl<E extends Model>
    implements BasicDao<E> {

    @Inject
    private SessionFactory sessionFactory;

    public BasicDaoImpl() {
        super();
    }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public boolean save(E e) {
        if (e == null) {
            return false;
        }
        this.getCurrentSession().save(e);

        return true;
    }

    @Override
    public boolean saveOrUpdate(E e) {
        return this.saveOrUpdate(e, false);
    }

    @Override
    public boolean saveOrUpdate(E e, boolean flush) {
        if (e == null) {
            return false;
        }
        this.getCurrentSession().saveOrUpdate(e);

        if (flush) {
            this.getCurrentSession().flush();
        }
        return true;
    }

    @Override
    public boolean delete(E e) {
        if (e == null) {
            return false;
        }
        this.getCurrentSession().delete(e);

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());
        criteria.add(criterion);

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E findUniqueByCriteria(Criterion criterion) {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());
        criteria.add(criterion);

        return (E) criteria.uniqueResult();
    }

    protected abstract Class<E> getEntityClass();

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll() {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll(Integer maxResults) {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());
        if (maxResults != null && maxResults > 0) {
            criteria.setMaxResults(maxResults);
        }

        return criteria.list();
    }

    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean refresh(E e) {
    	this.getCurrentSession().refresh(e);
    	
    	return true;
    }
}
