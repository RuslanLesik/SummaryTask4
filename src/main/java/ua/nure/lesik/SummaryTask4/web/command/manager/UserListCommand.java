package ua.nure.lesik.SummaryTask4.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.UserService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class for obtain and processing list of users
 *
 * @author Ruslan Lesik
 */
public class UserListCommand implements Command {

    private static final Logger LOG = Logger.getLogger(UserListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command user_list start!");
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        if ("GET".equals(request.getMethod())) {
            List<User> userList = userService.getAllUsers();
            userList.forEach(user -> user.setPassword(""));
            request.setAttribute("userList", userList);
            LOG.debug("Command user_list, get all users!");
            return Path.PAGE_USER_LIST;
        }

        LOG.debug("Command user_list, delete user!");
        String login = request.getParameter("login");
        boolean isSuccessfully = userService.deleteUserByLogin(login);
        if (!isSuccessfully) {
            request.setAttribute("errorMessage", "Cannot delete user!");
            LOG.error("Command user_list, cannot delete user!");
            return Path.PAGE_ERROR_PAGE;
        }
        LOG.trace("Command user_list finished!");
        return Path.COMMAND_USER_LIST;
    }
}
