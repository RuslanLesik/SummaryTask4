package ua.nure.lesik.SummaryTask4.extractor;

import ua.nure.lesik.SummaryTask4.db.mapper.Fields;
import ua.nure.lesik.SummaryTask4.entity.Answer;

import javax.servlet.http.HttpServletRequest;

/**
 * The class designed to extract Answer entity
 *
 * @author Ruslan Lesik
 */
public class AnswerExtractor implements Extractor<Answer>{

    @Override
    public Answer extract(HttpServletRequest request) {
        Answer answer = new Answer();
        answer.setRoom_id(Integer.parseInt(request.getParameter(Fields.ROOM_ID)));
        answer.setUser_login(request.getParameter(Fields.USER_LOGIN_REQUEST));
        return answer;
    }
}
