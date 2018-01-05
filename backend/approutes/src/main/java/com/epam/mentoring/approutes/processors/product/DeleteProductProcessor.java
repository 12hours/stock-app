package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class DeleteProductProcessor implements Processor {
    private ProductService productService;

    public DeleteProductProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        try {
            productService.deleteProductById(id);
        } catch (DataRetrievalFailureException e) {
            throw new NotFoundException("Object with given id not found");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
