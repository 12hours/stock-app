CREATE TABLE IF NOT EXISTS product_type
	(product_type_id INT PRIMARY KEY AUTO_INCREMENT,
	 product_type_name VARCHAR(64));

CREATE TABLE IF NOT EXISTS user
	(user_id INT PRIMARY KEY AUTO_INCREMENT,
	 username VARCHAR(64),
	 password VARCHAR(256),
	 privileges BIT
	 );

	 
CREATE TABLE IF NOT EXISTS product
	(product_id INT PRIMARY KEY AUTO_INCREMENT,
	 product_name VARCHAR(64),
	 price DECIMAL(9,2),
	 product_type_id INT,
	 FOREIGN KEY (product_type_id) 
	 REFERENCES product_type(product_type_id)
	 );
	 

CREATE TABLE IF NOT EXISTS supplier
	(supplier_id INT PRIMARY KEY AUTO_INCREMENT,
	 supplier_name VARCHAR(64),
	 details VARCHAR(256));
	 
CREATE TABLE IF NOT EXISTS product_income
	(product_income_id INT PRIMARY KEY AUTO_INCREMENT,
	 quantity INT,
	 order_number INT,
	 operation_date DATE,
	 product_id INT,
	 supplier_id INT,
	 user_id INT,
	 FOREIGN KEY (product_id)
	 REFERENCES product(product_id),
	 FOREIGN KEY (supplier_id)
	 REFERENCES supplier(supplier_id),
	 FOREIGN KEY (user_id)
	 REFERENCES user(user_id)
	 );
	 
CREATE TABLE IF NOT EXISTS product_outcome
	(product_outcome_id INT PRIMARY KEY AUTO_INCREMENT,
	 quantity INT,
	 operation_date DATE,
	 product_id INT,
	 user_id INT,
	 FOREIGN KEY (product_id)
	 REFERENCES product(product_id),
	 FOREIGN KEY (user_id)
	 REFERENCES user(user_id)
	 );
	 
