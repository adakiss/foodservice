--meal table initial data
INSERT INTO meal (meal_name, meal_description, meal_isallergic, meal_price)
VALUES ('gulash', 'traditional hungarian soup', false, 500);

INSERT INTO meal (meal_name, meal_description, meal_isallergic, meal_price)
VALUES ('clear soup', 'traditional hungarian soup', false, 450);

INSERT INTO meal (meal_name, meal_description, meal_isallergic, meal_price)
VALUES ('pasta with cottage cheese', 'traditional hungarian meal', true, 600);

INSERT INTO meal (meal_name, meal_description, meal_isallergic, meal_price)
VALUES ('pizza', 'with the toppings of your choosing', true, 1200);

INSERT INTO meal (meal_name, meal_description, meal_isallergic, meal_price)
VALUES ('semolina pudding', null , true, 500);

INSERT INTO meal (meal_name, meal_description, meal_isallergic, meal_price)
VALUES ('pudding', 'vanilla/chokolate/strawberry', true, 350);

--menu table initial data
INSERT INTO menu (menu_name, menu_isgeneric, menu_price)
VALUES ('A menu', true, 1000);

INSERT INTO menu (menu_name, menu_isgeneric, menu_price)
VALUES ('B menu', true, 1000);

INSERT INTO menu (menu_name, menu_isgeneric, menu_price)
VALUES ('X menu', true, 3500);

--customer table initial data
INSERT INTO customer (customer_name, customer_address, customer_phone, customer_email)
VALUES ('Adam Kidd', '1066 Budapest, Terez korut 58 fsz. 2', 706225466, 'htown1666@gmail.com');

INSERT INTO customer (customer_name, customer_address, customer_phone, customer_email)
VALUES ('Vivien Kiss', '1077 Budapest, Izabella utca 34 4. emelet', 304567895, 'kissvivi92@gmail.com');

INSERT INTO customer (customer_name, customer_address, customer_phone, customer_email)
VALUES ('Liliana Zold', '1077 Budapest, Izabella utca 34 4. emelet', 201111111, 'lilivagyok2017@gmail.com');

INSERT INTO customer (customer_name, customer_address, customer_phone, customer_email)
VALUES ('John Doe', 'Nyugati aluljaro', 500000005, 'homelessman@noemail.com');

--orderstatus table initial data
INSERT INTO orderstatus (orderstatus_id, orderstatus_name)
VALUES (0, 'WAITING');

INSERT INTO orderstatus (orderstatus_id, orderstatus_name)
VALUES (1, 'IN_PROGRESS');

INSERT INTO orderstatus (orderstatus_id, orderstatus_name)
VALUES (2, 'DELIVERY');

INSERT INTO orderstatus (orderstatus_id, orderstatus_name)
VALUES (3, 'COMPLETED');

--customerorder table initial data
INSERT INTO customerorder (customerorder_ordercode, customerorder_buyer, customerorder_menu, customerorder_deadline, customerorder_status)
VALUES ('T0', 1, 1, CURRENT_DATE, 0);

INSERT INTO customerorder (customerorder_ordercode, customerorder_buyer, customerorder_menu, customerorder_deadline, customerorder_status)
VALUES ('T1', 2, 3, CURRENT_DATE, 0);

--menu_meal table initial data
INSERT INTO menu_meal (menu_id, meal_id)
VALUES (1, 1);

INSERT INTO menu_meal (menu_id, meal_id)
VALUES (1, 3);

INSERT INTO menu_meal (menu_id, meal_id)
VALUES (2, 2);

INSERT INTO menu_meal (menu_id, meal_id)
VALUES (2, 4);

INSERT INTO menu_meal (menu_id, meal_id)
VALUES (3, 1);

INSERT INTO menu_meal (menu_id, meal_id)
VALUES (3, 4);

INSERT INTO menu_meal (menu_id, meal_id)
VALUES (3, 6);