package ua.nure.lesik.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.AnswerService;
import ua.nure.lesik.SummaryTask4.service.MessagesService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class for deleting answers
 *
 * @author Ruslan Lesik
 */
public class DeleteAnswerCommand implements Command {

    private static final Logger LOG = Logger.getLogger(DeleteAnswerCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command delete_answer start!");
        AnswerService answerService = (AnswerService) request.getServletContext().getAttribute("answerService");
        MessagesService messagesService = (MessagesService) request.getServletContext().getAttribute("messagesService");

        String messageId = request.getParameter("messageId");
        String roomId = request.getParameter("roomId");

        if (messageId != null) {
            messagesService.deleteMessageById(Integer.parseInt(messageId));
            LOG.debug("Command delete_answer, deleted message!");
        }
        if (roomId != null) {
            User user = (User) request.getSession(false).getAttribute("user");
            answerService.deleteAnswerByUserLoginAndRoomID(user.getLogin(), Integer.parseInt(roomId));
            LOG.debug("Command delete_answer, deleted offered room message!");
        }
        LOG.trace("Command delete_answer finished!");
        return Path.COMMAND_REQUEST;
    }
}
