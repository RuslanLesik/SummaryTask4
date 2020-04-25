package ua.nure.lesik.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class for presenting the welcome page
 *
 * @author Ruslan Lesik
 */
public class IndexCommand implements Command {

    private static final Logger LOG = Logger.getLogger(IndexCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command index started and finished!");
        return Path.PAGE_INDEX;
    }
}
