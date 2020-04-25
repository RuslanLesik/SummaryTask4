package ua.nure.lesik.SummaryTask4.service.serviceImpl;

import ua.nure.lesik.SummaryTask4.db.dao.CommentDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.Comment;
import ua.nure.lesik.SummaryTask4.service.CommentService;

import java.util.List;

/**
 * The class is a service for working with comment
 *
 * @author Ruslan Lesik
 */
public class CommentServiceImpl implements CommentService{

    private CommentDao commentDao;
    private TransactionManager transactionManager;

    public CommentServiceImpl(CommentDao commentDao, TransactionManager transactionManager) {
        this.commentDao = commentDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean addComment(Comment comment) {
        long key = transactionManager.executeOperation(connection -> commentDao.addComment(comment, connection));
        return key !=0;
    }

    @Override
    public boolean deleteCommentById(int comment_id) {
        long key = transactionManager.executeOperation(connection -> commentDao.deleteCommentById(comment_id, connection));
        return key !=0;
    }

    @Override
    public List<Comment> getAllCommentsByRoomId(int room_id) {
        return transactionManager.executeOperation(connection -> commentDao.getAllCommentsByRoomId(room_id, connection));
    }
}
