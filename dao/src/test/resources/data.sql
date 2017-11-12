INSERT INTO `${product_type.table.name}`
(`${product_type.column.id.name}`,
`${product_type.column.name.name}`)
VALUES
(1, 'CPU'),
(2, 'Motherboard'),
(3, 'Videocard'),
(4, 'SSD'),
(5, 'HDD');
  
INSERT INTO `${product.table.name}`
(`${product.column.id.name}`,
`${product.column.name.name}`,
`${product.column.price.name}`,
`${product.column.product_type_id.name}`)
VALUES
(1, 'Intel Core i7 8700', 360, 1),
(2, 'Intel Core i3 8100', 135, 1),
(3, 'Nvidia GTX 1050Ti', 200, 3),
(4, 'Intel Pentium G4360', 65, 1),
(5, 'AMD Ryzen 7 1700', 325, 1),
(6, 'Samsung 850 Evo 256 Gb', 100, 4),
(7, 'Intel Core i5 6600K', 250, 1),
(8, 'Kingston UV400 120 Gb', 65, 4),
(9, 'ASRock Z370', 165, 2);

INSERT INTO `${supplier.table.name}`
(`${supplier.column.id.name}`,
`${supplier.column.name.name}`,
`${supplier.column.details.name}`)
VALUES
(1, 'Nova Computers', ''),
(2, 'PC World Group', ''),
(3, 'Ultra Hardware Inc.', '');

INSERT INTO `${user.table.name}`
(`${user.column.id.name}`,
`${user.column.name.name}`,
`${user.column.password.name}`,
`${user.column.privileges.name}`)
VALUES
(1, 'Accounter', '12345', 0),
(2, 'Admin', '54321', 1);

INSERT INTO `${product_income.table.name}`
(`${product_income.column.id.name}`,
`${product_income.column.quantity.name}`,
`${product_income.column.order_number.name}`,
`${product_income.column.date.name}`,
`${product_income.column.product_id.name}`,
`${product_income.column.supplier_id.name}`,
`${product_income.column.user_id.name}`) 
VALUES
(1, 10, 10001, '2017-10-25', 1, 1, 1),
(2, 40, 10002, '2017-10-25', 2, 2, 1),
(3, 35, 10003, '2017-10-26', 3, 2, 1),
(4, 50, 10004, '2017-10-27', 4, 1, 1),
(5, 10, 10005, '2017-10-28', 3, 3, 1),
(6, 25, 10006, '2017-10-29', 5, 2, 1),
(7, 25, 10007, '2017-10-30', 6, 1, 1),
(8, 15, 10008, '2017-10-31', 9, 3, 1),
(9, 10, 10009, '2017-11-02', 7, 3, 1),
(10, 25, 10010, '2017-11-05', 6, 2, 1),
(11, 30, 10011, '2017-11-05', 8, 1, 1),
(12, 10, 10012, '2017-11-07', 9, 2, 1);

INSERT INTO `${product_outcome.table.name}`
(`${product_outcome.column.id.name}`,
`${product_outcome.column.quantity.name}`,
`${product_outcome.column.date.name}`,
`${product_outcome.column.product_id.name}`,
`${product_outcome.column.user_id.name}`) 
VALUES
(1, 7, '2017-10-27', 2, 1),
(2, 5, '2017-10-27', 1, 1),
(3, 12, '2017-10-31', 5, 1),
(4, 20, '2017-11-01', 6, 1),
(5, 6, '2017-11-01', 2, 1),
(6, 3, '2017-11-03', 6, 1),
(7, 12, '2017-11-04', 9, 1),
(8, 16, '2017-11-04', 3, 1),
(9, 1, '2017-11-06', 9, 1),
(10, 8, '2017-11-07', 5, 1),
(11, 3, '2017-11-07', 7, 1),
(12, 2, '2017-11-08', 6, 1);



