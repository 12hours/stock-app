package com.epam.mentoring.approutes.constants;

public class RouteNames {

    public static final String MAIN_ROUTE = "direct-vm:mainRoute";
    public static final String MAIN_ROUTE_ID = "mainRoute";

    public static final String PRODUCT_ROUTE = "cxfrs:bean:productRestService?bindingStyle=SimpleConsumer";
    public static final String PRODUCT_ROUTE_ID = "productRoute";

    public static final String SUPPLIER_ROUTE = "cxfrs:bean:supplierRestService?bindingStyle=SimpleConsumer";;
    public static final String SUPPLIER_ROUTE_ID = "supplierRoute";

    public static final String STOCK_ROUTE = "cxfrs:bean:stockRestService?bindingStyle=SimpleConsumer";
    public static final String STOCK_ROUTE_ID = "stockRoute";

    public static final String PRODUCT_TYPE_ROUTE = "cxfrs:bean:productTypeRestService?bindingStyle=SimpleConsumer";
    public static final String PRODUCT_TYPE_ROUTE_ID = "productTypeRoute";

    public static final String PRODUCT_INCOME_ROUTE = "cxfrs:bean:productIncomeRestService?bindingStyle=SimpleConsumer";
    public static final String PRODUCT_INCOME_ROUTE_ID = "productIncomeRoute";

    public static final String NOT_FOUND_ROUTE = "direct-vm:notFound";
    public static final String NOT_FOUND_ID = "notFound";

    public static final String EXCEPTION_ROUTE = "direct-vm:exception";
    public static final String EXCEPTION_ROUTE_ID = "exception";

}
