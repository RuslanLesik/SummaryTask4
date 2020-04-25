package ua.nure.lesik.SummaryTask4.service;

import ua.nure.lesik.SummaryTask4.entity.User;

import java.util.List;

/**
 * Interface defines methods for user service
 *
 * @author Ruslan Lesik
 */
public interface UserService {

    /**
     * Create user
     *
     * @param user User
     *
     * @return true if user has been created
     */
    boolean createUser(User user);

    /**
     * Find user by login
     *
     * @param login String
     *
     * @return user User if user was found
     */
    User getUserByLogin(String login);

    /**
     * Finds all users
     *
     * @return List of users
     */
    List<User> getAllUsers();

    /**
     * Delete user by login
     *
     * @param login String
     *
     * @return true if user has been deleted
     */
    boolean deleteUserByLogin(String login);
}