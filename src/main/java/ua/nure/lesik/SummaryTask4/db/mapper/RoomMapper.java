package ua.nure.lesik.SummaryTask4.db.mapper;

import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.entity.enums.RoomClasses;
import ua.nure.lesik.SummaryTask4.entity.enums.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Room object retrieves
 *
 * @author Ruslan Lesik
 */
public class RoomMapper implements Mapper<Room> {

    @Override
    public Room extract(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt(Fields.ID));
        room.setNumber_of_places(rs.getInt(Fields.NUMBER_OF_PLACES));
        room.setPrice(rs.getDouble(Fields.PRICE));
        room.setRoom_classes(RoomClasses.valueOf(rs.getString(Fields.ROOM_CLASSES)));
        room.setRoom_status(Status.valueOf(rs.getString(Fields.ROOM_STATUS)));
        room.setDescription(rs.getString(Fields.DESCRIPTION));
        room.setImage_name(rs.getString(Fields.IMAGE_NAME));
        return room;
    }
}
