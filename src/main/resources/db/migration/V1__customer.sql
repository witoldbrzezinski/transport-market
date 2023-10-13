CREATE TABLE customers
(
	id BIGSERIAL PRIMARY KEY,
	name CHARACTER VARYING(255),
	country CHARACTER VARYING(100),
	city CHARACTER VARYING(255),
	email CHARACTER VARYING(255),
	phone CHARACTER VARYING(255),
	is_deleted boolean NOT NULL DEFAULT false,
    uuid VARCHAR(36) NOT NULL,
    version BIGINT DEFAULT 0
);


INSERT INTO customers
VALUES (1, 'Optima', 'Poland','Warsaw','opt@optima.com','+48123456',false,'df5a0b4b-fbb1-4f7d-a2b6-2d0c003dd73b',0),
       (2, 'DSV', 'Denmark','Kopenhagen','dsvdsv@dsv.com','+45234567',false,'6eec55ce-53d7-4536-9f99-9853df1a71bf',0);

