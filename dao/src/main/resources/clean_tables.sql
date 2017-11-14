SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE IF EXISTS @product.table.name@;
TRUNCATE TABLE IF EXISTS @product_type.table.name@;
TRUNCATE TABLE IF EXISTS @product_income.table.name@;
TRUNCATE TABLE IF EXISTS @product_outcome.table.name@;
TRUNCATE TABLE IF EXISTS @supplier.table.name@;
TRUNCATE TABLE IF EXISTS @user.table.name@;
SET REFERENTIAL_INTEGRITY TRUE;