package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.action.UpdateJspAction;
import by.gsu.epamlab.constants.PropertiesConstants;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.exception.IllegalDataException;

public class UpdateController extends Controller {

    private static final long serialVersionUID = 2628899248688017032L;

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UpdateJspAction.update(request);
        } catch (DatabaseException | IllegalDataException e) {
            request.setAttribute(PropertiesConstants.ERROR, e.getMessage());
        }

        jump(UpdateJspAction.getJsp(request), request, response);
    }

}
