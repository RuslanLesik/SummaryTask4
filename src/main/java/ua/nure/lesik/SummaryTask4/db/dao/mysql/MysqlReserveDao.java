package ua.nure.lesik.SummaryTask4.db.dao.mysql;

import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.dao.ReserveDao;
import ua.nure.lesik.SummaryTask4.db.mapper.ReserveMapper;
import ua.nure.lesik.SummaryTask4.entity.Reserve;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for Reserve
 *
 * @author Ruslan Lesik
 */
public class MysqlReserveDao implements ReserveDao {

    private static final String GET_ALL_RESERVES = "SELECT * FROM reserves;";
    private static final String GET_RESERVE_BY_DATE_USER_ROOM_ID = "SELECT * FROM reserves WHERE check_in=? AND check_out=? AND user_id=? AND room_id=?;";
    private static final String GET_RESERVE_BY_USER_ROOM_ID = "SELECT * FROM reserves WHERE room_id=? AND user_id=?;";
    private static final String DELETE_RESERVE = "DELETE FROM reserves WHERE id=?;";
    private static final String CHANGE_RESERVE = "UPDATE reserves SET status_reserve=true WHERE id=?";
    private static final String INSERT_RESERVE = "INSERT INTO reserves (room_id, user_id, date_create, check_in, check_out, price, status_reserve)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_RESERVES_BY_ROOM_ID = "SELECT * FROM reserves WHERE room_id=? ORDER BY check_in;";

    private JdbcTemplate<Reserve> jdbcTemplate;

    public MysqlReserveDao(JdbcTemplate<Reserve> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long deleteReserveById(int id, Connection connection) {
        return jdbcTemplate.update(connection, DELETE_RESERVE, new Object[]{id});
    }

    @Override
    public long changeReserveStatusById(int id, Connection connection) {
        return jdbcTemplate.update(connection, CHANGE_RESERVE, new Object[]{id});
    }

    @Override
    public long addReserve(Reserve reserve, Connection connection) {
        return jdbcTemplate.update(connection, INSERT_RESERVE, new Object[]{
                reserve.getRoom_id(), reserve.getUser_id(), reserve.getDate_create(),
                reserve.getCheck_in(), reserve.getCheck_out(), reserve.getPrice(),
                reserve.isStatus_reserve()
        });
    }

    @Override
    public List<Reserve> getAllReserves(Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_RESERVES, new Object[]{}, new ReserveMapper());
    }

    @Override
    public Reserve getReserveByDateUserAndRoomID(String check_in, String check_out, int userId, int room_id, Connection connection) {
        return jdbcTemplate.get(connection, GET_RESERVE_BY_DATE_USER_ROOM_ID, new Object[]{
                check_in, check_out, userId, room_id}, new ReserveMapper());
    }

    @Override
    public List<Reserve> getAllReservesByRoomId(int roomId, Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_RESERVES_BY_ROOM_ID, new Object[]{roomId}, new ReserveMapper());
    }

    @Override
    public List<Reserve> getAllReservesByRoomAndUserId(int roomId, int userId, Connection connection) {
        return jdbcTemplate.getAll(connection, GET_RESERVE_BY_USER_ROOM_ID, new Object[]{roomId, userId}, new ReserveMapper());
    }
}
