package com.epam.mentoring.data.model.dto.form;

import javax.validation.constraints.NotNull;

public class ProductTypeForm {
    @NotNull
    private String name;

    @java.beans.ConstructorProperties({"name"})
    public ProductTypeForm(String name) {
        this.name = name;
    }

    public ProductTypeForm() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductTypeForm)) return false;
        final ProductTypeForm other = (ProductTypeForm) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductTypeForm;
    }
}
