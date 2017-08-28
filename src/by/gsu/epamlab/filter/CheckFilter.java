package by.gsu.epamlab.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import by.gsu.epamlab.constants.ActionConstants;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.PropertiesConstants;
import by.gsu.epamlab.database.structure.columns.info.ActorColumns;
import by.gsu.epamlab.database.structure.columns.info.TagColumns;
import by.gsu.epamlab.database.structure.columns.info.TrackColumns;
import by.gsu.epamlab.database.structure.columns.user.UserColumns;

public class CheckFilter implements Filter {

    private static final String METHOD = "POST";

    @Override
    public void init(final FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (httpServletRequest.getMethod() == METHOD) {
            checkData(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private void checkData(final ServletRequest request, final ServletResponse response) {
        ActionConstants action = ActionConstants
                .valueOf(request.getParameter(PropertiesConstants.ACTION));

        switch (action) {
            case LOGIN:
                checkValues(request, UserColumns.LOGIN.toString(), UserColumns.PASSWORD.toString());
                break;
            case USER:
                checkValues(request, UserColumns.LOGIN.toString(), UserColumns.PASSWORD.toString(),
                        PropertiesConstants.CHECK_PASSWORD);
                checkPassword(request);
                break;
            case TRACK:
                checkValues(request, TrackColumns.NAME.toString(), TrackColumns.URL.toString());
                break;
            case ACTOR:
                checkValues(request, ActorColumns.NAME.toString());
                break;
            case TAG:
                checkValues(request, TagColumns.NAME.toString());
                break;
            case TRACK_ACTOR:
                checkBox(request);
                break;
            case TRACK_TAG:
                checkBox(request);
                break;
            case ACTOR_TAG:
                checkBox(request);
                break;
            case DELETE_TRACK_ACTOR:
                checkBox(request);
                break;
            case DELETE_TRACK_TAG:
                checkBox(request);
                break;
            case DELETE_ACTOR_TAG:
                checkBox(request);
                break;
            default:
                break;
        }
    }

    private void checkValues(final ServletRequest request, final String... keys) {
        for (final String key : keys) {
            String value = request.getParameter(key);
            if (value == null || value.trim().isEmpty()) {
                request.setAttribute(PropertiesConstants.ERROR, ExceptionConstants.ERROR_CORRECT);
            }
        }
    }

    private void checkPassword(final ServletRequest request) {
        final String password = request.getParameter(UserColumns.PASSWORD.toString());
        final String checkPassword = request.getParameter(PropertiesConstants.CHECK_PASSWORD);

        if (!password.equals(checkPassword)) {
            request.setAttribute(PropertiesConstants.ERROR,
                    ExceptionConstants.ERROR_CHECK_PASSWORD);
        }
    }

    private void checkBox(final ServletRequest request) {
        @SuppressWarnings("rawtypes")
        final Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            final String name = (String) paramNames.nextElement();
            if (request.getParameter(name).equals(PropertiesConstants.ON)) {
                return;
            }
        }
        request.setAttribute(PropertiesConstants.ERROR, ExceptionConstants.ERROR_CORRECT);
    }

}
