package ua.nure.lesik.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.UserService;
import ua.nure.lesik.SummaryTask4.util.UtilHash;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * The class for authentication
 *
 * @author Ruslan Lesik
 */
public class LoginCommand implements Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command login start");
        if ("GET".equals(request.getMethod())) {
            LOG.debug("Command login finished, forward address login page!");
            return Path.PAGE_LOGIN;
        }

        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        String path = Path.COMMAND_PROFILE;

        boolean isValidCaptcha = Validator.isValidateCaptcha(request);
        LOG.debug("Validate captcha is --> " + isValidCaptcha);
        if (!isValidCaptcha) {
            LOG.warn("Command login failed, incorrect captcha!");
            request.setAttribute("errorMessage", "Incorrect captcha!");
            return Path.PAGE_LOGIN;
        }

        User user = userService.getUserByLogin(request.getParameter("login"));
        String password = UtilHash.encryptPassword(request.getParameter("password"));

        if (user == null || !user.getPassword().equals(password)) {
            LOG.error("User not found or password do not match!");
            path = Path.PAGE_ERROR_PAGE;
            request.setAttribute("errorMessage", "Login and password do not match!");
        } else {
            List<String> languages = Arrays.asList("en", "ru");
            HttpSession session = request.getSession(true);
            user.setPassword("");
            session.setAttribute("user", user);
            session.setAttribute("languages", languages);
            session.setAttribute("language", "en");
            LOG.trace("Command login finished, user login is --> " + user.getLogin());
        }
        return path;
    }
}
