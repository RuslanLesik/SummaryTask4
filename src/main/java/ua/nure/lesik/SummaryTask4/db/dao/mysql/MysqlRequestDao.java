package ua.nure.lesik.SummaryTask4.db.dao.mysql;

import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.dao.RequestDao;
import ua.nure.lesik.SummaryTask4.db.mapper.RequestMapper;
import ua.nure.lesik.SummaryTask4.entity.Request;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for Request
 *
 * @author Ruslan Lesik
 */
public class MysqlRequestDao implements RequestDao {

    private static final String GET_ALL_REQUEST = "SELECT * FROM request;";
    private static final String DELETE_REQUEST = "DELETE FROM request WHERE id=?;";
    private static final String INSERT_REQUEST = "INSERT INTO request (user_login, numbers_of_places, room_classes, number_of_days)"
            + " VALUES (?, ?, ?, ?)";

    private JdbcTemplate<Request> jdbcTemplate;

    public MysqlRequestDao(JdbcTemplate<Request> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long deleteRequestById(int id, Connection connection) {
        return jdbcTemplate.update(connection, DELETE_REQUEST, new Object[]{id});
    }

    @Override
    public long addRequest(Request request, Connection connection) {
        return jdbcTemplate.update(connection, INSERT_REQUEST, new Object[]{
                request.getUser_login(), request.getNumbers_of_places(),
                request.getRoom_classes().toString(), request.getNumber_of_days()
        });
    }

    @Override
    public List<Request> getAllRequests(Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_REQUEST, new Object[]{}, new RequestMapper());
    }
}
