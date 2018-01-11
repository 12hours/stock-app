package com.epam.mentoring;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.data.model.dto.view.SupplierView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TestObjectData {

    public static CollectionDTO<ProductWithQuantityView> productWithQuantityViewCollectionDTO() {
        CollectionDTO<ProductWithQuantityView> dto = new CollectionDTO<>();
        dto.setLinks(new HashMap<>());
        dto.setProperties(new HashMap<>());

        Collection<ProductWithQuantityView> items = new ArrayList<ProductWithQuantityView>() {
            {
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(5), "AMD Ryzen 7 1700", BigDecimal.valueOf(325), Integer.valueOf(1)), 25));
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(9), "ASRock Z370", BigDecimal.valueOf(165), Integer.valueOf(2)), 25));
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(4), "Intel Pentium G4360", BigDecimal.valueOf(65), Integer.valueOf(1)), 50));
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(2), "Intel Core i3 8100", BigDecimal.valueOf(135), Integer.valueOf(1)), 40));
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(7), "Intel Core i5 6600K", BigDecimal.valueOf(250), Integer.valueOf(1)), 10));
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(1), "Intel Core i7 8700", BigDecimal.valueOf(360), Integer.valueOf(1)), 10));
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(3), "Nvidia GTX 1050Ti", BigDecimal.valueOf(200), Integer.valueOf(3)), 45));
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(8), "Kingston UV400 120 Gb", BigDecimal.valueOf(65), Integer.valueOf(4)), 30));
                add(new ProductWithQuantityView(new ProductView(Integer.valueOf(6), "Samsung 850 Evo 256 Gb", BigDecimal.valueOf(100), Integer.valueOf(4)), 50));
            }
        };

        dto.setItems(items);
        return dto;
    }

    public static CollectionDTO<ProductView> productViewCollectionDTO() {
        CollectionDTO<ProductView> dto = new CollectionDTO<>();
        dto.setProperties(new HashMap<String, Object>());
        dto.setLinks(new HashMap<String, Object>());

        Collection<ProductView> products = new ArrayList<ProductView>() {
            {
                add(new ProductView(Integer.valueOf(1), "Intel Core i7 8700", BigDecimal.valueOf(360), Integer.valueOf(1)));
                add(new ProductView(Integer.valueOf(2), "Intel Core i3 8100", BigDecimal.valueOf(135), Integer.valueOf(1)));
                add(new ProductView(Integer.valueOf(3), "Nvidia GTX 1050Ti", BigDecimal.valueOf(200), Integer.valueOf(3)));
                add(new ProductView(Integer.valueOf(4), "Intel Pentium G4360", BigDecimal.valueOf(65), Integer.valueOf(1)));
                add(new ProductView(Integer.valueOf(5), "AMD Ryzen 7 1700", BigDecimal.valueOf(325), Integer.valueOf(1)));
                add(new ProductView(Integer.valueOf(6), "Samsung 850 Evo 256 Gb", BigDecimal.valueOf(100), Integer.valueOf(4)));
                add(new ProductView(Integer.valueOf(7), "Intel Core i5 6600K", BigDecimal.valueOf(250), Integer.valueOf(1)));
                add(new ProductView(Integer.valueOf(8), "Kingston UV400 120 Gb", BigDecimal.valueOf(65), Integer.valueOf(4)));
                add(new ProductView(Integer.valueOf(9), "ASRock Z370", BigDecimal.valueOf(165), Integer.valueOf(2)));
            }
        };

        dto.setItems(products);
        return dto;
    }

    public static CollectionDTO<ProductTypeView> productTypeViewCollectionDTO() {
        CollectionDTO<ProductTypeView> dto = new CollectionDTO<>();
        dto.setProperties(new HashMap<String, Object>());
        dto.setLinks(new HashMap<String, Object>());

        Collection<ProductTypeView> productTypes = new ArrayList<ProductTypeView>() {
            {
                add(new ProductTypeView(Integer.valueOf(1), "CPU"));
                add(new ProductTypeView(Integer.valueOf(2), "Motherboard"));
                add(new ProductTypeView(Integer.valueOf(3), "Videocard"));
                add(new ProductTypeView(Integer.valueOf(4), "SSD"));
                add(new ProductTypeView(Integer.valueOf(5), "HDD"));
            }
        };

        dto.setItems(productTypes);
        return dto;
    }

    public static CollectionDTO<SupplierView> supplierViewCollectionDTO() {
        CollectionDTO<SupplierView> dto = new CollectionDTO<>();
        dto.setProperties(new HashMap<String, Object>());
        dto.setLinks(new HashMap<String, Object>());

        Collection<SupplierView> suppliers = new ArrayList<SupplierView>() {
            {
                add(new SupplierView(Integer.valueOf(1), "Nova Computers", ""));
                add(new SupplierView(Integer.valueOf(2), "PC World Group", ""));
                add(new SupplierView(Integer.valueOf(3), "Ultra Hardware Inc.", ""));
            }
        };

        dto.setItems(suppliers);
        return dto;
    }
}
