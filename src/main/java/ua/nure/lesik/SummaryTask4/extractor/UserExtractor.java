package ua.nure.lesik.SummaryTask4.extractor;

import ua.nure.lesik.SummaryTask4.db.mapper.Fields;
import ua.nure.lesik.SummaryTask4.entity.enums.Role;
import ua.nure.lesik.SummaryTask4.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * The class designed to extract User entity
 *
 * @author Ruslan Lesik
 */
public class UserExtractor implements Extractor<User> {

    @Override
    public User extract(HttpServletRequest request){
        User user = new User();
        user.setLogin(request.getParameter(Fields.USER_LOGIN));
        user.setPassword(request.getParameter(Fields.USER_PASSWORD));
        user.setFirst_name(request.getParameter(Fields.USER_FIRST_NAME));
        user.setLast_name(request.getParameter(Fields.USER_LAST_NAME));
        user.setPatronymic(request.getParameter(Fields.USER_PATRONYMIC));
        user.setEmail(request.getParameter(Fields.USER_EMAIL));
        user.setPhone_number(request.getParameter(Fields.USER_PHONE_NUMBER));
        user.setRole(Role.CLIENT);
        return user;
    }
}
