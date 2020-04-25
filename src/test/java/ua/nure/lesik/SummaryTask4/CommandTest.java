package ua.nure.lesik.SummaryTask4;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.UserServiceImpl;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.LanguageCommand;
import ua.nure.lesik.SummaryTask4.web.command.LoginCommand;
import ua.nure.lesik.SummaryTask4.web.command.LogoutCommand;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Ruslan Lesik
 */
@RunWith(MockitoJUnitRunner.class)
public class CommandTest {

    private static User user;

    @InjectMocks
    private static LanguageCommand languageCommand;

    @InjectMocks
    private static LogoutCommand logoutCommand;

    @InjectMocks
    private static LoginCommand loginCommand;

    @Mock
    private static UserServiceImpl userService;

    @Mock
    private static ServletContext servletContext;

    @Mock
    private static HttpServletRequest request;

    @Mock
    private static HttpServletResponse response;

    @Mock
    private static HttpSession session;

    @BeforeClass
    public static void setUp() {
        user = new User();
        user.setLogin("admin");
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");
        PropertyConfigurator.configure("src/main/webapp/WEB-INF/log4j.properties");
    }

    @Test
    public void testLanguageCommand() throws ServletException, IOException {
        when(request.getParameter("lang")).thenReturn("ru");
        when(request.getSession(false)).thenReturn(session);
        assertEquals(Path.COMMAND_PROFILE, languageCommand.execute(request, response));
        verify(request).getSession(false);
    }

    @Test
    public void testLogoutCommand() {
        when(request.getSession(false)).thenReturn(session);
        assertEquals(Path.COMMAND_INDEX, logoutCommand.execute(request, response));
        verify(request).getSession(false);
    }

    @Test
    public void testLoginCommand() {
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext().getAttribute("userService")).thenReturn(userService);
        when(userService.getUserByLogin("admin")).thenReturn(user);
        when(request.getSession(true)).thenReturn(session);

        when(request.getParameter("captchaCode")).thenReturn("code");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("captcha")).thenReturn("code");

        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");

        assertEquals(Path.COMMAND_PROFILE, loginCommand.execute(request, response));
        verify(userService).getUserByLogin(any(String.class));
    }
}
