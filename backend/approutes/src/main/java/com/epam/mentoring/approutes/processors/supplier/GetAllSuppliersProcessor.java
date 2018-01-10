package com.epam.mentoring.approutes.processors.supplier;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.mapstruct.SupplierMapper;
import com.epam.mentoring.data.model.dto.view.SupplierView;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllSuppliersProcessor implements Processor {

    private SupplierMapper supplierMapper = Mappers.getMapper(SupplierMapper.class);

    private SupplierService supplierService;

    public GetAllSuppliersProcessor(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<SupplierView> suppliers = supplierService.getAllSuppliers().stream()
                                            .map(supplier -> supplierMapper.supplierToSupplierView(supplier))
                                            .collect(Collectors.toList());
        exchange.getIn().setBody(new CollectionDTO<>(suppliers));
    }
}
