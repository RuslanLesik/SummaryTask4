package ua.nure.lesik.SummaryTask4.extractor;

import ua.nure.lesik.SummaryTask4.db.mapper.Fields;
import ua.nure.lesik.SummaryTask4.entity.Request;
import ua.nure.lesik.SummaryTask4.entity.enums.RoomClasses;

import javax.servlet.http.HttpServletRequest;

/**
 * The class designed to extract Request entity
 *
 * @author Ruslan Lesik
 */
public class RequestExtractor implements Extractor<Request>{

    @Override
    public Request extract(HttpServletRequest request) {
        Request req = new Request();
        req.setUser_login(request.getParameter(Fields.USER_LOGIN_REQUEST));
        req.setNumbers_of_places(Integer.parseInt(request.getParameter(Fields.NUMBER_OF_PLACES)));
        req.setRoom_classes(RoomClasses.valueOf(request.getParameter(Fields.ROOM_CLASSES)));
        req.setNumber_of_days(Integer.parseInt(request.getParameter(Fields.NUMBER_OF_DAYS)));
        req.setStatus_request(Boolean.parseBoolean(request.getParameter(Fields.STATUS_REQUEST)));
        return req;
    }
}
