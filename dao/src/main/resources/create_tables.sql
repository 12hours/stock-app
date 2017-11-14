CREATE TABLE IF NOT EXISTS ${product_type.table.name}
	(${product_type.column.id.name} ${product_type.column.id.type},
	 ${product_type.column.name.name} ${product_type.column.name.type});

CREATE TABLE IF NOT EXISTS ${user.table.name}
	(${user.column.id.name} ${user.column.id.type},
	 ${user.column.name.name} ${user.column.name.type},
	 ${user.column.password.name} ${user.column.password.type},
	 ${user.column.privileges.name} ${user.column.privileges.type}
	 );

	 
CREATE TABLE IF NOT EXISTS ${product.table.name}
	(${product.column.id.name} ${product.column.id.type},
	 ${product.column.name.name} ${product.column.name.type},
	 ${product.column.price.name} ${product.column.price.type},
	 ${product.column.product_type_id.name} ${product.column.product_type_id.type},
	 FOREIGN KEY (${product.column.product_type_id.name}) 
	 REFERENCES ${product_type.table.name}(${product_type.column.id.name})
	 );
	 

CREATE TABLE IF NOT EXISTS ${supplier.table.name}
	(${supplier.column.id.name} ${supplier.column.id.type},
	 ${supplier.column.name.name} ${supplier.column.name.type},
	 ${supplier.column.details.name} ${supplier.column.details.type});
	 
CREATE TABLE IF NOT EXISTS ${product_income.table.name}
	(${product_income.column.id.name} ${product_income.column.id.type},
	 ${product_income.column.quantity.name} ${product_income.column.quantity.type},
	 ${product_income.column.order_number.name} ${product_income.column.order_number.type},
	 ${product_income.column.date.name} ${product_income.column.date.type},
	 ${product_income.column.product_id.name} ${product_income.column.product_id.type},
	 ${product_income.column.supplier_id.name} ${product_income.column.supplier_id.type},
	 ${product_income.column.user_id.name} ${product_income.column.user_id.type},
	 FOREIGN KEY (${product_income.column.product_id.name})
	 REFERENCES ${product.table.name}(${product.column.id.name}),
	 FOREIGN KEY (${product_income.column.supplier_id.name})
	 REFERENCES ${supplier.table.name}(${supplier.column.id.name}),
	 FOREIGN KEY (${product_income.column.user_id.name})
	 REFERENCES ${user.table.name}(${user.column.id.name})
	 );
	 
CREATE TABLE IF NOT EXISTS ${product_outcome.table.name}
	(${product_outcome.column.id.name} ${product_outcome.column.id.type},
	 ${product_outcome.column.quantity.name} ${product_outcome.column.quantity.type},
	 ${product_outcome.column.date.name} ${product_outcome.column.date.type},
	 ${product_outcome.column.product_id.name} ${product_outcome.column.product_id.type},
	 ${product_outcome.column.user_id.name} ${product_outcome.column.user_id.type},
	 FOREIGN KEY (${product_outcome.column.product_id.name})
	 REFERENCES ${product.table.name}(${product.column.id.name}),
	 FOREIGN KEY (${product_outcome.column.user_id.name})
	 REFERENCES ${user.table.name}(${user.column.id.name})
	 );
	 
