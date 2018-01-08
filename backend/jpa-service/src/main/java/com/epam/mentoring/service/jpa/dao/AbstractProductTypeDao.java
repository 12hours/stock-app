package com.epam.mentoring.service.jpa.dao;

import com.epam.mentoring.data.model.ProductType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AbstractProductTypeDao extends AbstractDao<ProductType> {
    private final EntityManagerFactory emf;

    public AbstractProductTypeDao(EntityManagerFactory emf) {
        super(ProductType.class, emf);
        this.emf = emf;
    }

    protected ProductType findProductTypeByProductId(Integer productId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProductType> query = em.createQuery("SELECT pt FROM ProductType pt JOIN pt.products pr WHERE pr.id = ?1", ProductType.class);
        query.setParameter(1, productId);
        ProductType productType = query.getSingleResult();
        em.close();
        return productType;
    }

}
