package ua.nure.lesik.SummaryTask4.service;

import ua.nure.lesik.SummaryTask4.entity.Comment;

import java.util.List;

/**
 * Interface defines methods for comment service
 *
 * @author Ruslan Lesik
 */
public interface CommentService {

    /**
     * Create comment
     *
     * @param comment Comment
     *
     * @return true if comment has been created
     */
    boolean addComment(Comment comment);

    /**
     * Delete comment by id
     *
     * @param comment_id int
     *
     * @return true if comment has been deleted
     */
    boolean deleteCommentById(int comment_id);

    /**
     * Find list of comments by  room_id
     *
     * @param room_id int
     *
     * @return List of comments
     */
    List<Comment> getAllCommentsByRoomId(int room_id);
}
