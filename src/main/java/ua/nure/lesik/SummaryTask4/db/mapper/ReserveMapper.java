package ua.nure.lesik.SummaryTask4.db.mapper;

import ua.nure.lesik.SummaryTask4.entity.Reserve;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Reserve object retrieves
 *
 * @author Ruslan Lesik
 */
public class ReserveMapper implements Mapper<Reserve> {

    @Override
    public Reserve extract(ResultSet rs) throws SQLException {
        Reserve reserve = new Reserve();
        reserve.setId(rs.getInt(Fields.ID));
        reserve.setRoom_id(rs.getInt(Fields.ROOM_ID));
        reserve.setUser_id(rs.getInt(Fields.USER_ID));
        reserve.setDate_create(rs.getString(Fields.DATE_CREATE));
        reserve.setCheck_in(rs.getString(Fields.CHECK_IN));
        reserve.setCheck_out(rs.getString(Fields.CHECK_OUT));
        reserve.setStatus_reserve(rs.getBoolean(Fields.STATUS_RESERVE));
        return reserve;
    }
}
