package ua.nure.lesik.SummaryTask4.service;

import ua.nure.lesik.SummaryTask4.entity.Answer;

/**
 * Interface defines methods for answer service
 *
 * @author Ruslan Lesik
 */
public interface AnswerService {

    /**
     * Create Answer
     *
     * @param answer Answer
     * @param requestId int
     *
     * @return true if answer has been created
     */
    boolean createAnswer(Answer answer, int requestId);

    /**
     * Delete answer by user_login and roomId
     *
     * @param user_login String
     * @param roomId int
     *
     * @return true if answer has been deleted
     */
    boolean deleteAnswerByUserLoginAndRoomID(String user_login, int roomId);
}
