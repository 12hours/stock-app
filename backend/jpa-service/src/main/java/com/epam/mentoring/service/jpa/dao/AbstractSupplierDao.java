package com.epam.mentoring.service.jpa.dao;

import com.epam.mentoring.data.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AbstractSupplierDao extends AbstractDao<Supplier> {

    private static Logger log = LoggerFactory.getLogger(AbstractSupplierDao.class.getName());

    private EntityManagerFactory emf;

    public AbstractSupplierDao(EntityManagerFactory emf) {
        super(Supplier.class, emf);
        this.emf = emf;
    }

    // TODO: maybe use findAndFetchField instead??
    protected Supplier findSupplierOfProductIncome(Integer productIncomeId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Supplier> query = em.createQuery("SELECT s FROM Supplier s JOIN s.productIncomes pi WHERE pi.id = ?1", Supplier.class);
            query.setParameter(1, productIncomeId);
            Supplier supplier = query.getSingleResult();
            return supplier;
        } catch (NoResultException e) {
            return null;
        } finally {
            try {
                em.close();
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("Can not close entity manager: {}",e);
            }
        }
    }
}
