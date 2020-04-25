package ua.nure.lesik.SummaryTask4.web.validator;


import ua.nure.lesik.SummaryTask4.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The class provides data validation
 *
 * @author Ruslan Lesik
 */
public class Validator {

    /**
     * Checks the data from the registration form
     *
     * @param user User
     * @return list List<String> of errors
     */
    public static List<String> validateRegisterForm(User user) {
        List<String> list = new ArrayList<String>();
        if (!isValidEmail(user.getEmail())) {
            list.add("Email address is not correct: " + user.getEmail());
        }
        if (!isValidLogin(user.getLogin())) {
            list.add("Login is not correct: " + user.getLogin());
        }
        if (!isValidName(user.getFirst_name())) {
            list.add("First name is not correct: " + user.getFirst_name());
        }
        if (!isValidName(user.getLast_name())) {
            list.add("Last name is not correct: " + user.getLast_name());
        }
        if (!isValidPassword(user.getPassword())) {
            list.add("Password is too simple: " + user.getPassword());
        }
        if (!isValidPhoneNumber(user.getPhone_number())) {
            list.add("Phone number is not correct: " + user.getPassword());
        }

        return list;
    }

    /**
     * Validates the captcha
     *
     * @param request HttpServletRequest
     * @return true if the captcha is valid
     */
    public static boolean isValidateCaptcha(HttpServletRequest request) {
        String enteredCaptcha = request.getParameter("captchaCode");
        String captcha = (String) request.getSession().getAttribute("captcha");
        return captcha.equals(enteredCaptcha);
    }

    /**
     * Validates the comment length
     *
     * @param comment String
     * @return true if the price is valid
     */
    public static boolean isValidateLengthComment(String comment){
        return comment.length() > 0 && comment.length() < 201;
    }

    /**
     * Validates the price
     *
     * @param price double
     * @return true if the price is valid
     */
    public static boolean isValidatePrice(double price){
        return !(price == 0) && !(price > 10_000);
    }

    /**
     * Validates the description
     *
     * @param description String
     * @return true if the description is valid
     */
    public static boolean isValidateDescription(String description){
        return description.length() > 20 && description.length() < 201;
    }

    /**
     * Validates the number of days
     *
     * @param number int
     * @return true if the number is valid
     */
    public static boolean isValidateNumberOfDays(int number){
        return number > 0 && number < 90;
    }


    /**
     * Validates the email
     *
     * @param email String
     * @return true if the email is valid
     */
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Validates the login
     *
     * @param login String
     * @return true if the login is valid
     */
    private static boolean isValidLogin(String login) {
        String nameRegex = "^(?=.{2,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
        Pattern pat = Pattern.compile(nameRegex);
        if (login == null)
            return false;
        return pat.matcher(login).matches();
    }

    /**
     * Validates the phone
     *
     * @param phone String
     * @return true if the phone is valid
     */
    private static  boolean isValidPhoneNumber(String phone){
        String phoneRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        Pattern pat = Pattern.compile(phoneRegex);
        if (phone == null)
            return false;
        return pat.matcher(phone).matches();
    }

    /**
     * Validates the name or lastName
     *
     * @param name String
     * @return true if the name is valid
     */
    private static boolean isValidName(String name) {
        String nameRegex = "(\\p{L}{1,20})";
        Pattern pat = Pattern.compile(nameRegex);
        if (name == null)
            return false;
        return pat.matcher(name).matches();
    }

    /**
     * Validates the password
     *
     * @param password String
     * @return true if the password is valid
     */
    private static boolean isValidPassword(String password) {
        String nameRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";
        Pattern pat = Pattern.compile(nameRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }
}


