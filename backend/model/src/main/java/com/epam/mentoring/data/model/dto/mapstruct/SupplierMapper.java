package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.view.SupplierView;
import org.mapstruct.Mapper;

@Mapper
public interface SupplierMapper {

    SupplierView supplierToSupplierView(Supplier supplier);
}
