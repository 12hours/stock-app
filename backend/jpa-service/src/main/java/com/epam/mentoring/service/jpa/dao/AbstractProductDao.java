package com.epam.mentoring.service.jpa.dao;

import com.epam.mentoring.data.dao.ProductDao;
import com.epam.mentoring.data.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractProductDao extends AbstractDao<Product> {

    private EntityManagerFactory emf;

    public AbstractProductDao(EntityManagerFactory emf) {
        super(Product.class, emf);
        this.emf = emf;
    }

    protected Map<Product, Integer> getAllProductsWithProductIncomesMap(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p, SUM(pi.quantity) FROM Product AS p JOIN p.productIncomes pi GROUP BY p");
        List<Object[]> resultList = query.getResultList();

        HashMap<Product, Integer> productIntegerHashMap = new HashMap<>();
        for (Object[] item : resultList) {
            productIntegerHashMap.put((Product)item[0], ((Long)item[1]).intValue());
        }
        em.close();
        return productIntegerHashMap;
    }


}
