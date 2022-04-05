DROP TABLE IF EXISTS customers;

CREATE TABLE customers
(
	customer_id SERIAL PRIMARY KEY,
	name CHARACTER VARYING(255),
	country CHARACTER VARYING(100),
	city CHARACTER VARYING(255),
	email CHARACTER VARYING(255),
	phone CHARACTER VARYING(255)
);


INSERT INTO customers
VALUES (1, 'Optima', 'Poland','Warsaw','opt@optima.com','+48123456'),
       (2, 'DSV', 'Denmark','Kopenhagen','dsvdsv@dsv.com','+45234567');

