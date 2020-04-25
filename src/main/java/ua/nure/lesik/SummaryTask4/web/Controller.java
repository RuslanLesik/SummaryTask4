package ua.nure.lesik.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.web.command.Command;
import ua.nure.lesik.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class obtain the client's request data and process it.
 *
 * @author Ruslan Lesik
 */
@MultipartConfig
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter("command");
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command : " + command);
        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(req, resp);
        } catch (IOException e) {
            LOG.warn("Cannot obtain forward address!", e);
        }
        LOG.trace("Forward address : " + forward);
        LOG.debug("Controller finished, forward address : " + forward);

        if (forward.contains("controller?command")) {
            resp.sendRedirect(forward);
        } else {
            req.getRequestDispatcher(forward).forward(req, resp);
        }
    }
}
