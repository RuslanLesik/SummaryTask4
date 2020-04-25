package ua.nure.lesik.SummaryTask4.db.dao.mysql;

import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.dao.RoomDao;
import ua.nure.lesik.SummaryTask4.db.mapper.RoomMapper;
import ua.nure.lesik.SummaryTask4.entity.Room;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for Room
 *
 * @author Ruslan Lesik
 */
public class MysqlRoomDao implements RoomDao {

    private static final String DISABLE_ROOM = "UPDATE rooms SET room_status='NOT_AVAILABLE' WHERE id=?;";
    private static final String ENABLE_ROOM = "UPDATE rooms SET room_status='FREE' WHERE id=?;";
    private static final String GET_ALL_ROOMS = "SELECT * FROM rooms;";
    private static final String GET_ALL_ROOMS_ANSWER = "SELECT * FROM rooms r JOIN answer a ON r.id=a.room_id AND a.user_login=? GROUP BY r.id;";
    private static final String GET_ALL_ROOMS_BY_USER = "SELECT * FROM rooms r JOIN reserves res ON r.id=res.room_id AND res.user_id=? GROUP BY r.id;";
    private static final String GET_ROOM = "SELECT * FROM rooms WHERE id=?;";
    private static final String DELETE_ROOM = "DELETE FROM rooms WHERE id=?;";
    private static final String INSERT_ROOM = "INSERT INTO rooms (numbers_of_places, price, room_classes, room_status, description, image_name)"
            + " VALUES (?, ?, ?, ?, ?, ?)";



   private JdbcTemplate<Room> jdbcTemplate;

    public MysqlRoomDao(JdbcTemplate<Room> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Room getRoomById(int id, Connection connection) {
        return jdbcTemplate.get(connection,GET_ROOM, new Object[]{id}, new RoomMapper());
    }

    @Override
    public long deleteRoomById(int id, Connection connection) {
        return jdbcTemplate.update(connection, DELETE_ROOM, new Object[]{id});
    }

    @Override
    public long addRoom(Room room, Connection connection) {
        return jdbcTemplate.update(connection, INSERT_ROOM, new Object[]{
                room.getNumber_of_places(), room.getPrice(),
                room.getRoom_classes().toString(), room.getRoom_status().toString(), room.getDescription(),
                room.getImage_name()
        });
    }

    @Override
    public List<Room> getAllRooms(Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_ROOMS, new Object[]{}, new RoomMapper());
    }

    @Override
    public List<Room> getAllRoomsByUserId(int userId, Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_ROOMS_BY_USER, new Object[]{userId}, new RoomMapper());
    }

    @Override
    public List<Room> getAllRoomsAnswer(String userLogin, Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_ROOMS_ANSWER, new Object[]{userLogin}, new RoomMapper());
    }

    @Override
    public long enableRoom(int id, Connection connection) {
        return jdbcTemplate.update(connection,ENABLE_ROOM, new Object[]{id});
    }

    @Override
    public long disableRoom(int id, Connection connection) {
        return  jdbcTemplate.update(connection,DISABLE_ROOM, new Object[]{id});
    }
}
