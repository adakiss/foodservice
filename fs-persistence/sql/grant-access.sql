GRANT SELECT, INSERT, UPDATE, DELETE ON
meal, menu, menu_meal, customer, customerorder TO foodservice_role;

GRANT USAGE, SELECT, UPDATE ON
meal_meal_id_seq, menu_menu_id_seq, customer_customer_id_seq, customerorder_customerorder_id_seq TO foodservice_role;