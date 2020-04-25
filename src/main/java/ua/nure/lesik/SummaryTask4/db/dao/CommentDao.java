package ua.nure.lesik.SummaryTask4.db.dao;

import ua.nure.lesik.SummaryTask4.entity.Comment;

import java.sql.Connection;
import java.util.List;

/**
 * Interface defines methods for comment dao
 *
 * @author Ruslan Lesik
 */
public interface CommentDao {

    long addComment(Comment comment, Connection connection);

    long deleteCommentById(int comment_id, Connection connection);

    List<Comment> getAllCommentsByRoomId(int room_id, Connection connection);
}
