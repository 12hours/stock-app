package com.epam.mentoring.data.util.mappers;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductResultSetExtractor implements ResultSetExtractor<List<Product>> {

    @Value("${product.column.id}")
    private String PRODUCT_ID_COL;

    @Value("${product.column.price}")
    private String PRODUCT_PRICE_COL;

    @Value("${product.column.name}")
    private String PRODUCT_NAME_COL;

    @Value("${product_type.column.id}")
    private String PRODUCT_TYPE_ID_COL;

    @Value("${product_type.column.name}")
    private String PRODUCT_TYPE_NAME_COL;

    @Override
    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt(PRODUCT_ID_COL));
            product.setPrice(rs.getBigDecimal(PRODUCT_PRICE_COL));
            product.setProductName(rs.getString(PRODUCT_NAME_COL));
            ProductType productType = new ProductType();
            productType.setId(rs.getInt(PRODUCT_TYPE_ID_COL));
            productType.setName(rs.getString(PRODUCT_TYPE_NAME_COL));
            product.setType(productType);
            products.add(product);
        }
        return products;
    }
}
