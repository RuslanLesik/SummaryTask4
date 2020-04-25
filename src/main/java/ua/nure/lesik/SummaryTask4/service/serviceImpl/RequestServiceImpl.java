package ua.nure.lesik.SummaryTask4.service.serviceImpl;

import ua.nure.lesik.SummaryTask4.db.dao.RequestDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.Request;
import ua.nure.lesik.SummaryTask4.service.RequestService;

import java.util.List;

/**
 * The class is a service for working with request
 *
 * @author Ruslan Lesik
 */
public class RequestServiceImpl implements RequestService {

    private RequestDao requestDao;
    private TransactionManager transactionManager;

    public RequestServiceImpl(RequestDao requestDao, TransactionManager transactionManager) {
        this.requestDao = requestDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean createRequest(Request request) {
        long key = transactionManager.executeOperation(connection -> requestDao.addRequest(request, connection));
        return key != 0;
    }

    @Override
    public List<Request> getAllRequests() {
        return transactionManager.executeOperation(connection -> requestDao.getAllRequests(connection));
    }
}
