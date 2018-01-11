package com.epam.mentoring.web.client;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.data.model.dto.view.SupplierView;

public interface SupplierServiceClient {

    CollectionDTO<SupplierView> findAllSuppliers();

    Integer saveSupplier(SupplierForm supplierForm);

}
