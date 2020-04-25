package ua.nure.lesik.SummaryTask4.service;

import ua.nure.lesik.SummaryTask4.entity.Reserve;

import java.util.List;

/**
 * Interface defines methods for reserve service
 *
 * @author Ruslan Lesik
 */
public interface ReserveService {

    /**
     * Create reserve
     *
     * @param reserve Reserve
     *
     * @return true if reserve has been created
     */
    boolean createReserve(Reserve reserve);

    /**
     * Find reserve by date,user_id and room_id
     *
     * @param check_in String
     * @param check_out String
     * @param userId int
     * @param room_id int
     *
     * @return reserve Reserve
     */
    Reserve getReserveByDateUserAndRoomID(String check_in, String check_out, int userId, int room_id);

    /**
     * Find list of reserves
     *
     * @return List of reserve
     */
    List<Reserve> getAllReserves();

    /**
     * Delete reserve by id
     *
     * @param id int
     *
     * @return true if reserve has been deleted
     */
    boolean deleteReserveById(int id);

    /**
     * Find list of reserve by room_id
     *
     * @param roomId int
     *
     * @return List of reserve
     */
    List<Reserve> getAllReservesByRoomId(int roomId);

    /**
     * Find list of reserve by room_id and user_id
     *
     * @param roomId int
     * @param userId int
     *
     * @return List of reserve
     */
    List<Reserve> getAllReservesByRoomAndUserId(int roomId, int userId);
}
