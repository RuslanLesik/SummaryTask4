package ua.nure.lesik.SummaryTask4.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class for obtain and processing room
 *
 * @author Ruslan Lesik
 */
public class ChangeRoomCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ChangeRoomCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command change_room start!");
        RoomService roomService = (RoomService) request.getServletContext().getAttribute("roomService");
        String disable_room_id = request.getParameter("disable_room_id");
        String enable_room_id = request.getParameter("enable_room_id");
        String delete_room_id = request.getParameter("delete_room_id");

        if (enable_room_id != null) {
            roomService.enableRoom(Integer.parseInt(enable_room_id));
            LOG.debug("Command change_room, enable room!");
        }

        if (disable_room_id != null) {
            roomService.disableRoom(Integer.parseInt(disable_room_id));
            LOG.debug("Command change_room, disable room!");
        }

        if (delete_room_id != null) {
            roomService.deleteRoomById(Integer.parseInt(delete_room_id));
            LOG.debug("Command change_room, delete room!");
        }

        LOG.trace("Command change_room finished!");
        return Path.COMMAND_PROFILE;
    }
}
