package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller extends HttpServlet {

    private static final long serialVersionUID = -7100483626992866218L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        performTask(request, response);
    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        performTask(request, response);
    }

    public void jump(final String page, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected abstract void performTask(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException;

}
