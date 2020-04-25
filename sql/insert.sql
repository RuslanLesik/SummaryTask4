INSERT INTO users (login, password, first_name, last_name, patronymic,  email, phone_number, role)
VALUES
  ('admin', '21232f297a57a5a743894a0e4a801fc3', 'Ruslan', 'Lesik', 'Alex', 'admin@admin.com', '(096)000-0000', 'MANAGER'),
  ('student', 'cd73502828457d15655bbd7a63fb0bc8', 'Petr', 'Petrov', 'Petrovich', 'student@student.com', '090-000-0000', 'CLIENT'),
  ('client', 'cd73502828457d15655bbd7a63fb0bc8', 'Max', 'Maxov', 'Maxovich', 'max@client.com', '091-111-1111', 'CLIENT'),
  ('client2', 'cd73502828457d15655bbd7a63fb0bc8', 'Alex', 'Alexov', 'Alexovich', 'alex@client.com', '092-222-2222', 'CLIENT'),
  ('client3', 'cd73502828457d15655bbd7a63fb0bc8', 'Евгений', 'Шестаков', 'Иванович', 'evg@client.com', '093-333-3333', 'CLIENT');


INSERT INTO rooms (numbers_of_places, price, room_classes, room_status, description, image_name )
VALUES
  ('2', '700.00', 'LUXE', 'FREE', 'Located on the first floor, this well equipped apartment is fully air-conditioned and has fast cable Internet access. Room is suitable for twin accommodations.', '1.jpg'),
  ('1', '550.00', 'LUXE', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  air conditioner, cable TV, bed sheets and towels. Room is suitable for single accommodations.', '2.jpg'),
  ('1', '200.00', 'STANDART', 'FREE', 'Room facilities: a shower, toilet room, toilet goods,
  cable TV, electric kettle, bed clothes
  and towels.
  Room is suitable for single accommodations.', '3.jpg'),
  ('2', '200.00', 'STANDART', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for twin accommodations.', '4.jpg'),
  ('1', '350.00', 'DE_LUXE', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for single accommodations.', '5.jpg'),
  ('2', '450.00', 'DE_LUXE', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for twin accommodations.', '6.jpg'),
  ('3', '550.00', 'DE_LUXE', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for triple  accommodations.', '7.jpg'),
  ('3', '300.00', 'STANDART', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for triple  accommodations.', '8.jpg'),
  ('3', '850.00', 'LUXE', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for triple  accommodations.', '9.jpg'),
  ('4', '950.00', 'DE_LUXE', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for four people.', '10.jpg'),
  ('5', '1050.00', 'DE_LUXE', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for five people.', '11.jpg'),
  ('6', '1150.00', 'DE_LUXE', 'FREE', 'In the room: cable TV, shower, toilet, toiletries,
  cable TV, bed sheets and towels.Room is suitable for six people', '12.jpg');


  INSERT INTO request (user_login, numbers_of_places, room_classes, number_of_days)
VALUES
  ('student', '3', 'STANDART', '4'),
  ('student', '2', 'LUXE', '2');

  INSERT INTO reserves (room_id, user_id, date_create, check_in, check_out, price, status_reserve  )
VALUES
  ('4', '2', '2020-03-25', '2020.03.26', '2020.03.29', '100.0', true),
  ('5', '2', '2020.03.25', '2020.03.25', '2020.03.29', '250.0', true),
  ('3', '2', '2020.03.26', '2020.03.26', '2020.03.29', '222.0', false );
