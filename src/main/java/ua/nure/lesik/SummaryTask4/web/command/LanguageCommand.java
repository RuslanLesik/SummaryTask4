package ua.nure.lesik.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class for changing language
 *
 * @author Ruslan Lesik
 */
public class LanguageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("lang");
        LOG.trace("Current locale --> " + locale);
        HttpSession session = request.getSession(false);
        session.setAttribute("language", locale);
        LOG.trace("Language command finished!");
        return Path.COMMAND_PROFILE;
    }
}
