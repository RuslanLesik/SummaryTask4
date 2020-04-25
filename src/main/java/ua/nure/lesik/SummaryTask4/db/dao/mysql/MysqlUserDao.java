package ua.nure.lesik.SummaryTask4.db.dao.mysql;

import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.dao.UserDao;
import ua.nure.lesik.SummaryTask4.db.mapper.UserMapper;
import ua.nure.lesik.SummaryTask4.entity.User;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for User
 *
 * @author Ruslan Lesik
 */
public class MysqlUserDao implements UserDao {

    private static final String GET_ALL_USERS = "SELECT * FROM users WHERE role='CLIENT';";
    private static final String GET_USER = "SELECT * FROM users WHERE login=?;";
    private static final String DELETE_USER = "DELETE FROM users WHERE login=?;";
    private static final String INSERT_USER = "INSERT INTO users (login, password, first_name, last_name, patronymic, email, phone_number, role)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private JdbcTemplate<User> jdbcTemplate;

    public MysqlUserDao(JdbcTemplate<User> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByLogin(String login, Connection connection) {
        return jdbcTemplate.get(connection, GET_USER, new Object[]{login}, new UserMapper());
    }

    @Override
    public long deleteUserByLogin(String login, Connection connection) {
        return jdbcTemplate.update(connection, DELETE_USER, new Object[]{login});
    }

    @Override
    public long addUser(User user, Connection connection) {
        return jdbcTemplate.update(connection, INSERT_USER, new Object[]{
                user.getLogin(), user.getPassword(), user.getFirst_name(),
                user.getLast_name(), user.getPatronymic(), user.getEmail(),
                user.getPhone_number(), user.getRole().toString()
        });
    }

    @Override
    public List<User> getAllUsers(Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_USERS, new Object[]{}, new UserMapper());
    }
}
