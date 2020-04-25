package ua.nure.lesik.SummaryTask4.service.serviceImpl;

import ua.nure.lesik.SummaryTask4.db.dao.MessagesDao;
import ua.nure.lesik.SummaryTask4.db.dao.RequestDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.Messages;
import ua.nure.lesik.SummaryTask4.service.MessagesService;

import java.util.List;

/**
 * The class is a service for working with messages
 *
 * @author Ruslan Lesik
 */
public class MessagesServiceImpl implements MessagesService {

    private MessagesDao messagesDao;
    private RequestDao requestDao;
    private TransactionManager transactionManager;

    public MessagesServiceImpl(MessagesDao messagesDao, RequestDao requestDao, TransactionManager transactionManager) {
        this.messagesDao = messagesDao;
        this.requestDao = requestDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean deleteMessageById(int id) {
        long key = transactionManager.executeOperation(connection -> messagesDao.deleteMessageById(id, connection));
        return key != 0;
    }

    @Override
    public boolean addMessage(Messages messages, int requestId) {
        return transactionManager.executeOperation(connection -> {
            long key = messagesDao.addMessage(messages, connection);
            requestDao.deleteRequestById(requestId, connection);
            return key != 0;
        });
    }

    @Override
    public List<Messages> getAllMessagesByUserLogin(String userLogin) {
        return transactionManager.executeOperation(connection -> messagesDao.getAllMessagesByUserLogin(userLogin, connection));
    }
}
