SELECT username, password, enabled, email, matching_password
	FROM public.users;
	

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users 
(  user_id SERIAL PRIMARY KEY,
   username CHARACTER VARYING(50) NOT NULL UNIQUE,
  password CHARACTER VARYING(100) NOT NULL,
  enabled SMALLINT NOT NULL,
	email CHARACTER VARYING(100) NOT NULL UNIQUE
);



DROP TABLE IF EXISTS authorities CASCADE;
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

