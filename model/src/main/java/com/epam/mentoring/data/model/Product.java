package com.epam.mentoring.data.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product {
	private Integer id;
	private String name;
	private BigDecimal price;
	private ProductType type;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (price != null ? !(price.compareTo(product.price) == 0) : product.price != null) return false;
		return type != null ? type.equals(product.type) : product.type == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}
}
