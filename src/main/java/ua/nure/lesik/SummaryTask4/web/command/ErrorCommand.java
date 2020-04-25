package ua.nure.lesik.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class for processing errors
 *
 * @author Ruslan Lesik
 */
public class ErrorCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ErrorCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command error started and finished!");
        return Path.PAGE_ERROR_PAGE;
    }
}
