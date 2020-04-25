package ua.nure.lesik.SummaryTask4.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlAnswerDao;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlCommentDao;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlInvoiceDao;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlMessagesDao;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlRequestDao;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlReserveDao;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlRoomDao;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlUserDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.Answer;
import ua.nure.lesik.SummaryTask4.entity.Comment;
import ua.nure.lesik.SummaryTask4.entity.Invoice;
import ua.nure.lesik.SummaryTask4.entity.Messages;
import ua.nure.lesik.SummaryTask4.entity.Request;
import ua.nure.lesik.SummaryTask4.entity.Reserve;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.AnswerService;
import ua.nure.lesik.SummaryTask4.service.CommentService;
import ua.nure.lesik.SummaryTask4.service.InvoiceService;
import ua.nure.lesik.SummaryTask4.service.MessagesService;
import ua.nure.lesik.SummaryTask4.service.RequestService;
import ua.nure.lesik.SummaryTask4.service.ReserveService;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.service.UserService;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.AnswerServiceImpl;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.CommentServiceImpl;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.InvoiceServiceImpl;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.MessagesServiceImpl;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.RequestServiceImpl;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.ReserveServiceImpl;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.RoomServiceImpl;
import ua.nure.lesik.SummaryTask4.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Locale;

/**
 * Context listener
 *
 * @author Ruslan Lesik
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext context = event.getServletContext();
        initLog4J(context);
        Locale.setDefault(new Locale("en"));

        TransactionManager transactionManager = new TransactionManager();
        JdbcTemplate<User> userJdbcTemplate = new JdbcTemplate<>();
        JdbcTemplate<Room> roomJdbcTemplate = new JdbcTemplate<>();
        JdbcTemplate<Request> requestJdbcTemplate = new JdbcTemplate<>();
        JdbcTemplate<Reserve> reserveJdbcTemplate = new JdbcTemplate<>();
        JdbcTemplate<Invoice> invoiceJdbcTemplate = new JdbcTemplate<>();
        JdbcTemplate<Answer> answerJdbcTemplate = new JdbcTemplate<>();
        JdbcTemplate<Messages> messagesJdbcTemplate = new JdbcTemplate<>();
        JdbcTemplate<Comment> commentJdbcTemplate = new JdbcTemplate<>();

        MysqlRequestDao mysqlRequestDao = new MysqlRequestDao(requestJdbcTemplate);
        MysqlReserveDao mysqlReserveDao = new MysqlReserveDao(reserveJdbcTemplate);

        UserService userService = new UserServiceImpl(new MysqlUserDao(userJdbcTemplate), transactionManager);
        RoomService roomService = new RoomServiceImpl(new MysqlRoomDao(roomJdbcTemplate), transactionManager);
        RequestService requestService = new RequestServiceImpl(mysqlRequestDao, transactionManager);
        ReserveService reserveService = new ReserveServiceImpl(mysqlReserveDao, transactionManager);
        InvoiceService invoiceService = new InvoiceServiceImpl(new MysqlInvoiceDao(invoiceJdbcTemplate),mysqlReserveDao, transactionManager);
        AnswerService answerService = new AnswerServiceImpl(new MysqlAnswerDao(answerJdbcTemplate),mysqlRequestDao, transactionManager);
        MessagesService messagesService = new MessagesServiceImpl(new MysqlMessagesDao(messagesJdbcTemplate),mysqlRequestDao, transactionManager);
        CommentService commentService = new CommentServiceImpl(new MysqlCommentDao(commentJdbcTemplate), transactionManager);

        context.setAttribute("userService", userService);
        context.setAttribute("roomService", roomService);
        context.setAttribute("requestService", requestService);
        context.setAttribute("reserveService", reserveService);
        context.setAttribute("invoiceService", invoiceService);
        context.setAttribute("answerService", answerService);
        context.setAttribute("messagesService", messagesService);
        context.setAttribute("commentService", commentService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("Servlet context destruction finished");
    }

    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            LOG.error("Cannot configure Log4j", ex);
        }
        log("Log4J initialization finished");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}
