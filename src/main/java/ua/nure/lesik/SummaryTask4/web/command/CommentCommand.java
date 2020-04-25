package ua.nure.lesik.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Comment;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.CommentService;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The class for processing comments
 *
 * @author Ruslan Lesik
 */
public class CommentCommand implements Command {

    private static final Logger LOG = Logger.getLogger(CommentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command comment start!");
        CommentService commentService = (CommentService) request.getServletContext().getAttribute("commentService");

        RoomService roomService = (RoomService) request.getServletContext().getAttribute("roomService");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        Room room = roomService.getRoomById(roomId);
        request.setAttribute("room", room);

        if ("GET".equals(request.getMethod())) {
            List<Comment> commentList = commentService.getAllCommentsByRoomId(roomId);
            request.setAttribute("commentList", commentList);
            LOG.debug("Command comment finished, without create comment!");
            return Path.PAGE_COMMENT;
        }

        String add_comment = request.getParameter("add_comment");
        String delete_comment = request.getParameter("delete_comment");

        if (add_comment != null) {
            Comment comment = createComment(request);
            boolean isValidLength = Validator.isValidateLengthComment(comment.getComment());
            if (!isValidLength) {
                request.setAttribute("errorMessage", "Max length of comment should be 200 symbols!");
                LOG.error("Command comment, not valid comment length!");
                return Path.PAGE_ERROR_PAGE;
            }
            commentService.addComment(comment);
            LOG.debug("Command comment, create a new comment!");
        }

        if (delete_comment != null) {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            commentService.deleteCommentById(commentId);
            LOG.debug("Command comment, delete a comment!");
        }
        LOG.trace("Command comment finished!");
        return Path.COMMAND_COMMENT + roomId;
    }

    private Comment createComment(HttpServletRequest request) {
        String format = "E, dd MMM yyyy, HH:mm:ss";
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        User user = (User) request.getSession(false).getAttribute("user");
        int room_id = Integer.parseInt(request.getParameter("roomId"));
        String message = request.getParameter("message");
        Comment comment = new Comment();
        comment.setUser_login(user.getLogin());
        comment.setDate_create(localDate);
        comment.setRoom_id(room_id);
        comment.setComment(message);
        return comment;
    }
}
