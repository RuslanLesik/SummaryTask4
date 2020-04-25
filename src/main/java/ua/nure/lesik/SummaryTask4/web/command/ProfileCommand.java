package ua.nure.lesik.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Reserve;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.entity.enums.Status;
import ua.nure.lesik.SummaryTask4.service.ReserveService;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * The class for obtain and processing list of room
 *
 * @author Ruslan Lesik
 */
public class ProfileCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ProfileCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.trace("Command profile start");
        RoomService roomService = (RoomService) request.getServletContext().getAttribute("roomService");
        ReserveService reserveService = (ReserveService) request.getServletContext().getAttribute("reserveService");
        List<Room> roomList = roomService.getAllRooms();
        List<Reserve> reserveList = reserveService.getAllReserves();
        LocalDate currentDay = LocalDate.now();
        roomList.stream()
                .filter(room -> !room.getRoom_status().equals(Status.NOT_AVAILABLE))
                .forEach(room -> reserveList.stream()
                        .filter(reserve -> reserve.getRoom_id() == room.getId())
                        .forEach(reserve ->
                        {
                            LocalDate checkIn = LocalDate.parse(reserve.getCheck_in());
                            LocalDate checkOut = LocalDate.parse(reserve.getCheck_out());

                            if ((checkIn.isEqual(currentDay) || checkIn.isBefore(currentDay))
                                    && (checkOut.isEqual(currentDay) || checkOut.isAfter(currentDay))) {
                                if (reserve.isStatus_reserve()) {
                                    room.setRoom_status(Status.BUSY);
                                } else {
                                    room.setRoom_status(Status.BOOKED);
                                }
                            }
                        }));
        request.setAttribute("roomList", roomList);
        LOG.trace("Command profile finished");
        return Path.PAGE_PROFILE;
    }
}
