package com.epam.mentoring.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name="supplier")
public class Supplier {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "details")
	private String details;

	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore
	private Collection<ProductIncome> productIncomes = new HashSet<>();

	public Supplier() {
	}

	public Supplier(Integer id, String name, String details) {
		this.id = id;
		this.name = name;
		this.details = details;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
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
		Supplier other = (Supplier) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
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
		return "Supplier [id=" + id + ", name=" + name + ", details=" + details + "]";
	}

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDetails() {
        return this.details;
    }

    public Collection<ProductIncome> getProductIncomes() {
        return this.productIncomes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setProductIncomes(Collection<ProductIncome> productIncomes) {
        this.productIncomes = productIncomes;
    }
}
