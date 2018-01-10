package com.epam.mentoring.approutes.processors.supplier;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.mapstruct.ProductIncomeMapper;
import com.epam.mentoring.data.model.dto.mapstruct.SupplierMapper;
import com.epam.mentoring.data.model.dto.view.ProductIncomeView;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.stream.Collectors;

public class GetIncomesOfSupplierProcessor implements Processor {

    private ProductIncomeMapper productIncomeMapper = Mappers.getMapper(ProductIncomeMapper.class);

    private SupplierService supplierService;

    public GetIncomesOfSupplierProcessor(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Collection<ProductIncomeView> productIncomes = supplierService.getAllIncomesOfSupplier(id).stream()
                .map(productIncome -> productIncomeMapper.productIncomeToProductIncomeView(productIncome))
                .collect(Collectors.toList());

        exchange.getIn().setBody(new CollectionDTO<>(productIncomes));
    }
}
