package ua.nure.lesik.SummaryTask4.service.serviceImpl;

import ua.nure.lesik.SummaryTask4.db.dao.RoomDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.service.RoomService;

import java.util.List;

/**
 * The class is a service for working with room
 *
 * @author Ruslan Lesik
 */
public class RoomServiceImpl implements RoomService {

    private RoomDao roomDao;
    private TransactionManager transactionManager;

    public RoomServiceImpl(RoomDao roomDao, TransactionManager transactionManager) {
        this.roomDao = roomDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean createRoom(Room room) {
        long key = transactionManager.executeOperation(connection -> roomDao.addRoom(room, connection));
        return key != 0;
    }

    @Override
    public Room getRoomById(int id) {
        return transactionManager.executeOperation(connection -> roomDao.getRoomById(id, connection));
    }

    @Override
    public List<Room> getAllRooms() {
        return transactionManager.executeOperation(connection -> roomDao.getAllRooms(connection));
    }

    @Override
    public boolean deleteRoomById(int id) {
        long key = transactionManager.executeOperation(connection -> roomDao.deleteRoomById(id, connection));
        return key != 0;
    }

    @Override
    public boolean enableRoom(int id) {
        long key = transactionManager.executeOperation(connection -> roomDao.enableRoom(id, connection));
        return key != 0;
    }

    @Override
    public boolean disableRoom(int id) {
        long key = transactionManager.executeOperation(connection -> roomDao.disableRoom(id, connection));
        return key != 0;
    }

    @Override
    public List<Room> getAllRoomsAnswer(String userLogin) {
        return transactionManager.executeOperation(connection -> roomDao.getAllRoomsAnswer(userLogin, connection));
    }

    @Override
    public List<Room> getAllRoomsByUserId(int userId) {
        return transactionManager.executeOperation(connection -> roomDao.getAllRoomsByUserId(userId, connection));
    }
}
