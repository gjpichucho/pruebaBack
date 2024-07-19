-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- public.person definition


-- DROP TABLE public.person;

CREATE TABLE public.person (
	id_person bigserial NOT NULL,
	address varchar(100) NOT NULL,
	age int4 NOT NULL,
	gender varchar(10) NULL,
	identification varchar(16) NOT NULL,
	"name" varchar(100) NOT NULL,
	phone varchar(12) NULL,
	CONSTRAINT person_pkey PRIMARY KEY (id_person)
);

-- public.client definition

-- DROP TABLE public.client;

CREATE TABLE public.client (
	"password" varchar(100) NOT NULL,
	status bool NOT NULL,
	id_person int8 NOT NULL,
	CONSTRAINT client_pkey PRIMARY KEY (id_person)
);


-- public.client foreign keys

ALTER TABLE public.client ADD CONSTRAINT fk9e7femh5hss1rcvl0qfin9h6a FOREIGN KEY (id_person) REFERENCES person(id_person);

-- public.account definition


-- DROP TABLE public.account;

CREATE TABLE public.account (
	id_account bigserial NOT NULL,
	account_number varchar(10) NOT NULL,
	initial_balance numeric(16,2) NOT NULL,
	status bool NOT NULL,
	type_account varchar(10) NOT NULL,
	id_person int8 NULL,
	CONSTRAINT account_pkey PRIMARY KEY (id_account),
	CONSTRAINT uk_66gkcp94endmotfwb8r4ocxm9 UNIQUE (account_number)
);


-- public.account foreign keys

ALTER TABLE public.account ADD CONSTRAINT fk_account_to_client FOREIGN KEY (id_person) REFERENCES client(id_person);

-- public.movement definition

-- Drop table

-- DROP TABLE public.movement;

CREATE TABLE public.movement (
	id_movement bigserial NOT NULL,
	balance numeric(16,2) NOT NULL,
	date_movement timestamp NOT NULL,
	type_movement varchar(10) NOT NULL,
	value numeric(16,2) NOT NULL,
	id_account int8 NULL,
	CONSTRAINT movement_pkey PRIMARY KEY (id_movement)
);


-- public.movement foreign keys

ALTER TABLE public.movement ADD CONSTRAINT fk_movement_to_account FOREIGN KEY (id_account) REFERENCES account(id_account);



-- INSERTS
INSERT INTO person (id_person, address, age, gender, identification, "name", phone) VALUES(1, 'conocoto', 26, 'MASCULINO', '1719097745', 'Galo Pichucho', '0999273956');
INSERT INTO person (id_person, address, age, gender, identification, "name", phone) VALUES(2, 'Quito', 25, 'MASCULINO', '1719097780', 'Juan Osorio', '098874587');
INSERT INTO person (id_person, address, age, gender, identification, "name", phone) VALUES(3, 'Av. Mariscal sucre', 30, 'MASCULINO', '1719097780', 'Luis Santana', '0984912369');
INSERT INTO person (id_person, address, age, gender, identification, "name", phone) VALUES(4, 'Amazonas y NNUU', 20, 'FEMENINO', '1713778584', 'Marianela Montalvo', '0998240734');


INSERT INTO client ("password", status, id_person) VALUES('fLrWwizWMq5aI7xRNlp4Bg==', true, 1);
INSERT INTO client ("password", status, id_person) VALUES('fLrWwizWMq5aI7xRNlp4Bg==', true, 2);
INSERT INTO client ("password", status, id_person) VALUES('fLrWwizWMq5aI7xRNlp4Bg==', true, 3);
INSERT INTO client ("password", status, id_person) VALUES('fLrWwizWMq5aI7xRNlp4Bg==', true, 4);


INSERT INTO account (id_account, account_number, initial_balance, status, type_account, id_person) VALUES(1, '478758', 100.00, true, 'CORRIENTE', 1);
INSERT INTO account (id_account, account_number, initial_balance, status, type_account, id_person) VALUES(4, '495878', 25.00, true, 'AHORRO', 1);
INSERT INTO account (id_account, account_number, initial_balance, status, type_account, id_person) VALUES(5, '496825', 25.00, true, 'AHORRO', 4);



INSERT INTO movement (id_movement, balance, date_movement, type_movement, value, id_account) VALUES(1, 100.00, '2024-07-19 11:11:15.750', 'CREDITO', 100.00, 1);
INSERT INTO movement (id_movement, balance, date_movement, type_movement, value, id_account) VALUES(2, 150.00, '2024-07-19 11:27:15.331', 'CREDITO', 50.00, 1);
INSERT INTO movement (id_movement, balance, date_movement, type_movement, value, id_account) VALUES(3, 200.00, '2024-07-19 11:40:31.585', 'CREDITO', 50.00, 1);
INSERT INTO movement (id_movement, balance, date_movement, type_movement, value, id_account) VALUES(4, 175.00, '2024-07-19 11:41:50.212', 'DEBITO', 25.00, 1);
INSERT INTO movement (id_movement, balance, date_movement, type_movement, value, id_account) VALUES(9, 675.00, '2024-07-19 12:05:45.416', 'CREDITO', 500.00, 1);
INSERT INTO movement (id_movement, balance, date_movement, type_movement, value, id_account) VALUES(10, 25.00, '2024-07-19 12:23:06.588', 'CREDITO', 25.00, 4);
INSERT INTO movement (id_movement, balance, date_movement, type_movement, value, id_account) VALUES(11, 25.00, '2024-07-19 12:23:50.684', 'CREDITO', 25.00, 5);

