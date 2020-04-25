package ua.nure.lesik.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.entity.enums.Role;
import ua.nure.lesik.SummaryTask4.web.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * The class provides a security function for accessing commands.
 *
 * @author Ruslan Lesik
 */
public class AccessFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AccessFilter.class);

    private Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();



    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts");

        if (accessAllowed(request)) {
            LOG.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessage);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessage);

            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        User user = (User) session.getAttribute("user");
        if (user.getRole() == null) {
            return false;
        }

        return accessMap.get(user.getRole()).contains(commandName)
                || commons.contains(commandName);
    }

    public void init(FilterConfig fConfig){
        LOG.debug("Filter initialization starts");

        accessMap.put(Role.MANAGER, asList(fConfig.getInitParameter("manager")));
        accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter("client")));
        LOG.trace("Access map --> " + accessMap);

        commons = asList(fConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("Filter initialization finished");
    }

    public void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction finished");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}