package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.data.model.dto.form.ProductForm;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.dao.DataAccessException;

import javax.ws.rs.ServerErrorException;
import java.util.HashMap;

public class SaveProductProcessor implements Processor{

    private ProductService productService;

    public SaveProductProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        ProductForm productForm = (ProductForm) exchange.getIn().getBody();
        try {
            Integer id = productService.saveProduct(productForm);
            HashMap<String, Integer> idMap = new HashMap<>();
            idMap.put("id", id);
            exchange.getIn().setBody(idMap);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new ServerErrorException("Can not save object", 500);
        }
    }
}
