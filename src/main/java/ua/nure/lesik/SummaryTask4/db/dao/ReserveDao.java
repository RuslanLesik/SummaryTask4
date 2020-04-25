package ua.nure.lesik.SummaryTask4.db.dao;

import ua.nure.lesik.SummaryTask4.entity.Reserve;

import java.sql.Connection;
import java.util.List;

/**
 * Interface defines methods for reserve dao
 *
 * @author Ruslan Lesik
 */
public interface ReserveDao {

    long deleteReserveById(int id, Connection connection);

    long changeReserveStatusById(int id, Connection connection);

    long addReserve(Reserve reserve, Connection connection);

    List<Reserve> getAllReserves(Connection connection);

    Reserve getReserveByDateUserAndRoomID(String check_in, String check_out, int userId, int room_id, Connection connection);

    List<Reserve> getAllReservesByRoomId(int roomId, Connection connection);

    List<Reserve> getAllReservesByRoomAndUserId(int roomId, int userId, Connection connection);
}
