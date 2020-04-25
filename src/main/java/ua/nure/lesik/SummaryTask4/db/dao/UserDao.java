package ua.nure.lesik.SummaryTask4.db.dao;

import ua.nure.lesik.SummaryTask4.entity.User;

import java.sql.Connection;
import java.util.List;

/**
 * Interface defines methods for user dao
 *
 * @author Ruslan Lesik
 */
public interface UserDao {

    User getUserByLogin(String login, Connection connection);

    long deleteUserByLogin(String login, Connection connection);

    long addUser(User user, Connection connection);

    List<User> getAllUsers(Connection connection);
}
