package ua.nure.lesik.SummaryTask4.web;

/**
 * Contains pages and commands
 *
 * @author Ruslan Lesik
 */
public final class Path {

    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_INDEX = "/index.jsp";
    public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/registration.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_PROFILE = "/WEB-INF/jsp/profile.jsp";
    public static final String PAGE_ADD_ROOM = "/WEB-INF/jsp/manager/add_room.jsp";
    public static final String PAGE_USER_LIST = "/WEB-INF/jsp/manager/user_list.jsp";
    public static final String PAGE_REQUEST_LIST = "/WEB-INF/jsp/manager/request_list.jsp";
    public static final String PAGE_REQUEST = "/WEB-INF/jsp/client/request.jsp";
    public static final String PAGE_RESERVATION = "/WEB-INF/jsp/client/reservation.jsp";
    public static final String PAGE_INVOICE = "/WEB-INF/jsp/client/invoice.jsp";
    public static final String PAGE_LIST_RESERVATION = "/WEB-INF/jsp/client/list_reservation.jsp";
    public static final String PAGE_INFO_RESERVATION = "/WEB-INF/jsp/client/info_reservation.jsp";
    public static final String PAGE_COMMENT = "/WEB-INF/jsp/comment.jsp";

    public static final String COMMAND_PROFILE = "controller?command=profile";
    public static final String COMMAND_LOGIN = "controller?command=login";
    public static final String COMMAND_USER_LIST = "controller?command=user_list";
    public static final String COMMAND_INDEX = "controller?command=index";
    public static final String COMMAND_REQUEST_LIST = "controller?command=request_list";
    public static final String COMMAND_REQUEST = "controller?command=client_request";
    public static final String COMMAND_INVOICE = "controller?command=invoice";
    public static final String COMMAND_COMMENT = "controller?command=comment&roomId=";
}