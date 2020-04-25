package ua.nure.lesik.SummaryTask4.db.transaction;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.db.ConnectionPool;
import ua.nure.lesik.SummaryTask4.exeption.DBException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The class provides transaction execution
 *
 * @author Ruslan Lesik
 */
public class TransactionManager {

    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    public TransactionManager() {
    }

    public <E> E executeOperation(Operation<E> operation) {
        E result;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            result = operation.execute(connection);
            connection.commit();
        } catch (Throwable thr) {
            LOG.warn("Cannot do transaction! Do rollback.", thr);
            rollback(connection);
            throw new DBException(thr);
        } finally {
            close(connection);
        }
        return result;
    }

    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LOG.warn("Cannot close the connection!", e);
            }
        }
    }

    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                LOG.warn("Cannot rollback the transaction", e);
            }
        }
    }
}

