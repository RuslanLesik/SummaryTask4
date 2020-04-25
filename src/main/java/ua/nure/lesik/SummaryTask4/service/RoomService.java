package ua.nure.lesik.SummaryTask4.service;

import ua.nure.lesik.SummaryTask4.entity.Room;

import java.util.List;

/**
 * Interface defines methods for room service
 *
 * @author Ruslan Lesik
 */
public interface RoomService {

    /**
     * Create room
     *
     * @param room Room
     *
     * @return true if room has been created
     */
    boolean createRoom(Room room);

    /**
     * Find room by id
     *
     * @param id int
     *
     * @return room Room if room was found
     */
    Room getRoomById(int id);

    /**
     * Find all rooms
     *
     * @return List of rooms
     */
    List<Room> getAllRooms();

    /**
     * Delete room by id
     *
     * @param id int
     *
     * @return true if room has been deleted
     */
    boolean deleteRoomById(int id);

    /**
     * Enable room
     *
     * @param id int
     *
     * @return true if room has been enabled
     */
    boolean enableRoom(int id);

    /**
     * Disable room
     *
     * @param id int
     *
     * @return true if room has been disabled
     */
    boolean disableRoom(int id);

    /**
     * Find all rooms for answer by user login
     *
     * @param userLogin String
     *
     * @return List of rooms
     */
    List<Room> getAllRoomsAnswer(String userLogin);

    /**
     * Find all rooms by user id
     *
     * @param userId ind
     *
     * @return List of rooms
     */
    List<Room> getAllRoomsByUserId(int userId);
}
