package ua.nure.lesik.SummaryTask4.db.dao.mysql;

import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.dao.MessagesDao;
import ua.nure.lesik.SummaryTask4.db.mapper.MessagesMapper;
import ua.nure.lesik.SummaryTask4.entity.Messages;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for Messages
 *
 * @author Ruslan Lesik
 */
public class MysqlMessagesDao implements MessagesDao {

    private static final String GET_ALL_MESSAGE = "SELECT * FROM messages WHERE user_login=?;";
    private static final String DELETE_MESSAGE = "DELETE FROM messages WHERE id=?;";
    private static final String INSERT_MESSAGE = "INSERT INTO messages (user_login, message)"
            + " VALUES (?, ?)";

    private JdbcTemplate<Messages> jdbcTemplate;

    public MysqlMessagesDao(JdbcTemplate<Messages> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long deleteMessageById(int id, Connection connection) {
        return jdbcTemplate.update(connection, DELETE_MESSAGE, new Object[]{id});
    }

    @Override
    public long addMessage(Messages messages, Connection connection) {
        return jdbcTemplate.update(connection, INSERT_MESSAGE, new Object[]{
                messages.getUser_login(), messages.getMessage()
        });
    }

    @Override
    public List<Messages> getAllMessagesByUserLogin(String userLogin, Connection connection) {
        return jdbcTemplate.getAll(connection,GET_ALL_MESSAGE, new Object[]{userLogin}, new MessagesMapper());
    }
}
