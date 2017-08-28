package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.constants.ControllerConstants;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.PropertiesConstants;

public class LogoutController extends Controller {

    private static final long serialVersionUID = -8700100975765784712L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(PropertiesConstants.ERROR, ExceptionConstants.ERROR_ACCESS_REQUEST);
        jump(JspConstants.Pages.TRACK_PAGE_JSP, request, response);
    }

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(ControllerConstants.UPDATE_CONTROLLER);
    }

}
