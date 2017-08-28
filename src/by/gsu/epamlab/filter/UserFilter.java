package by.gsu.epamlab.filter;

import static by.gsu.epamlab.constants.ActionConstants.USER;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.constants.ActionConstants;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.PropertiesConstants;

public class UserFilter implements Filter {

    @Override
    public void init(final FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
        if (checkUser(request, response)) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean checkUser(final ServletRequest request, final ServletResponse response)
            throws ServletException, IOException {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertiesConstants.ACTION);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        if ((session.getAttribute(PropertiesConstants.USER) == null) && (action != USER)) {
            request.setAttribute(PropertiesConstants.ERROR, ExceptionConstants.ERROR_USER_LOGIN);
            request.getRequestDispatcher(JspConstants.Forms.LOGIN_FORM_JSP).forward(request,
                    response);
            return false;
        }
        return true;
    }

}
