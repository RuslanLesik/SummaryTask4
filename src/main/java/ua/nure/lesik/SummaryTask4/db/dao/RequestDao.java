package ua.nure.lesik.SummaryTask4.db.dao;

import ua.nure.lesik.SummaryTask4.entity.Request;

import java.sql.Connection;
import java.util.List;

/**
 * Interface defines methods for request dao
 *
 * @author Ruslan Lesik
 */
public interface RequestDao {

    long deleteRequestById(int id, Connection connection);

    long addRequest(Request request, Connection connection);

    List<Request> getAllRequests(Connection connection);
}
