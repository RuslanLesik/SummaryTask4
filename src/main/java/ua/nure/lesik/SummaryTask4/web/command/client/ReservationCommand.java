package ua.nure.lesik.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Reserve;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.service.ReserveService;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class for create a new reservation
 *
 * @author Ruslan Lesik
 */
public class ReservationCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ReservationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command reservation start!");
        RoomService roomService = (RoomService) request.getServletContext().getAttribute("roomService");
        ReserveService reserveService = (ReserveService) request.getServletContext().getAttribute("reserveService");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        Room room = roomService.getRoomById(roomId);
        List<Reserve> reserveList = reserveService.getAllReservesByRoomId(roomId);
        request.setAttribute("room", room);
        request.setAttribute("reserveList", reserveList);
        LOG.trace("Command reservation finished!");
        return Path.PAGE_RESERVATION;

    }
}
