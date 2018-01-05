package com.epam.mentoring.approutes.constants;

import com.epam.mentoring.rest.controllers.ProductRestController;

import javax.ws.rs.POST;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

public class Headers {

    public static final String OPERATION = "operationName";
    public static final String ID = "id";

    public static final String GET_ALL = "getAllProducts";
    public static final String GET = Arrays.stream(ProductRestController.class.getMethods()).filter(m -> m.isAnnotationPresent(javax.ws.rs.GET.class) && (m.getParameterCount() == 1)).findAny().get().getName();
    public static final String GET_BY_ID = "getProductById";
    public static final String POST = "postProduct";
    public static final String DELETE = "deleteProduct";
    public static final String UPDATE = "update";

    public static final String STATUS = "status";


    public static final String PRODUCT_GET_ALL = "getAllProducts";
    public static final String PRODUCT_GET_BY_ID = "getProductById";
    public static final String PRODUCT_POST = "postProduct";
    public static final String PRODUCT_DELETE = "deleteProduct";
    public static final String PRODUCT_GET_ALL_WITH_QAUNT = "getAllProductsWithQuantities";

    public static final String PRODUCT_TYPE_GET_ALL = "getAllProductTypes";
    public static final String PRODUCT_TYPE_GET_BY_ID = "getProductTypeById";
    public static final String PRODUCT_TYPE_POST = "postProductType";
    public static final String PRODUCT_TYPE_DELETE = "deleteProductType";
    public static final String PRODUCT_TYPE_PRODUCTS = "getProductsOfType";


    public static final String PRODUCT_INCOME_GET_ALL = "getAllProductIncomes";
    public static final String PRODUCT_INCOME_GET_BY_ID = "getProductIncomeById";
    public static final String PRODUCT_INCOME_POST = "postProductIncome";
    public static final String PRODUCT_INCOME_DELETE = "deleteProductIncome";

    public static final String STOCK_LIST = "getStockList";

    public static final String SUPPLIER_GET_ALL = "getAllSuppliers";
    public static final String SUPPLIER_GET_BY_ID = "getSupplierById";
    public static final String SUPPLIER_POST = "postSupplier";
    public static final String SUPPLIER_DELETE = "deleteSupplier";
    public static final String SUPPLIER_INCOMES = "getProductIncomesOfSupplier";
}
