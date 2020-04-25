package ua.nure.lesik.SummaryTask4;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lesik.SummaryTask4.web.Controller;
import ua.nure.lesik.SummaryTask4.web.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * @author Ruslan Lesik
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @InjectMocks
    private static Controller controller;
    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;

    @BeforeClass
    public static void setUp() {
        PropertyConfigurator.configure("src/main/webapp/WEB-INF/log4j.properties");
    }

    @Test
    public void testControllerWithNoCommand() throws ServletException, IOException {
        when(request.getParameter("command")).thenReturn("fake command");
        when(request.getRequestDispatcher(Path.PAGE_ERROR_PAGE)).thenReturn(mock(RequestDispatcher.class));
        controller.service(request, response);
        verify(request).getRequestDispatcher(Path.PAGE_ERROR_PAGE);
    }

    @Test
    public void testControllerWithLogoutCommand() throws ServletException, IOException {
        when(request.getParameter("command")).thenReturn("logout");
        doNothing().when(response).sendRedirect(Path.COMMAND_INDEX);
        controller.service(request, response);
        verify(response).sendRedirect(Path.COMMAND_INDEX);
    }
}
