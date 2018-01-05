package com.epam.mentoring.data.model.dto.form;

import java.math.BigDecimal;

public class ProductForm {
    private String name;
    private BigDecimal price;
    private Integer productTypeId;

    @java.beans.ConstructorProperties({"name", "price", "productTypeId"})
    public ProductForm(String name, BigDecimal price, Integer productTypeId) {
        this.name = name;
        this.price = price;
        this.productTypeId = productTypeId;
    }

    public ProductForm() {
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Integer getProductTypeId() {
        return this.productTypeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductForm)) return false;
        final ProductForm other = (ProductForm) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$productTypeId = this.getProductTypeId();
        final Object other$productTypeId = other.getProductTypeId();
        if (this$productTypeId == null ? other$productTypeId != null : !this$productTypeId.equals(other$productTypeId))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $productTypeId = this.getProductTypeId();
        result = result * PRIME + ($productTypeId == null ? 43 : $productTypeId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductForm;
    }
}
