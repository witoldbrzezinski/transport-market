CREATE TABLE users
(  id BIGSERIAL PRIMARY KEY,
   username CHARACTER VARYING(50) NOT NULL UNIQUE,
   password CHARACTER VARYING(100) NOT NULL,
   enabled SMALLINT NOT NULL,
   email CHARACTER VARYING(100) NOT NULL UNIQUE,
   customer_id BIGSERIAL NOT NULL,
   is_deleted boolean NOT NULL DEFAULT false,
   uuid VARCHAR(36) NOT NULL,
   version BIGINT DEFAULT 0
);

CREATE TABLE authorities
(
 username CHARACTER VARYING(50) NOT NULL,
 authority CHARACTER VARYING(50) NOT NULL,
 UNIQUE (username,authority)
) ;

ALTER TABLE ONLY authorities
    ADD CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES users(username);

CREATE TABLE roles
(
 role_id SERIAL PRIMARY KEY,
 role CHARACTER VARYING(50) NOT NULL
) ;

INSERT INTO public.roles(
	role_id, role)
	VALUES (1, 'ROLE_ADMIN'),
	(2,'ROLE_USER');

