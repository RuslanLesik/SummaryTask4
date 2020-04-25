package ua.nure.lesik.SummaryTask4.db.mapper;

import ua.nure.lesik.SummaryTask4.entity.Request;
import ua.nure.lesik.SummaryTask4.entity.enums.RoomClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Request object retrieves
 *
 * @author Ruslan Lesik
 */
public class RequestMapper implements Mapper<Request> {
    @Override
    public Request extract(ResultSet rs) throws SQLException {
        Request request = new Request();
        request.setId(rs.getInt(Fields.ID));
        request.setUser_login(rs.getString(Fields.USER_LOGIN_REQUEST));
        request.setNumbers_of_places(rs.getInt(Fields.NUMBER_OF_PLACES));
        request.setRoom_classes(RoomClasses.valueOf(rs.getString(Fields.ROOM_CLASSES)));
        request.setNumber_of_days(rs.getInt(Fields.NUMBER_OF_DAYS));
        request.setStatus_request(rs.getBoolean(Fields.STATUS_REQUEST));
        return request;
    }
}
