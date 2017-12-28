SELECT 	product.product_id AS product_id,
		product.product_name AS product_name,
        product.price AS price,
        product_type.product_type_id AS product_type_id,
        product_type.product_type_name AS product_type_name, 
        product_quantity.quantity AS quantity
	FROM
	(SELECT product_income.product_id AS product_id, (product_income.quantity - (select if(product_outcome.quantity is null, 0, product_outcome.quantity))) AS quantity FROM
		(SELECT product_income.product_id AS product_id, SUM(product_income.quantity) AS quantity
			FROM product_income
			GROUP BY product_income.product_id
		) AS product_income
		LEFT JOIN
		(SELECT product_outcome.product_id AS product_id, SUM(product_outcome.quantity) AS quantity
			FROM product_outcome
			GROUP BY product_outcome.product_id
		) AS product_outcome
		ON product_income.product_id = product_outcome.product_id
	)AS product_quantity
JOIN product ON product.product_id = product_quantity.product_id
JOIN product_type ON product.product_type_id = product_type.product_type_id
ORDER BY product_id;