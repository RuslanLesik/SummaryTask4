package ua.nure.lesik.SummaryTask4.db.mapper;

import ua.nure.lesik.SummaryTask4.entity.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Messages object retrieves
 *
 * @author Ruslan Lesik
 */
public class MessagesMapper implements Mapper<Messages>{
    @Override
    public Messages extract(ResultSet rs) throws SQLException {
        Messages messages = new Messages();
        messages.setId(rs.getInt(Fields.ID));
        messages.setUser_login(rs.getString(Fields.USER_LOGIN_REQUEST));
        messages.setMessage(rs.getString(Fields.MESSAGE));
        return messages;
    }
}
