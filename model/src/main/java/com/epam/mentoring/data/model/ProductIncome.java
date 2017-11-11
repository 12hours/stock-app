package com.epam.mentoring.data.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductIncome {
	private int id;
	private Product product;
	private int quantity;
	private long orderNumber;
	private Date date;
	private User user;
	private Supplier supplier;
	

}
