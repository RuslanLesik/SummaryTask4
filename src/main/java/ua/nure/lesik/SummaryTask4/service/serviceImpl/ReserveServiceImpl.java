package ua.nure.lesik.SummaryTask4.service.serviceImpl;

import ua.nure.lesik.SummaryTask4.db.dao.ReserveDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.Reserve;
import ua.nure.lesik.SummaryTask4.service.ReserveService;

import java.util.List;

/**
 * The class is a service for working with reserve
 *
 * @author Ruslan Lesik
 */
public class ReserveServiceImpl implements ReserveService {

    private ReserveDao reserveDao;
    private TransactionManager transactionManager;


    public ReserveServiceImpl(ReserveDao reserveDao, TransactionManager transactionManager) {
        this.reserveDao = reserveDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean createReserve(Reserve reserve) {
        long key = transactionManager.executeOperation(connection -> reserveDao.addReserve(reserve, connection));
        return key != 0;
    }

    @Override
    public List<Reserve> getAllReserves() {
        return transactionManager.executeOperation(connection -> reserveDao.getAllReserves(connection));
    }

    @Override
    public boolean deleteReserveById(int id) {
        long key = transactionManager.executeOperation(connection -> reserveDao.deleteReserveById(id, connection));
        return key != 0;
    }

    @Override
    public List<Reserve> getAllReservesByRoomId(int roomId) {
        return transactionManager.executeOperation(connection -> reserveDao.getAllReservesByRoomId(roomId, connection));
    }

    @Override
    public List<Reserve> getAllReservesByRoomAndUserId(int roomId, int userId) {
        return transactionManager.executeOperation(connection -> reserveDao.getAllReservesByRoomAndUserId(roomId,userId, connection));
    }

    @Override
    public Reserve getReserveByDateUserAndRoomID(String check_in, String check_out, int userId, int room_id) {
        return transactionManager.executeOperation(connection -> reserveDao.getReserveByDateUserAndRoomID(check_in,check_out,userId,room_id,connection));
    }
}
