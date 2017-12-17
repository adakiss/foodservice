GRANT SELECT, INSERT, UPDATE, DELETE ON
meal, menu, menu_meal, customer, customerorder TO foodservice_role;

GRANT USAGE, SELECT, UPDATE ON
meal_meal_id_seq, menu_menu_id_seq, customer_customer_id_seq, customerorder_customerorder_id_seq TO foodservice_role;

GRANT SELECT, INSERT, UPDATE, DELETE ON appuser, role, userrole TO foodservice_role;
GRANT USAGE, SELECT, UPDATE ON appuser_appuser_id_seq, role_role_id_seq, userrole_userrole_id_seq TO foodservice_role;