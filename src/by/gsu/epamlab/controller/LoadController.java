package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.action.LoadDataAction;
import by.gsu.epamlab.constants.PropertiesConstants;
import by.gsu.epamlab.exception.DatabaseException;

public class LoadController extends Controller {

    private static final long serialVersionUID = 780268871785212152L;

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LoadDataAction.load(request);
        } catch (DatabaseException e) {
            request.setAttribute(PropertiesConstants.ERROR, e.getMessage());
        }

        jump(LoadDataAction.getJsp(request), request, response);
    }

}
