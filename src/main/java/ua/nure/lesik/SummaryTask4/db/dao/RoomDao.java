package ua.nure.lesik.SummaryTask4.db.dao;

import ua.nure.lesik.SummaryTask4.entity.Room;

import java.sql.Connection;
import java.util.List;

/**
 * Interface defines methods for room dao
 *
 * @author Ruslan Lesik
 */
public interface RoomDao {

    Room getRoomById(int id, Connection connection);

    long deleteRoomById(int id, Connection connection);

    long addRoom(Room room, Connection connection);

    List<Room> getAllRooms(Connection connection);

    List<Room> getAllRoomsByUserId(int userId, Connection connection);

    List<Room> getAllRoomsAnswer(String userLogin, Connection connection);

    long enableRoom(int id, Connection connection);

    long disableRoom(int id, Connection connection);
}
