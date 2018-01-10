package com.epam.mentoring.service.jpa.dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
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

    /**
     * Merge the state of the given entity into the current persistence context.
     * Method is supposed to be used for updating entities.
     * @param entity updated entity
     * @return entity
     */
    protected T merge(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                em.close();
            } catch (Exception e) {
                log.warn("Can not close EntityManager: {}", e);
            }
        }
    }

    protected void remove(Object entityId) throws ObjectNotFoundException {
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
        } else {
            throw new ObjectNotFoundException((Serializable) entityId, "Entity with given id does not exist");
        }
    }

    protected T find(Object id) {
        EntityManager em = getEntityManager();
        T entity = em.find(entityClass, id);
        try {
            em.close();
        } catch (Exception e) {
            log.warn("Can not close EntityManager: {}", e);
        }
        return entity;
    }

    /**
     * Finds object with given id, initialize specified field
     * @param id id of object to find
     * @param fieldNames names of the fields that should be initialized in case they are lazy-fetched by default
     * @return entity object
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    protected T find(Object id, String... fieldNames) throws NoSuchFieldException, IllegalAccessException {
        EntityManager em = getEntityManager();
        T entity = em.find(entityClass, id);
        for (String fieldName: fieldNames) {
            Field field = entity.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Hibernate.initialize(field.get(entity)); // avoid LazyInitializationException
        }
        try {
            em.close();
        } catch (Exception e) {
            log.warn("Can not close EntityManager: {}", e);
        }
        return entity;
    }

    /**
     * Finds object by id. Initializes, extracts and returns requested field
     *
     * @param id        requested object id
     * @param fieldName name of the field to extract
     * @return {@code Object} laying in requested field
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    protected Object findAndFetchField(Object id, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        EntityManager em = getEntityManager();
        Object requestedField = null;
        // actually getting the initialized field
        try {
            T entity = em.find(entityClass, id);
            Field field = entity.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Hibernate.initialize(field.get(entity)); // avoid LazyInitializationException
            requestedField = field.get(entity);
            return requestedField;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                em.close();
            } catch (Exception e) {
                log.warn("Can not close EntityManager: {}", e);
            }
        }
    }

    /**
     * Updates object with given id with fields of provided object.
     *
     * <p>
     * Provided <code>dtoEntity</code> is used as simple DTO. It means, that it's fields are copied to actual persisted object.
     * Notice that this method <b>ignores</b> all {@code Collection} fields. If you need to update <b>all</b> fields
     * (including {@code Collection} fields), use {@link #merge(T) merge} method instead.
     *
     * @param id id of object to update
     * @param dtoEntity transfer object with updated fields
     */
    protected void update(Object id, T dtoEntity) {
        EntityManager em = getEntityManager();
        try {
            T persistedEntity = em.find(entityClass, id);
            if (persistedEntity == null) { /* TODO: check */ }

            // collect all fields that implement Collection interface
            String[] fieldsToIgnore = Arrays.stream(dtoEntity.getClass().getDeclaredFields())
                    .filter(field -> field.getType().isAssignableFrom(Collection.class))
                    .map(field -> field.getName())
                    .toArray(String[]::new);

            em.getTransaction().begin();
            BeanUtils.copyProperties(dtoEntity, persistedEntity, fieldsToIgnore);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception e) {
                log.error("Can not rollback: {}", e);
            }
            throw ex;
        } finally {
            try {
                em.close();
            } catch (Exception e) {
                log.warn("Can not close EntityManager: {}", e);
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
