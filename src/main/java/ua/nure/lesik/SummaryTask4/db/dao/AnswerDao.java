package ua.nure.lesik.SummaryTask4.db.dao;

import ua.nure.lesik.SummaryTask4.entity.Answer;

import java.sql.Connection;

/**
 * Interface defines methods for answer dao
 *
 * @author Ruslan Lesik
 */
public interface AnswerDao {

    long deleteAnswerByUserLoginAndRoomID(String user_login, int roomId, Connection connection);

    long addAnswer(Answer answer, Connection connection);
}
