package ua.nure.lesik.SummaryTask4.db.dao;

import ua.nure.lesik.SummaryTask4.entity.Messages;

import java.sql.Connection;
import java.util.List;

/**
 * Interface defines methods for messages dao
 *
 * @author Ruslan Lesik
 */
public interface MessagesDao {

    long deleteMessageById(int id, Connection connection);

    long addMessage(Messages messages, Connection connection);

    List<Messages> getAllMessagesByUserLogin(String userLogin, Connection connection);
}
