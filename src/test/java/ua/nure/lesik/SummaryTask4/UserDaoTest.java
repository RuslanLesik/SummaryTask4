package ua.nure.lesik.SummaryTask4;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.dao.UserDao;
import ua.nure.lesik.SummaryTask4.db.dao.mysql.MysqlUserDao;
import ua.nure.lesik.SummaryTask4.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Ruslan Lesik
 */
public class UserDaoTest {

    private static final String VALID_NAME = "student";
    private static final String INVALID_NAME = "fail";
    private static final String URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;
    private static UserDao userDao;


    @BeforeClass
    public static void setUp() {
        PropertyConfigurator.configure("src/main/webapp/WEB-INF/log4j.properties");
        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();
        userDao = new MysqlUserDao(jdbcTemplate);
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Test
    public void testUserDaoGetUserByLogin() {
        User user = userDao.getUserByLogin(VALID_NAME, connection);
        assertNotNull(user);
        assertEquals(VALID_NAME, user.getLogin());
        assertNotEquals(INVALID_NAME, user.getLogin());
    }

    @Test
    public void testUserDaoGetListOfAllUsers() {
        List<User> userList = userDao.getAllUsers(connection);
        assertNotNull(userList);
    }
}
