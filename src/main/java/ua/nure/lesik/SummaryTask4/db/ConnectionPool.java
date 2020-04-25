package ua.nure.lesik.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.exeption.DBException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The class provides connection pool
 *
 * @author Ruslan Lesik
 */
public class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final Object MONITOR = new Object();
    private static ConnectionPool instance;
    private DataSource ds;

    /**
     * Used to get instance
     */
    public static ConnectionPool getInstance(){
        if (instance == null) {
            synchronized (MONITOR) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    /**
     * Initialization of objects Context and DataSourse
     * in the constructor
     */
    private ConnectionPool(){
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/Hotel");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error("Cannot obtain datasource object", ex);
            throw new DBException(ex);
        }
    }

    /**
     * Gets a connection to the database from the connection pool.
     *
     * @return DB connection.
     */
    public Connection getConnection() throws DBException {
        Connection con;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error("", ex);
            throw new DBException(ex);
        }
        return con;
    }
}

