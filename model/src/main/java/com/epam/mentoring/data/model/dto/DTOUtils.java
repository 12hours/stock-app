package com.epam.mentoring.data.model.dto;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

public class DTOUtils {
    private static final ModelMapper INSTANCE;

    static {
        INSTANCE = new ModelMapper();
        INSTANCE.addMappings(new PropertyMap<ProductIncomeForm, ProductIncome>() {
            @Override
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

        INSTANCE.addMappings(new PropertyMap<ProductIncome, ProductIncomeForm>() {
            @Override
            protected void configure() {
                map().setProductId(source.getProduct().getId());
                map().setDate(source.getDate());
                map().setOrderNumber(source.getOrderNumber());
                map().setQuantity(source.getQuantity());
                map().setSupplierId(source.getSupplier().getId());
                map().setUserId(source.getUser().getId());
            }
        });

        INSTANCE.addMappings(new PropertyMap<ProductTypeForm, ProductType>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setName(source.getName());
            }
        });

        INSTANCE.addMappings(new PropertyMap<ProductForm, Product>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setPrice(source.getPrice());
                map().setName(source.getName());
                map().getType().setId(source.getProductTypeId());
            }
        });

        INSTANCE.addMappings(new PropertyMap<SupplierForm, Supplier>() {
             @Override
             protected void configure() {
                skip().setId(null);
                map().setName(source.getName());
                map().setDetails(source.getDetails());
             }
        });

        INSTANCE.addMappings(new PropertyMap<Product, ProductView>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setPrice(source.getPrice());
                map().setProductTypeId(source.getType().getId());
            }
        });
        INSTANCE.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        INSTANCE.getConfiguration().setAmbiguityIgnored(true);
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        return INSTANCE.map(source, targetClass);
    }

    public static <S, T> void mapTo(S source, T dist) {
        INSTANCE.map(source, dist);
    }
}
