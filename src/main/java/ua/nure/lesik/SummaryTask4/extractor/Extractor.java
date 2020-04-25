package ua.nure.lesik.SummaryTask4.extractor;

import javax.servlet.http.HttpServletRequest;

/**
 * Basic interface for all Extractor
 *
 * @author Ruslan Lesik
 */
public interface Extractor<E> {
    E extract(HttpServletRequest request);
}
