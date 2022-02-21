DROP TABLE IF EXISTS customers;

CREATE TABLE customers
(
	customer_id SERIAL PRIMARY KEY,
	name CHARACTER VARYING(255),
	country CHARACTER VARYING(100)
);


INSERT INTO customers
VALUES (1, 'Optima', 'Poland'),
       (2, 'DSV', 'Denmark');

