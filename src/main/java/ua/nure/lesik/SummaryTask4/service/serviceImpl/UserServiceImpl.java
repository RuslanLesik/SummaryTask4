package ua.nure.lesik.SummaryTask4.service.serviceImpl;

import ua.nure.lesik.SummaryTask4.db.dao.UserDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.UserService;

import java.util.List;

/**
 * The class is a service for working with user
 *
 * @author Ruslan Lesik
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean createUser(User user) {
        long key = transactionManager.executeOperation(connection -> userDao.addUser(user, connection));
        return key != 0;
    }

    @Override
    public User getUserByLogin(String login) {
        return transactionManager.executeOperation(connection -> userDao.getUserByLogin(login, connection));
    }

    @Override
    public List<User> getAllUsers() {
        return transactionManager.executeOperation(connection -> userDao.getAllUsers(connection));
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        long key = transactionManager.executeOperation(connection -> userDao.deleteUserByLogin(login, connection));
        return key != 0;
    }
}
