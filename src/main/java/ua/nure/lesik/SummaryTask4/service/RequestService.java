package ua.nure.lesik.SummaryTask4.service;

import ua.nure.lesik.SummaryTask4.entity.Request;

import java.util.List;

/**
 * Interface defines methods for request service
 *
 * @author Ruslan Lesik
 */
public interface RequestService {

    /**
     * Create request
     *
     * @param request Request
     *
     * @return true if request has been created
     */
    boolean createRequest(Request request);

    /**
     * Find list of requests
     *
     * @return List of request
     */
    List<Request> getAllRequests();
}
