package ua.nure.lesik.SummaryTask4.db.dao.mysql;

import ua.nure.lesik.SummaryTask4.db.dao.CommentDao;
import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.mapper.CommentMapper;
import ua.nure.lesik.SummaryTask4.entity.Comment;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for Comment
 *
 * @author Ruslan Lesik
 */
public class MysqlCommentDao implements CommentDao {

    private static final String DELETE_COMMENT_BY_ID = "DELETE FROM comments WHERE id=?;";
    private static final String INSERT_COMMENT = "INSERT INTO comments (user_login, room_id, date_create, comment)"
            + " VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_COMMENTS_BY_ROOM_ID = "SELECT * FROM comments WHERE room_id=?;";

    private JdbcTemplate<Comment> jdbcTemplate;

    public MysqlCommentDao(JdbcTemplate<Comment> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long addComment(Comment comment, Connection connection) {
        return jdbcTemplate.update(connection, INSERT_COMMENT, new Object[]{
                comment.getUser_login(),
                comment.getRoom_id(),
                comment.getDate_create(),
                comment.getComment()});
    }

    @Override
    public long deleteCommentById(int comment_id, Connection connection) {
        return jdbcTemplate.update(connection, DELETE_COMMENT_BY_ID, new Object[]{comment_id});
    }

    @Override
    public List<Comment> getAllCommentsByRoomId(int room_id, Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_COMMENTS_BY_ROOM_ID, new Object[]{room_id}, new CommentMapper());
    }
}
