SET REFERENTIAL_INTEGRITY FALSE;
DROP TABLE IF EXISTS ${product.table.name};
DROP TABLE IF EXISTS ${product_type.table.name};
DROP TABLE IF EXISTS ${product_income.table.name};
DROP TABLE IF EXISTS ${product_outcome.table.name};
DROP TABLE IF EXISTS ${supplier.table.name};
DROP TABLE IF EXISTS ${user.table.name};
SET REFERENTIAL_INTEGRITY TRUE;