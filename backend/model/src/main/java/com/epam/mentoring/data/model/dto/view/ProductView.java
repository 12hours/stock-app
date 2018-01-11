package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductView {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer productTypeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductView that = (ProductView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                (price.compareTo(that.price) == 0) &&
                Objects.equals(productTypeId, that.productTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, price, productTypeId);
    }
}
