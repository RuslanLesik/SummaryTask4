package ua.nure.lesik.SummaryTask4.extractor;

import ua.nure.lesik.SummaryTask4.db.mapper.Fields;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.entity.enums.RoomClasses;
import ua.nure.lesik.SummaryTask4.entity.enums.Status;

import javax.servlet.http.HttpServletRequest;

/**
 * The class designed to extract Room entity
 *
 * @author Ruslan Lesik
 */
public class RoomExtractor implements Extractor<Room> {
    @Override
    public Room extract(HttpServletRequest request) {
        Room room = new Room();
        room.setNumber_of_places(Integer.parseInt(request.getParameter(Fields.NUMBER_OF_PLACES)));
        room.setPrice(Double.parseDouble(request.getParameter(Fields.PRICE)));
        room.setRoom_classes(RoomClasses.valueOf(request.getParameter(Fields.ROOM_CLASSES)));
        room.setDescription(request.getParameter(Fields.DESCRIPTION));
        room.setRoom_status(Status.FREE);
        return room;
    }
}
