
CREATE TABLE CAPITOLE_CONSULTING_JAVA_TEST_SCHEMA.PRICES(
	ID INT NOT NULL,
	BRAND_ID INT,
	START_DATE TIMESTAMP,
	END_DATE TIMESTAMP,
	PRICE_LIST INT,
	PRODUCT_ID INT,
	PRIORITY INT,
	PRICE DECIMAL(4,2),
	CURR VARCHAR(5)
);


INSERT INTO CAPITOLE_CONSULTING_JAVA_TEST_SCHEMA.PRICES VALUES (
	1,
	1,
	'2020-06-14 00:00:00.000',
	'2020-12-31 23:59:59.000',
	1,
	35455,
	0,
	35.50,
	'EUR'
), (
	2,
	1,
	'2020-06-14 15:00:00.000',
	'2020-06-14 18:30:00.000',
	2,
	35455,
	1,
	25.45,
	'EUR'
), (
	3,
	1,
	'2020-06-15 00:00:00.000',
	'2020-06-15 11:00:00.000',
	3,
	35455,
	1,
	30.50,
	'EUR'
), (
	4,
	1,
	'2020-06-15 16:00:00.000',
	'2020-12-31 23:59:59.000',
	4,
	35455,
	1,
	38.95,
	'EUR'
);
