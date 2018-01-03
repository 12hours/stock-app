package com.epam.mentoring.service.jpa.dao;

import com.epam.mentoring.service.jpa.config.EntityManagerFactoryWrapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.util.List;

public class AbstractDao<T> {

    protected Class<T> entityClass;

    private EntityManagerFactory emf;

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.emf = EntityManagerFactoryWrapper.getInstance().getEntityManagerFactory();
    }

    private EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    /**
     * Retrieves the meta-model for a certain entity.
     *
     * @return the meta-model of a certain entity.
     */
    protected EntityType<T> getMetaModel() {
        return getEntityManager().getMetamodel().entity(entityClass);
    }

    protected void persist(T entity) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    protected T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    protected void remove(Object entityId) {
        T entity = find(entityId);
        if (entity != null)
            getEntityManager().remove(entity);
    }

    protected T find(Object id) {
        EntityManager em = getEntityManager();
        T entity = em.find(entityClass, id);
//        em.close();
        return entity;
    }

    protected List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(
                entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    protected int count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));
        return getEntityManager().createQuery(cq).getSingleResult().intValue();
    }

}
