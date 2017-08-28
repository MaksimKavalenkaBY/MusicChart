package by.gsu.epamlab.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.PropertiesConstants;
import by.gsu.epamlab.database.dao.user.IUserDAO;
import by.gsu.epamlab.database.structure.columns.user.UserColumns;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.exception.IllegalDataException;

public class CheckData {

    public static boolean checkValue(final String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public static void hasNext(final ResultSet rs) throws SQLException, DatabaseException {
        if (!rs.next()) {
            throw new DatabaseException(ExceptionConstants.ERROR_DATA);
        }
    }

    public static void checkFilterError(final HttpServletRequest request)
            throws IllegalDataException {
        if (request.getAttribute(PropertiesConstants.ERROR) != null) {
            String error = (String) request.getAttribute(PropertiesConstants.ERROR);
            throw new IllegalDataException(error);
        }
    }

    public static void setUser(final HttpServletRequest request, final IUserDAO userDAO)
            throws DatabaseException {
        final String login = request.getParameter(UserColumns.LOGIN.toString());
        final String password = request.getParameter(UserColumns.PASSWORD.toString());
        final User user = userDAO.getUser(login, password);
        final HttpSession session = request.getSession();
        session.setAttribute(PropertiesConstants.USER, user);
    }

}
