package by.gsu.epamlab.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.ActionConstants;
import by.gsu.epamlab.constants.PropertiesConstants;

public class ActionFilter implements Filter {

    @Override
    public void init(final FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
        if (checkAction(request, response)) {
            chain.doFilter(request, response);
        } else {
            new UserFilter().doFilter(request, response, chain);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean checkAction(final ServletRequest request, final ServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter(PropertiesConstants.ACTION) != null) {
            try {
                ActionConstants action = ActionConstants
                        .valueOf(request.getParameter(PropertiesConstants.ACTION));
                request.setAttribute(PropertiesConstants.ACTION, action);
                if (action == ActionConstants.USER_TRACK || action == ActionConstants.USER_ACTOR
                        || action == ActionConstants.USER_TAG) {
                    return false;
                }
            } catch (final IllegalArgumentException e) {
                request.setAttribute(PropertiesConstants.ACTION, ActionConstants.TRACK);
                request.setAttribute(PropertiesConstants.ERROR,
                        ExceptionConstants.ERROR_PAGE_NOT_FOUND);
            }
        } else {
            request.setAttribute(PropertiesConstants.ACTION, ActionConstants.TRACK);
        }
        return true;
    }

}
