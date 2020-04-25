package ua.nure.lesik.SummaryTask4.service.serviceImpl;

import ua.nure.lesik.SummaryTask4.db.dao.AnswerDao;
import ua.nure.lesik.SummaryTask4.db.dao.RequestDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.Answer;
import ua.nure.lesik.SummaryTask4.service.AnswerService;

/**
 * The class is a service for working with answer
 *
 * @author Ruslan Lesik
 */
public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao;
    private RequestDao requestDao;
    private TransactionManager transactionManager;

    public AnswerServiceImpl(AnswerDao answerDao, RequestDao requestDao, TransactionManager transactionManager) {
        this.answerDao = answerDao;
        this.requestDao = requestDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean createAnswer(Answer answer, int requestId) {
        return transactionManager.executeOperation(connection ->
        {
            long key = answerDao.addAnswer(answer, connection);
            requestDao.deleteRequestById(requestId, connection);
            return key !=0;
        });
    }

    @Override
    public boolean deleteAnswerByUserLoginAndRoomID(String user_login, int roomId) {
        long key = transactionManager.executeOperation(connection -> answerDao.deleteAnswerByUserLoginAndRoomID(user_login, roomId, connection));
        return key !=0;
    }
}
