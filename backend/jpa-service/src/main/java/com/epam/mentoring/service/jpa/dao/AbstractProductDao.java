package com.epam.mentoring.service.jpa.dao;

import com.epam.mentoring.data.dao.ProductDao;
import com.epam.mentoring.data.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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

    protected Map<Product, Integer> getAllProductsWithProductIncomesMap(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p, SUM(pi.quantity) FROM Product p JOIN p.productIncomes pi GROUP BY p");
        List<Object[]> resultList = query.getResultList();

        HashMap<Product, Integer> productQuantityHashMap = new HashMap<>();
        for (Object[] item : resultList) {
            productQuantityHashMap.put((Product)item[0], ((Long)item[1]).intValue());
        }
        try {
            em.close();
        } catch (Exception e) {
            log.warn("Can not close EntityManager: {}", e);
        }
        return productQuantityHashMap;
    }


}
