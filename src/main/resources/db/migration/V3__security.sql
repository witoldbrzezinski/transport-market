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

CREATE TABLE roles
(
 id SERIAL PRIMARY KEY,
 role CHARACTER VARYING(50) NOT NULL
) ;

INSERT INTO public.roles(
	id, role)
	VALUES (1, 'ROLE_ADMIN'),
	(2,'ROLE_USER');


CREATE TABLE user_role(
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id,role_id),
    CONSTRAINT user_role_fk_1
    FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT use_role_fk_2
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

