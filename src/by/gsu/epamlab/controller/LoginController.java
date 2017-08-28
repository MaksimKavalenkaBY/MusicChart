package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.action.CheckData;
import by.gsu.epamlab.constants.ControllerConstants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.PropertiesConstants;
import by.gsu.epamlab.database.dao.user.IUserDAO;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.exception.IllegalDataException;
import by.gsu.epamlab.factory.user.UserFactory;

public class LoginController extends Controller {

    private static final long serialVersionUID = -4850029295195107756L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        jump(JspConstants.Forms.LOGIN_FORM_JSP, request, response);
    }

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        try (IUserDAO userDAO = UserFactory.getEditor()) {
            CheckData.checkFilterError(request);
            CheckData.setUser(request, userDAO);
            response.sendRedirect(ControllerConstants.UPDATE_CONTROLLER);
        } catch (DatabaseException | IllegalDataException e) {
            request.setAttribute(PropertiesConstants.ERROR, e.getMessage());
            jump(JspConstants.Forms.LOGIN_FORM_JSP, request, response);
        }
    }

}
