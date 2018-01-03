package com.epam.mentoring.service.jpa.dao;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.Field;
import java.util.List;

public abstract class AbstractDao<T> {

    private static Logger log = LoggerFactory.getLogger(AbstractDao.class.getName());

    protected Class<T> entityClass;

    private EntityManagerFactory emf;

    public AbstractDao(Class<T> entityClass, EntityManagerFactory emf) {
        this.entityClass = entityClass;
        this.emf = emf;
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
            try {
                em.close();
            } catch (Exception e) {
                log.warn("Can not close EntityManager: {}", e);
            }
        }
    }

    protected T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    protected void remove(Object entityId) {
        EntityManager em = getEntityManager();
        T entity = em.find(entityClass, entityId);
        if (entity != null) {
            em.getTransaction().begin();
            try {
                em.remove(entity);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                try {
                    em.close();
                } catch (Exception e) {
                    log.warn("Can not close EntityManager: {}", e);
                }
            }
        }
    }

    protected T find(Object id) {
        EntityManager em = getEntityManager();
        T entity = em.find(entityClass, id);
        em.close();
        return entity;
    }

    /**
     * Finds object by id, initialize, extracts and returns requested field
     * @param id requested object id
     * @param fieldName name of the field to extract
     * @return {@code Object} laying in requested field
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    protected Object findAndFetchField(Object id, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        EntityManager em = getEntityManager();
        T entity = em.find(entityClass, id);
        Hibernate.initialize(entity);
        Field field = entity.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object requestedField = field.get(entity);
        em.close();
        return requestedField;
    }

    protected void update(Object id, T updatedEntity) {
        T persistedEntity = find(id);

        if (persistedEntity != null) {
            EntityManager em = getEntityManager();
            try {
                em.getTransaction().begin();
                BeanUtils.copyProperties(updatedEntity, persistedEntity);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                try {
                    em.close();
                } catch (Exception e) {
                    log.warn("Can not close EntityManager: {}", e);
                }
            }
        }
    }

    protected List<T> findAll() {
        EntityManager em = getEntityManager();
        List<T> list = null;
        try {
            CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(
                    entityClass);
            cq.select(cq.from(entityClass));
            list = em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                em.close();
            } catch (Exception e) {
                log.warn("Can not close EntityManager: {}", e);
            }
        }

        return list;
    }

    protected int count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));
        return getEntityManager().createQuery(cq).getSingleResult().intValue();
    }

}
