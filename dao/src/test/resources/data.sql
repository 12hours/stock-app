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
(1, 'CPU'),
(2, 'Motherboard'),
(3, 'Videocard'),
(4, 'SSD'),
  (5, 'HDD');