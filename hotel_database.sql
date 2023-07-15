--password : admin123
INSERT INTO users(username, password, identity, user_role_id)
VALUES('nguyenvana', '$2a$12$SPwKh2SjFparWB.LrEfvbOkcV0gi6hKjzWSDXePmqSbdaKHXbHRY2', '012345678911', 1);

--password : admin123
INSERT INTO users(username, password, identity, user_role_id)
VALUES('nhanvien', '$2a$12$SPwKh2SjFparWB.LrEfvbOkcV0gi6hKjzWSDXePmqSbdaKHXbHRY2', '012345678912', 2);

INSERT INTO user_role(description, title)
VALUES('Role Manager', 'ROLE_MANAGER');
INSERT INTO user_role(description, title)
VALUES('Role Receptionist', 'ROLE_RECEPTIONIST');

