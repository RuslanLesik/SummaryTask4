package ua.nure.lesik.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Messages;
import ua.nure.lesik.SummaryTask4.entity.Request;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.entity.enums.RoomClasses;
import ua.nure.lesik.SummaryTask4.extractor.Extractor;
import ua.nure.lesik.SummaryTask4.extractor.RequestExtractor;
import ua.nure.lesik.SummaryTask4.service.MessagesService;
import ua.nure.lesik.SummaryTask4.service.RequestService;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;
import ua.nure.lesik.SummaryTask4.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * The class for obtain and create request
 *
 * @author Ruslan Lesik
 */
public class RequestClientCommand implements Command {

    private static final Logger LOG = Logger.getLogger(RequestClientCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command client_request start!");
        if ("GET".equals(request.getMethod())) {
            List<RoomClasses> roomClasses = Arrays.asList(RoomClasses.STANDART, RoomClasses.DE_LUXE, RoomClasses.LUXE);
            User user = (User) request.getSession(false).getAttribute("user");
            RoomService roomService = (RoomService) request.getServletContext().getAttribute("roomService");
            List<Room> roomList = roomService.getAllRoomsAnswer(user.getLogin());
            MessagesService messagesService = (MessagesService) request.getServletContext().getAttribute("messagesService");
            List<Messages> messagesList = messagesService.getAllMessagesByUserLogin(user.getLogin());

            request.setAttribute("roomClasses", roomClasses);
            request.setAttribute("roomList", roomList);
            request.setAttribute("messagesList", messagesList);
            LOG.debug("Command client_request, method GET!");
            return Path.PAGE_REQUEST;
        }

        LOG.debug("Command client_request, create a new request!");
        Extractor<Request> requestExtractor = new RequestExtractor();
        Request req = requestExtractor.extract(request);
        boolean isValidNumberOfDays = Validator.isValidateNumberOfDays(req.getNumber_of_days());
        if (!isValidNumberOfDays) {
            request.setAttribute("errorMessage", "Booking period from 1 to 90 days!");
            LOG.error("Command client_request, not valid number of days!");
            return Path.PAGE_ERROR_PAGE;
        }
        RequestService requestService = (RequestService) request.getServletContext().getAttribute("requestService");
        requestService.createRequest(req);
        LOG.trace("Command client_request finished!");
        return Path.COMMAND_REQUEST;
    }
}
