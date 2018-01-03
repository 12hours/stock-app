package com.epam.mentoring.data.model;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;

    @Column(name = "name")
	private String name;

    @Column(name = "price")
	private BigDecimal price;

    @Column(name = "type")
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_type_id", nullable = false, updatable = true)
	private ProductType type;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonIgnore
    private Collection<ProductIncome> productIncomes = new HashSet<>();

	public Product() {
	}

	public Product(Integer id, String name, BigDecimal price, ProductType type) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public static ProductBuilder builder() {
		return new ProductBuilder();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (price != null ? !(price.compareTo(product.price) == 0) : product.price != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (price != null ? price.hashCode() : 0);
		return result;
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

	public ProductType getType() {
		return this.type;
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

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public void setProductIncomes(Collection<ProductIncome> productIncomes) {
		this.productIncomes = productIncomes;
	}

	public String toString() {
		return "Product(id=" + this.getId() + ", name=" + this.getName() + ", price=" + this.getPrice() + ", type=" +
				this.getType() + ")";
	}

	public static class ProductBuilder {
		private Integer id;
		private String name;
		private BigDecimal price;
		private ProductType type;
		private Collection<ProductIncome> productIncomes;

		ProductBuilder() {
		}

		public Product.ProductBuilder id(Integer id) {
			this.id = id;
			return this;
		}

		public Product.ProductBuilder name(String name) {
			this.name = name;
			return this;
		}

		public Product.ProductBuilder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Product.ProductBuilder type(ProductType type) {
			this.type = type;
			return this;
		}

		public Product.ProductBuilder productIncomes(Collection<ProductIncome> productIncomes) {
			this.productIncomes = productIncomes;
			return this;
		}

		public Product build() {
			return new Product(id, name, price, type);
		}

		public String toString() {
			return "Product.ProductBuilder(id=" + this.id + ", name=" + this.name + ", price=" + this.price + ", type=" + this.type + ", productIncomes=" + this.productIncomes + ")";
		}
	}
}
