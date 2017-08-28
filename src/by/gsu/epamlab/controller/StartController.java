package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.constants.ActionConstants;
import by.gsu.epamlab.constants.ControllerConstants;
import by.gsu.epamlab.constants.DeveloperConstants;
import by.gsu.epamlab.constants.PropertiesConstants;

public class StartController extends Controller {

    private static final long serialVersionUID = -6775072306202144714L;

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        initContext(context);
        initRequest(request);
        jump(ControllerConstants.UPDATE_CONTROLLER, request, response);
    }

    private void initContext(final ServletContext context) {
        context.setAttribute(PropertiesConstants.NAME, DeveloperConstants.NAME);
        context.setAttribute(PropertiesConstants.EMAIL, DeveloperConstants.EMAIL);
    }

    private void initRequest(final HttpServletRequest request) {
        request.setAttribute(PropertiesConstants.ACTION, ActionConstants.TRACK);
        request.setAttribute(PropertiesConstants.PAGE, 1);
    }

}
