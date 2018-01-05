package com.epam.mentoring.data.model.dto.view;

public class ProductWithQuantityView {
    Integer id;
    String productName;
    Integer quantity;

    @java.beans.ConstructorProperties({"id", "productName", "quantity"})
    public ProductWithQuantityView(Integer id, String productName, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
    }

    public ProductWithQuantityView() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductWithQuantityView)) return false;
        final ProductWithQuantityView other = (ProductWithQuantityView) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$productName = this.getProductName();
        final Object other$productName = other.getProductName();
        if (this$productName == null ? other$productName != null : !this$productName.equals(other$productName))
            return false;
        final Object this$quantity = this.getQuantity();
        final Object other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !this$quantity.equals(other$quantity)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $productName = this.getProductName();
        result = result * PRIME + ($productName == null ? 43 : $productName.hashCode());
        final Object $quantity = this.getQuantity();
        result = result * PRIME + ($quantity == null ? 43 : $quantity.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductWithQuantityView;
    }
}
