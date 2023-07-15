INSERT INTO user_role(description, title)
VALUES('Role Manager', 'ROLE_MANAGER');
INSERT INTO user_role(description, title)
VALUES('Role Receptionist', 'ROLE_RECEPTIONIST');

--password : admin123
INSERT INTO users(username, password, identity, user_role_id)
VALUES('nguyenvana', '$2a$12$SPwKh2SjFparWB.LrEfvbOkcV0gi6hKjzWSDXePmqSbdaKHXbHRY2', '012345678911', 1);

--password : admin123
INSERT INTO users(username, password, identity, user_role_id)
VALUES('nhanvien', '$2a$12$SPwKh2SjFparWB.LrEfvbOkcV0gi6hKjzWSDXePmqSbdaKHXbHRY2', '012345678912', 2);


INSERT INTO room_type(description, price)
VALUES('Double room (2 beds)', 40);
INSERT INTO room_type(description, price)
VALUES('Double room (1 bed)', 40);

INSERT INTO room(room_number, room_type_id)
VALUES('101', 1);
INSERT INTO room(room_number, room_type_id)
VALUES('102', 2);
INSERT INTO room(room_number, room_type_id)
VALUES('102', 2);

INSERT INTO customer_type(title, description)
VALUES('Family', 'Small');
INSERT INTO customer_type(title, description)
VALUES('Company', 'Small');


INSERT INTO customer(full_name, identity, phone, booking_count, type_id)
VALUES('Mart Ziukabet', '123123123122', '0123456789', 1, 1);

INSERT INTO customer(full_name, identity, phone, booking_count, type_id)
VALUES('Fake Bock', '123532331239', '09999990192', 1, 2);

INSERT INTO status_booking(description, title)
VALUES('', 'Reserved');

INSERT INTO status_booking(description, title)
VALUES('', 'Checked-in');

INSERT INTO status_booking(description, title)
VALUES('', 'Checked-out');

INSERT INTO status_booking(description, title)
VALUES('', 'Cancelled');

INSERT INTO status_booking(description, title)
VALUES('', 'No-show');

INSERT INTO booking(booking_date, deposit, num_adult, status_booking_id, customer_id, user_id)
VALUES('2023-08-15 16:07:10.790551', 20, 2, 1, 1, 2);

INSERT INTO booking(booking_date, deposit, num_adult, status_booking_id, customer_id, user_id)
VALUES('2023-08-16 16:07:10.790551', 20, 2, 1, 2, 2);


INSERT INTO booking_room(booking_id, room_id)
VALUES(1, 3);

INSERT INTO booking_room(booking_id, room_id)
VALUES(2, 1);

INSERT INTO booking_room(booking_id, room_id)
VALUES(2, 2);