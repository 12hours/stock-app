package com.epam.mentoring.service.jpa.dao;

import com.epam.mentoring.data.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractProductDao extends AbstractDao<Product> {

    private static Logger log = LoggerFactory.getLogger(AbstractProductDao.class);

    private EntityManagerFactory emf;

    public AbstractProductDao(EntityManagerFactory emf) {
        super(Product.class, emf);
        this.emf = emf;
    }

    protected Map<Product, Integer> getAllProductsWithQuantitiesMap() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p, SUM(pi.quantity) FROM Product p JOIN p.productIncomes pi GROUP BY p");
        List<Object[]> resultList = query.getResultList();

        HashMap<Product, Integer> productQuantityHashMap = new HashMap<>();
        for (Object[] item : resultList) {
            productQuantityHashMap.put((Product) item[0], ((Long) item[1]).intValue());
        }
        try {
            em.close();
        } catch (Exception e) {
            log.warn("Can not close EntityManager: {}", e);
        }
        return productQuantityHashMap;
    }

//    TODO: exception handling
    protected Map<Product, Integer> getSingleProductWithQuantityMap(Integer id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p, SUM(pi.quantity) FROM Product p JOIN p.productIncomes pi WHERE p.id = ?1");
        query.setParameter(1, id);
        Object[] result = (Object[]) query.getSingleResult();
        Map<Product, Integer> productQuantityMap = new HashMap<>();
        productQuantityMap.put((Product) result[0], ((Long) result[1]).intValue());
        return productQuantityMap;
    }

    //    TODO: exception handling
    protected Product findProductOfProductIncome(Integer productIncomeId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p JOIN p.productIncomes pi WHERE pi.id = ?1", Product.class);
        query.setParameter(1, productIncomeId);
        Product product = query.getSingleResult();
        return product;
    }

}
