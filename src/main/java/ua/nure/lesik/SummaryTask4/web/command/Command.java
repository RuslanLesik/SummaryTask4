package ua.nure.lesik.SummaryTask4.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Ruslan Lesik
 */
@FunctionalInterface
public interface Command extends Serializable {

    String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;
}
