package com.epam.mentoring.data.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "product_income")
@Access(AccessType.FIELD)
public class ProductIncome {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "order_number")
	private Long orderNumber;

	@Column(name = "date")
//    @Temporal(value = TemporalType.DATE)
	private Date date;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "supplier_id", nullable = false)
	private Supplier supplier;

	@java.beans.ConstructorProperties({"id", "quantity", "orderNumber", "date", "product", "user", "supplier"})
	public ProductIncome(Integer id, Integer quantity, Long orderNumber, Date date, Product product, User user, Supplier supplier) {
		this.id = id;
		this.quantity = quantity;
		this.orderNumber = orderNumber;
		this.date = date;
		this.product = product;
		this.user = user;
		this.supplier = supplier;
	}

	public ProductIncome() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (orderNumber ^ (orderNumber >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ProductIncome other = (ProductIncome) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (orderNumber != other.orderNumber)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		if (supplier == null) {
			if (other.supplier != null)
				return false;
		} else if (!supplier.equals(other.supplier))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProductIncome [id=" + id + ", product=" + product + ", quantity=" + quantity + ", orderNumber="
				+ orderNumber + ", date=" + date + ", user=" + user + ", supplier=" + supplier + "]";
	}


	public Integer getId() {
		return this.id;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public Long getOrderNumber() {
		return this.orderNumber;
	}

	public Date getDate() {
		return this.date;
	}

	public Product getProduct() {
		return this.product;
	}

	public User getUser() {
		return this.user;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
