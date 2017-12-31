package com.epam.mentoring.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "product_type")
public class ProductType {
	@Id
	private Integer id;
	private String name;

	@OneToMany(mappedBy = "type")
	private Collection<Product> products = new HashSet<>();

    public ProductType() {
    }

    public ProductType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", name=" + name + "]";
	}


	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Collection<Product> getProducts() {
		return this.products;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
}
