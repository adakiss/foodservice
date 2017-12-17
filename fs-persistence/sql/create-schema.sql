DROP TABLE meal CASCADE;

CREATE TABLE meal (
	meal_id SERIAL NOT NULL,
	meal_name CHARACTER VARYING(100) NOT NULL,
	meal_description CHARACTER VARYING(300),
	meal_isallergic BOOLEAN,
	meal_price REAL NOT NULL,
	CONSTRAINT PK_MEAL_ID PRIMARY KEY (meal_id)
);

ALTER TABLE meal OWNER TO postgres;

CREATE UNIQUE INDEX UI_MEAL_NAME ON meal USING btree (meal_name);


DROP TABLE menu CASCADE;

CREATE TABLE menu (
	menu_id SERIAL NOT NULL,
	menu_name CHARACTER VARYING(100) NOT NULL,
	menu_isgeneric BOOLEAN,
	menu_price REAL NOT NULL,
	CONSTRAINT PK_MENU_ID PRIMARY KEY (menu_id)
);

ALTER TABLE menu OWNER TO postgres;

CREATE UNIQUE INDEX UI_MENU_NAME ON menu USING btree (menu_name);


DROP TABLE menu_meal CASCADE;

CREATE TABLE menu_meal (
	menu_id INTEGER NOT NULL,
	meal_id INTEGER NOT NULL,
	CONSTRAINT PK_MENU_MEAL_ID PRIMARY KEY (menu_id, meal_id),
	CONSTRAINT FK_MENU_MEAL_MENU FOREIGN KEY (menu_id)
		REFERENCES menu (menu_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT FK_MENU_MEAL_MEAL FOREIGN KEY (meal_id)
		REFERENCES meal (meal_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE menu_meal OWNER TO postgres;


DROP TABLE customer CASCADE;

CREATE TABLE customer (
	customer_id SERIAL NOT NULL,
	customer_name CHARACTER VARYING(100) NOT NULL,
	customer_address CHARACTER VARYING(300) NOT NULL,
	customer_phone REAL NOT NULL,
	customer_email CHARACTER VARYING(300) NOT NULL,
	CONSTRAINT PK_CUSTOMER_ID PRIMARY KEY (customer_id)
);

ALTER TABLE customer OWNER TO postgres;

CREATE UNIQUE INDEX UI_CUSTOMER_NAME ON customer USING btree (customer_name);

DROP TABLE orderstatus CASCADE;

CREATE TABLE orderstatus (
	orderstatus_id INTEGER NOT NULL,
	orderstatus_name CHARACTER VARYING(50) NOT NULL,
	CONSTRAINT PK_ORDERSTATUS_ID PRIMARY KEY (orderstatus_id)
);

DROP TABLE customerorder CASCADE;

CREATE TABLE customerorder (
	customerorder_id SERIAL NOT NULL,
	customerorder_ordercode CHARACTER VARYING(50) NOT NULL,
	customerorder_buyer INTEGER NOT NULL,
	customerorder_menu INTEGER NOT NULL,
	customerorder_deadline DATE NOT NULL,
	customerorder_status INTEGER NOT NULL,
	CONSTRAINT PK_CUSTOMERORDER_ID PRIMARY KEY (customerorder_id),
	CONSTRAINT FK_CUSTOMERORDER_BUYER FOREIGN KEY (customerorder_buyer)
		REFERENCES customer (customer_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT FK_CUSTOMERORDER_MENU FOREIGN KEY (customerorder_menu)
		REFERENCES menu (menu_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE customerorder OWNER TO postgres;

CREATE UNIQUE INDEX UI_CUSTOMERORDER_ORDERCODE ON customerorder USING btree (customerorder_ordercode);

DROP TABLE appuser CASCADE;

CREATE TABLE appuser (
	appuser_id SERIAL NOT NULL,
	appuser_name CHARACTER VARYING(100) NOT NULL,
	appuser_password CHARACTER VARYING(100),
	CONSTRAINT PK_APPUSER_ID PRIMARY KEY (appuser_id)
);

ALTER TABLE appuser OWNER TO postgres;

CREATE UNIQUE INDEX UI_APPUSER_NAME ON appuser USING btree (appuser_name);

DROP TABLE role CASCADE;

CREATE TABLE role (
	role_id SERIAL NOT NULL,
	role_name CHARACTER VARYING(100),
	CONSTRAINT PK_ROLE_ID PRIMARY KEY (role_id)
);

ALTER TABLE role OWNER TO postgres;

DROP TABLE userrole CASCADE;

CREATE TABLE userrole (
	userrole_id SERIAL NOT NULL,
	userrole_appuser_id INTEGER NOT NULL,
	userrole_role_id INTEGER NOT NULL,
	CONSTRAINT PK_USERROLE_ID PRIMARY KEY (userrole_id),
	CONSTRAINT FK_USERROLE_USER FOREIGN KEY (userrole_appuser_id) REFERENCES appuser (appuser_id)
		MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT FK_USERROLE_ROLE FOREIGN KEY (userrole_role_id) REFERENCES role (role_id)
		MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE userrole OWNER TO postgres;