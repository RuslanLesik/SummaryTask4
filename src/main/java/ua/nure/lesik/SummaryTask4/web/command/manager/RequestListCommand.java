package ua.nure.lesik.SummaryTask4.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Answer;
import ua.nure.lesik.SummaryTask4.entity.Messages;
import ua.nure.lesik.SummaryTask4.entity.Request;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.extractor.AnswerExtractor;
import ua.nure.lesik.SummaryTask4.extractor.Extractor;
import ua.nure.lesik.SummaryTask4.service.AnswerService;
import ua.nure.lesik.SummaryTask4.service.MessagesService;
import ua.nure.lesik.SummaryTask4.service.RequestService;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class for obtain and processing list of requests
 *
 * @author Ruslan Lesik
 */
public class RequestListCommand implements Command {

    private static final Logger LOG = Logger.getLogger(RequestListCommand.class);
    private final static String MESSAGE_FAIL = "Sorry at the moment there is no suitable room for you!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command request_list start!");
        RequestService requestService = (RequestService) request.getServletContext().getAttribute("requestService");
        RoomService roomService = (RoomService) request.getServletContext().getAttribute("roomService");

        if ("GET".equals(request.getMethod())) {
            List<Room> listRooms = roomService.getAllRooms();
            List<Request> requestList = requestService.getAllRequests();
            request.setAttribute("requestList", requestList);
            request.setAttribute("listRooms", listRooms);
            LOG.debug("Command request_list, obtain request list and room list!");
            return Path.PAGE_REQUEST_LIST;
        }

        if (request.getParameter("sendAnswer") != null) {
            int request_id = Integer.parseInt(request.getParameter("sendAnswer"));
            Extractor<Answer> answerExtractor = new AnswerExtractor();
            Answer answer = answerExtractor.extract(request);
            AnswerService answerService = (AnswerService) request.getServletContext().getAttribute("answerService");
            answerService.createAnswer(answer, request_id);
            LOG.debug("Command request_list, create an answer for user that found room!");
        }

        if (request.getParameter("notFound") != null) {
            Messages messages = new Messages();
            messages.setUser_login(request.getParameter("user_login"));
            messages.setMessage(MESSAGE_FAIL);
            int request_id = Integer.parseInt(request.getParameter("notFound"));
            MessagesService messagesService = (MessagesService) request.getServletContext().getAttribute("messagesService");
            messagesService.addMessage(messages, request_id);
            LOG.debug("Command request_list, create an answer for user that not found room!");
        }
        LOG.trace("Command request_list finished!");
        return Path.COMMAND_REQUEST_LIST;
    }
}
