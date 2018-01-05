package com.epam.mentoring.data.model.dto.view;

import java.math.BigDecimal;

public class ProductView {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer productTypeId;

    @java.beans.ConstructorProperties({"id", "name", "price", "productTypeId"})
    public ProductView(Integer id, String name, BigDecimal price, Integer productTypeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productTypeId = productTypeId;
    }

    public ProductView() {
    }

    public Integer getId() {
        return this.id;
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

    public void setId(Integer id) {
        this.id = id;
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
        if (!(o instanceof ProductView)) return false;
        final ProductView other = (ProductView) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
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
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $productTypeId = this.getProductTypeId();
        result = result * PRIME + ($productTypeId == null ? 43 : $productTypeId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductView;
    }

    public String toString() {
        return "ProductView(id=" + this.getId() + ", name=" + this.getName() + ", price=" + this.getPrice() + ", productTypeId=" + this.getProductTypeId() + ")";
    }
}
