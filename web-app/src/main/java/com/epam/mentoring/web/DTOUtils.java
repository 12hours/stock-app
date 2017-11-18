package com.epam.mentoring.web;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.web.forms.ProductIncomeForm;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.Provider;
import org.modelmapper.builder.ConfigurableMapExpression;
import org.modelmapper.convention.MatchingStrategies;

public class DTOUtils {
    private static final ModelMapper INSTANCE;

    static {
        INSTANCE = new ModelMapper();
        INSTANCE.addMappings(new PropertyMap<ProductIncomeForm, ProductIncome>() {
            protected void configure() {
                skip().setId(null);
                map().setDate(source.getDate());
                map().setOrderNumber(source.getOrderNumber());
                map().setQuantity(source.getQuantity());
                map().getProduct().setId(source.getProductId());
                map().getSupplier().setId(source.getSupplierId());
                map().getUser().setId(source.getUserId());
            }
        });
        INSTANCE.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        INSTANCE.getConfiguration().setAmbiguityIgnored(true);
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        return INSTANCE.map(source, targetClass);
    }

    public static ProductIncome map(ProductIncomeForm form) {
        return INSTANCE.map(form, ProductIncome.class);
    }

    public static <S, T> void mapTo(S source, T dist) {
        INSTANCE.map(source, dist);
    }
}
