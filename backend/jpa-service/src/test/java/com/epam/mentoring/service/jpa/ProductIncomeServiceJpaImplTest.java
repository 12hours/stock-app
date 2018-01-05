package com.epam.mentoring.service.jpa;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.User;
import com.epam.mentoring.data.model.dto.DTOUtils;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ProductIncomeServiceJpaImplTest {

    private EntityManagerFactory emf;

    private ProductIncomeService service;

    @Before
    public void before() {
        emf = Persistence.createEntityManagerFactory("PU");
        service = new ProductIncomeServiceJpaImpl(emf);
    }

    @After
    public void after() {
        emf.close();
    }

    @Test
    public void findAllProductIncomesTest() {
        List<ProductIncome> allProductIncomes = service.getAllProductIncomes();
        assertNotNull(allProductIncomes);
        assertEquals(12, allProductIncomes.size());
    }

    @Test
    public void findProductIncomeByIdTest() {
        ProductIncome productIncomeById = service.getProductIncomeById(4);
        assertNotNull(productIncomeById);
        assertEquals(Integer.valueOf(50), productIncomeById.getQuantity());
        assertEquals(Integer.valueOf(4), productIncomeById.getProduct().getId());
        assertEquals(Integer.valueOf(1), productIncomeById.getSupplier().getId());
        assertEquals(Integer.valueOf(1), productIncomeById.getUser().getId());
        assertEquals(Long.valueOf(10004), productIncomeById.getOrderNumber());

        Calendar date = Calendar.getInstance();
        date.setTime(productIncomeById.getDate());
        assertEquals(2017, date.get(date.YEAR));
        assertEquals(9, date.get(date.MONTH));
        assertEquals(27, date.get(date.DAY_OF_MONTH));
    }

    @Test
    public void saveProductIncomeTest() {
        ProductIncome productIncome = new ProductIncome(null, Integer.valueOf(123), 98789L, new Date(System.currentTimeMillis()),
                                                                                new Product(1, null, null, null),
                                                                                new User(1, null, null, false),
                                                                                new Supplier(2, null, null));
        Integer id = service.saveProductIncome(productIncome);
        ProductIncome productIncomeById = service.getProductIncomeById(id);

        checkProductIncomesEquality(productIncome, productIncomeById);
    }

    @Test
    public void saveProductIncomeFormTest() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date(System.currentTimeMillis()));
        ProductIncomeForm productIncomeForm = new ProductIncomeForm(98789L, currentDate.getTime(), Integer.valueOf(123), 1, 2, 2);
        Integer id = service.saveProductIncome(productIncomeForm);

        ProductIncome productIncomeById = service.getProductIncomeById(id);

        checkProductIncomesEquality(DTOUtils.map(productIncomeForm, ProductIncome.class), productIncomeById);
    }

    @Test
    public void updateProductIncomeTest() {
        ProductIncome productIncome = service.getProductIncomeById(6);
        productIncome.setSupplier(new Supplier(3, null, null));
        productIncome.setUser(new User(2, null, null, false));
        productIncome.setQuantity(Integer.valueOf(639));
        productIncome.setOrderNumber(Long.valueOf(88888L));
        productIncome.setDate(new Date(System.currentTimeMillis()));
        productIncome.setProduct(new Product(4, null, null, null));
        service.updateProductIncome(productIncome);

        checkProductIncomesEquality(productIncome, service.getProductIncomeById(6));
    }


    @Test
    public void updateProductIncomeFormTest() {
        ProductIncome productIncome = new ProductIncome();
        productIncome.setSupplier(new Supplier(3, null, null));
        productIncome.setUser(new User(2, null, null, false));
        productIncome.setQuantity(Integer.valueOf(639));
        productIncome.setOrderNumber(Long.valueOf(88888L));
        productIncome.setDate(new Date(System.currentTimeMillis()));
        productIncome.setProduct(new Product(4, null, null, null));
        ProductIncomeForm productIncomeForm = DTOUtils.map(productIncome, ProductIncomeForm.class);

        service.updateProductIncome(5, productIncomeForm);
        checkProductIncomesEquality(productIncome, service.getProductIncomeById(5));
    }

    @Test
    public void deleteProductIncomeTest() {
        assertNotNull(service.getProductIncomeById(5));
        service.deleteProductIncome(5);
        assertNull(service.getProductIncomeById(5));
    }

    private void checkProductIncomesEquality(ProductIncome piA, ProductIncome piB) {

        assertNotNull(piA);
        assertNotNull(piB);

        assertEquals(piB.getQuantity(),         piA.getQuantity());
        assertEquals(piB.getOrderNumber(),      piA.getOrderNumber());
        assertEquals(piB.getProduct().getId(),  piA.getProduct().getId());
        assertEquals(piB.getUser().getId(),     piA.getUser().getId());
        assertEquals(piB.getSupplier().getId(), piA.getSupplier().getId());

        Calendar dateA = Calendar.getInstance();
        dateA.setTime(piA.getDate());

        Calendar dateB = Calendar.getInstance();
        dateB.setTime(piB.getDate());

        assertEquals(dateA.get(dateA.YEAR),         dateB.get(dateB.YEAR));
        assertEquals(dateA.get(dateA.MONTH),        dateB.get(dateB.MONTH));
        assertEquals(dateA.get(dateA.DAY_OF_MONTH), dateB.get(dateB.DAY_OF_MONTH));
    }
}