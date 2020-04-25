package ua.nure.lesik.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.extractor.Extractor;
import ua.nure.lesik.SummaryTask4.extractor.UserExtractor;
import ua.nure.lesik.SummaryTask4.service.UserService;
import ua.nure.lesik.SummaryTask4.util.UtilHash;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;
import ua.nure.lesik.SummaryTask4.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class for registration a new user
 *
 * @author Ruslan Lesik
 */
public class RegistrationCommand implements Command {

    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command registration start!");
        boolean isSuccessfully = false;
        if ("GET".equals(request.getMethod())) {
            LOG.debug("Command registration, method GET!");
            return Path.PAGE_REGISTRATION;
        }
        LOG.trace("Command registration, create a new user!");
        String path = Path.COMMAND_LOGIN;
        Extractor<User> userExtractor = new UserExtractor();
        User user = userExtractor.extract(request);
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        boolean userExist = userService.getUserByLogin(user.getLogin()) != null;
        if (userExist) {
            request.setAttribute("errorMessage", "Cannot add new user, such login exists!");
            LOG.debug("Command registration, cannot add new user!");
            return Path.PAGE_ERROR_PAGE;
        }
        List<String> errorList = Validator.validateRegisterForm(user);
        if (!errorList.isEmpty()) {
            request.setAttribute("errorList", errorList);
            LOG.error("Command registration, validation failed!");
            path = Path.PAGE_ERROR_PAGE;
        } else {
            user.setPassword(UtilHash.encryptPassword(user.getPassword()));
            isSuccessfully = userService.createUser(user);
            LOG.debug("Command registration, try create a new user!");
        }
        if (!isSuccessfully) {
            request.setAttribute("errorMessage", "Registration is temporarily unavailable");
            LOG.error("Command registration, user not created!");
            path = Path.PAGE_ERROR_PAGE;
        }
        LOG.trace("Command registration finished!");
        return path;
    }
}
