package by.gsu.epamlab.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.PropertiesConstants;

public class PageFilter implements Filter {

    @Override
    public void init(final FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
        checkPage(request, response);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private void checkPage(final ServletRequest request, final ServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter(PropertiesConstants.PAGE) != null) {
            int page = Integer.valueOf(request.getParameter(PropertiesConstants.PAGE));

            if (page < 1) {
                request.setAttribute(PropertiesConstants.PAGE, 1);
                request.setAttribute(PropertiesConstants.ERROR,
                        ExceptionConstants.ERROR_PAGE_NUMBER);
            } else {
                request.setAttribute(PropertiesConstants.PAGE, page);
            }
        } else {
            request.setAttribute(PropertiesConstants.PAGE, 1);
        }
    }

}
