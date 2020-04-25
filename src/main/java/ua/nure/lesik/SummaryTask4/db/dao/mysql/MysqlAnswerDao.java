package ua.nure.lesik.SummaryTask4.db.dao.mysql;

import ua.nure.lesik.SummaryTask4.db.dao.AnswerDao;
import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.entity.Answer;

import java.sql.Connection;

/**
 * Data access object for Answer
 *
 * @author Ruslan Lesik
 */
public class MysqlAnswerDao implements AnswerDao {

    private static final String DELETE_ANSWER = "DELETE FROM answer WHERE room_id=? AND user_login=?;";
    private static final String INSERT_ANSWER = "INSERT INTO answer (room_id, user_login)"
            + " VALUES (?, ?)";

    private JdbcTemplate<Answer> jdbcTemplate;

    public MysqlAnswerDao(JdbcTemplate<Answer> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long deleteAnswerByUserLoginAndRoomID(String user_login, int roomId, Connection connection) {
        return jdbcTemplate.update(connection, DELETE_ANSWER, new Object[]{roomId, user_login});
    }

    @Override
    public long addAnswer(Answer answer, Connection connection) {
        return jdbcTemplate.update(connection, INSERT_ANSWER, new Object[]{
                answer.getRoom_id(), answer.getUser_login()
        });
    }
}
