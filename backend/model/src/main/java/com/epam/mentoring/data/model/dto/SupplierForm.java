package com.epam.mentoring.data.model.dto;


import javax.validation.constraints.NotNull;

public class SupplierForm {
    @NotNull
    private String name;
    @NotNull
    private String details;

    @java.beans.ConstructorProperties({"name", "details"})
    public SupplierForm(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public SupplierForm() {
    }

    public String getName() {
        return this.name;
    }

    public String getDetails() {
        return this.details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SupplierForm)) return false;
        final SupplierForm other = (SupplierForm) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$details = this.getDetails();
        final Object other$details = other.getDetails();
        if (this$details == null ? other$details != null : !this$details.equals(other$details)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $details = this.getDetails();
        result = result * PRIME + ($details == null ? 43 : $details.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof SupplierForm;
    }
}
