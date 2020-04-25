SET GLOBAL time_zone='+3:00';
SET GLOBAL event_scheduler = ON;
DROP DATABASE IF EXISTS hotel;
CREATE DATABASE hotel;
USE hotel;



CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
	login VARCHAR(20) UNIQUE NOT NULL,
	password VARCHAR(200) NOT NULL,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	patronymic VARCHAR(20),
	email VARCHAR(25) NOT NULL,
	phone_number VARCHAR(25) NOT NULL,
	role VARCHAR(10) NOT NULL
);

CREATE TABLE rooms (
  id INT PRIMARY KEY AUTO_INCREMENT,
  numbers_of_places INT NOT NULL,
  price DOUBLE(10,2) NOT NULL,
  room_classes VARCHAR(25) NOT NULL,
  room_status VARCHAR(25) NOT NULL,
  description VARCHAR(200) NOT NULL,
  image_name VARCHAR(200) NOT NULL
);

CREATE TABLE reserves (
  id INT PRIMARY KEY AUTO_INCREMENT,
  room_id INT NOT NULL,

  constraint reserves_rooms_fk
foreign key (room_id) references rooms (id)
ON DELETE CASCADE ON UPDATE RESTRICT,

  user_id INT NOT NULL,

  constraint reserves_users_fk
foreign key (user_id) references users (id)
ON DELETE CASCADE ON UPDATE RESTRICT,

  date_create DATE NOT NULL,
  check_in DATE NOT NULL,
  check_out DATE NOT NULL,
  price DOUBLE(10,2) NOT NULL,
  status_reserve BIT DEFAULT FALSE
);

CREATE TABLE request (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_login VARCHAR(25) NOT NULL,
  numbers_of_places INT NOT NULL,
  room_classes VARCHAR(25) NOT NULL,
  number_of_days INT NOT NULL,
  status_request BIT DEFAULT FALSE,
  constraint request_users_fk
foreign key (user_login) references users (login)
ON DELETE CASCADE ON UPDATE RESTRICT
);


CREATE TABLE invoice (
  id INT PRIMARY KEY AUTO_INCREMENT,

  days_count int NOT NULL,
  reserve_id INT NOT NULL,

  constraint invoice_reserves_fk
foreign key (reserve_id) references reserves (id)
ON DELETE CASCADE ON UPDATE RESTRICT,

  user_id INT NOT NULL,

  constraint invoice_users_fk
foreign key (user_id) references users (id)
ON DELETE CASCADE ON UPDATE RESTRICT,

  reckoning DOUBLE(10,2) NOT NULL,
  isActive BIT DEFAULT FALSE
);



CREATE TABLE answer (
  id INT PRIMARY KEY AUTO_INCREMENT,
  room_id INT NOT NULL,

  constraint answer_rooms_fk
foreign key (room_id) references rooms (id)
ON DELETE CASCADE ON UPDATE RESTRICT,

  user_login VARCHAR(25) NOT NULL,

  constraint answer_users_fk
foreign key (user_login) references users (login)
ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE messages (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_login VARCHAR(25) NOT NULL,

  constraint messages_users_fk
foreign key (user_login) references users (login)
ON DELETE CASCADE ON UPDATE RESTRICT,
  message VARCHAR(250) NOT NULL
);

CREATE TABLE comments (
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_login VARCHAR(25) NOT NULL,
  room_id INT NOT NULL,

  constraint comments_rooms_fk
foreign key (room_id) references rooms (id)
ON DELETE CASCADE ON UPDATE RESTRICT,

	date_create VARCHAR(25) NOT NULL,
	comment VARCHAR(200) NOT NULL
);


CREATE EVENT `delete_reserve`
ON SCHEDULE EVERY 1 minute
DO
DELETE FROM reserves
WHERE date_add(date_create, interval 2 day) = curdate()
and status_reserve = false;

CREATE EVENT `delete_old_reserve`
ON SCHEDULE EVERY 1 minute
DO
DELETE FROM reserves
WHERE check_out < curdate();

