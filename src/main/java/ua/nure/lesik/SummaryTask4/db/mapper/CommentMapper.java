package ua.nure.lesik.SummaryTask4.db.mapper;

import ua.nure.lesik.SummaryTask4.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Comment object retrieves
 *
 * @author Ruslan Lesik
 */
public class CommentMapper implements Mapper<Comment> {
    @Override
    public Comment extract(ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt(Fields.ID));
        comment.setRoom_id(rs.getInt(Fields.ROOM_ID));
        comment.setUser_login(rs.getString(Fields.USER_LOGIN_REQUEST));
        comment.setDate_create(rs.getString(Fields.DATE_CREATE));
        comment.setComment(rs.getString(Fields.COMMENT));
        return comment;
    }
}
