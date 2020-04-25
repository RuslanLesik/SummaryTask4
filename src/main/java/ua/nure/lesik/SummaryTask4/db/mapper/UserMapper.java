package ua.nure.lesik.SummaryTask4.db.mapper;

import ua.nure.lesik.SummaryTask4.entity.enums.Role;
import ua.nure.lesik.SummaryTask4.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User object retrieves
 *
 * @author Ruslan Lesik
 */
public class UserMapper implements Mapper<User>{

    @Override
    public User extract(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirst_name(rs.getString(Fields.USER_FIRST_NAME));
        user.setLast_name(rs.getString(Fields.USER_LAST_NAME));
        user.setPatronymic(rs.getString(Fields.USER_PATRONYMIC));
        user.setEmail(rs.getString(Fields.USER_EMAIL));
        user.setPhone_number(rs.getString(Fields.USER_PHONE_NUMBER));
        user.setRole(Role.valueOf(rs.getString(Fields.USER_ROLE)));
        return user;
    }
}
