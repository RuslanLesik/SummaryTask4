package ua.nure.lesik.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class for invalidate session
 *
 * @author Ruslan Lesik
 */
public class LogoutCommand implements Command {

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command logout starts");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        LOG.trace("Command logout finished");
        return Path.COMMAND_INDEX;
    }
}
