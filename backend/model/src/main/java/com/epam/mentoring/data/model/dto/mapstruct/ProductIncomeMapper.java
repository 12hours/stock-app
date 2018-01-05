package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.view.ProductIncomeView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProductIncomeMapper {

    @Mappings({
            @Mapping(source = "productIncome.product.id", target = "productId"),
            @Mapping(source = "productIncome.user.id", target = "userId"),
            @Mapping(source = "productIncome.supplier.id", target = "supplierId"),
//            @Mapping(target = "date", expression = ("java( ((java.sql.Date) productIncome).getDate().toLocalDate())")),
//            @Mapping(target = "links", expression = ("java( new java.util.HashMap<String, Object>())"))
    })
    ProductIncomeView productIncomeToProductIncomeView(ProductIncome productIncome);
}
