package ua.nure.lesik.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Reserve;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.ReserveService;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class for obtain and processing list of reservations
 *
 * @author Ruslan Lesik
 */
public class ReservationListCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ReservationListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command list_reservation start!");
        User user = (User) request.getSession(false).getAttribute("user");
        if ("GET".equals(request.getMethod())) {
            RoomService roomService = (RoomService) request.getServletContext().getAttribute("roomService");

            List<Room> roomList = roomService.getAllRoomsByUserId(user.getId());
            request.setAttribute("roomList", roomList);
            return Path.PAGE_LIST_RESERVATION;
        }
        ReserveService reserveService = (ReserveService) request.getServletContext().getAttribute("reserveService");
        String reserve_id = request.getParameter("reserveId");
        if (reserve_id != null) {
            int reserveId = Integer.parseInt(reserve_id);
            reserveService.deleteReserveById(reserveId);
            LOG.debug("Command list_reservation, delete reserve!");
        }

        String room_id = request.getParameter("roomId");
        if (room_id != null) {
            int roomId = Integer.parseInt(room_id);
            List<Reserve> reserveList = reserveService.getAllReservesByRoomAndUserId(roomId, user.getId());
            request.setAttribute("reserveList", reserveList);
            LOG.debug("Command list_reservation, obtain list of reserve!");
        }

        LOG.trace("Command list_reservation finished!");
        return Path.PAGE_INFO_RESERVATION;
    }
}
