package ua.nure.lesik.SummaryTask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The class designed to control a character encoding
 *
 * @author Ruslan Lesik
 */

public class EncodingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void destroy() {
        LOG.debug("Filter destruction finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        LOG.debug("Encoding filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        LOG.trace("Request uri --> " + httpRequest.getRequestURI());
        LOG.trace("Request encoding -->" + request.getCharacterEncoding());
        LOG.trace("Response encoding -->" + response.getCharacterEncoding());
        LOG.debug("Encoding filter finished");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml --> " + encoding);
        LOG.debug("Filter initialization finished");
    }
}