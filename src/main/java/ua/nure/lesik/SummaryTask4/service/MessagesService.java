package ua.nure.lesik.SummaryTask4.service;

import ua.nure.lesik.SummaryTask4.entity.Messages;

import java.util.List;

/**
 * Interface defines methods for messages service
 *
 * @author Ruslan Lesik
 */
public interface MessagesService {

    /**
     * Delete messages by id
     *
     * @param id int
     *
     * @return true if messages has been deleted
     */
    boolean deleteMessageById(int id);

    /**
     * Create a new messages
     *
     * @param messages Messages
     * @param requestId int
     *
     * @return true if messages has been created
     */
    boolean addMessage(Messages messages, int requestId);

    /**
     * Find list of messages by user login
     *
     * @param userLogin String
     *
     * @return List of messages
     */
    List<Messages> getAllMessagesByUserLogin(String userLogin);
}
