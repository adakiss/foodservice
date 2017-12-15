CREATE USER foodservice_user;
ALTER USER foodservice_user PASSWORD
'123456';
GRANT foodservice_role TO foodservice_user;