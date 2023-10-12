CREATE TABLE loads
(
	load_id SERIAL PRIMARY KEY,
	name CHARACTER VARYING(255),
	loading_date timestamp with time zone,
	loading_city CHARACTER VARYING(255),
	loading_postcode CHARACTER VARYING(10),
	unloading_date timestamp with time zone,
	unloading_city CHARACTER VARYING(255),
	unloading_postcode CHARACTER VARYING(10),
	weight_in_tones numeric(10,3),
	load_type CHARACTER VARYING(255),
	price_in_PLN numeric (10,2),
	user_id INTEGER
);


