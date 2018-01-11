package com.epam.mentoring.data.model.dto.form;


import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProductIncomeForm {
    @NotNull
    private Long orderNumber;
    private Date date;
    private Integer quantity;
    @NotNull
    private Integer productId;
    private Integer supplierId;
    private Integer userId;

    @java.beans.ConstructorProperties({"orderNumber", "date", "quantity", "productId", "supplierId", "userId"})
    public ProductIncomeForm(Long orderNumber, Date date, Integer quantity, Integer productId, Integer supplierId, Integer userId) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.quantity = quantity;
        this.productId = productId;
        this.supplierId = supplierId;
        this.userId = userId;
    }

    public ProductIncomeForm() {
    }

    public static ProductIncomeFormBuilder builder() {
        return new ProductIncomeFormBuilder();
    }

    public Long getOrderNumber() {
        return this.orderNumber;
    }

    public Date getDate() {
        return this.date;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public Integer getSupplierId() {
        return this.supplierId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductIncomeForm)) return false;
        final ProductIncomeForm other = (ProductIncomeForm) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$orderNumber = this.getOrderNumber();
        final Object other$orderNumber = other.getOrderNumber();
        if (this$orderNumber == null ? other$orderNumber != null : !this$orderNumber.equals(other$orderNumber))
            return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        final Object this$quantity = this.getQuantity();
        final Object other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !this$quantity.equals(other$quantity)) return false;
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) return false;
        final Object this$supplierId = this.getSupplierId();
        final Object other$supplierId = other.getSupplierId();
        if (this$supplierId == null ? other$supplierId != null : !this$supplierId.equals(other$supplierId))
            return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $orderNumber = this.getOrderNumber();
        result = result * PRIME + ($orderNumber == null ? 43 : $orderNumber.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $quantity = this.getQuantity();
        result = result * PRIME + ($quantity == null ? 43 : $quantity.hashCode());
        final Object $productId = this.getProductId();
        result = result * PRIME + ($productId == null ? 43 : $productId.hashCode());
        final Object $supplierId = this.getSupplierId();
        result = result * PRIME + ($supplierId == null ? 43 : $supplierId.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductIncomeForm;
    }

    public String toString() {
        return "ProductIncomeForm(orderNumber=" + this.getOrderNumber() + ", date=" + this.getDate() + ", quantity=" + this.getQuantity() + ", productId=" + this.getProductId() + ", supplierId=" + this.getSupplierId() + ", userId=" + this.getUserId() + ")";
    }

    public static class ProductIncomeFormBuilder {
        private Long orderNumber;
        private Date date;
        private Integer quantity;
        private Integer productId;
        private Integer supplierId;
        private Integer userId;

        ProductIncomeFormBuilder() {
        }

        public ProductIncomeForm.ProductIncomeFormBuilder orderNumber(Long orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        public ProductIncomeForm.ProductIncomeFormBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public ProductIncomeForm.ProductIncomeFormBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductIncomeForm.ProductIncomeFormBuilder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public ProductIncomeForm.ProductIncomeFormBuilder supplierId(Integer supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public ProductIncomeForm.ProductIncomeFormBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public ProductIncomeForm build() {
            return new ProductIncomeForm(orderNumber, date, quantity, productId, supplierId, userId);
        }

        public String toString() {
            return "ProductIncomeForm.ProductIncomeFormBuilder(orderNumber=" + this.orderNumber + ", date=" + this.date + ", quantity=" + this.quantity + ", productId=" + this.productId + ", supplierId=" + this.supplierId + ", userId=" + this.userId + ")";
        }
    }
}

