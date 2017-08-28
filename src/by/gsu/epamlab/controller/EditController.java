package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.action.CheckData;
import by.gsu.epamlab.action.EditDatabaseAction;
import by.gsu.epamlab.action.LoadDataAction;
import by.gsu.epamlab.constants.ControllerConstants;
import by.gsu.epamlab.constants.PropertiesConstants;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.exception.IllegalDataException;

public class EditController extends Controller {

    private static final long serialVersionUID = 1288525734890178259L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        jump(EditDatabaseAction.getJsp(request), request, response);
    }

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LoadDataAction.load(request);
            CheckData.checkFilterError(request);
            EditDatabaseAction.edit(request, response);
            response.sendRedirect(ControllerConstants.UPDATE_CONTROLLER);
        } catch (DatabaseException | IllegalDataException e) {
            request.setAttribute(PropertiesConstants.ERROR, e.getMessage());
            jump(EditDatabaseAction.getJsp(request), request, response);
        }
    }

}
