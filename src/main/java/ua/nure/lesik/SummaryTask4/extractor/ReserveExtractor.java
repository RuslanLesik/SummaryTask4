package ua.nure.lesik.SummaryTask4.extractor;

import ua.nure.lesik.SummaryTask4.db.mapper.Fields;
import ua.nure.lesik.SummaryTask4.entity.Reserve;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * The class designed to extract Reserve entity
 *
 * @author Ruslan Lesik
 */
public class ReserveExtractor implements Extractor<Reserve> {
    @Override
    public Reserve extract(HttpServletRequest request) {
        Reserve reserve = new Reserve();
        reserve.setRoom_id(Integer.parseInt(request.getParameter(Fields.ROOM_ID)));
        reserve.setUser_id(Integer.parseInt(request.getParameter(Fields.USER_ID)));
        reserve.setCheck_in(request.getParameter(Fields.CHECK_IN));
        reserve.setCheck_out(request.getParameter(Fields.CHECK_OUT));
        reserve.setPrice(Double.parseDouble(request.getParameter(Fields.PRICE)));
        LocalDate currentDate = LocalDate.now();
        reserve.setDate_create(currentDate.toString());
        return reserve;
    }
}
