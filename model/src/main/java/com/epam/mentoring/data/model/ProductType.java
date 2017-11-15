package com.epam.mentoring.data.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductType {
	int id;
	private String typeName;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", typeName=" + typeName + "]";
	}
	
	

}
