package by.gsu.epamlab.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.ActionConstants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.PropertiesConstants;
import by.gsu.epamlab.database.dao.data.IActorTagDAO;
import by.gsu.epamlab.database.dao.data.ITrackActorDAO;
import by.gsu.epamlab.database.dao.data.ITrackTagDAO;
import by.gsu.epamlab.database.dao.info.IActorDAO;
import by.gsu.epamlab.database.dao.info.ITagDAO;
import by.gsu.epamlab.database.dao.info.ITrackDAO;
import by.gsu.epamlab.database.dao.user.IUserActorDAO;
import by.gsu.epamlab.database.dao.user.IUserDAO;
import by.gsu.epamlab.database.dao.user.IUserTagDAO;
import by.gsu.epamlab.database.dao.user.IUserTrackDAO;
import by.gsu.epamlab.database.editor.user.UserDatabaseEditor;
import by.gsu.epamlab.database.structure.columns.info.ActorColumns;
import by.gsu.epamlab.database.structure.columns.info.TagColumns;
import by.gsu.epamlab.database.structure.columns.info.TrackColumns;
import by.gsu.epamlab.database.structure.columns.user.UserColumns;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.exception.IllegalDataException;
import by.gsu.epamlab.factory.data.ActorTagFactory;
import by.gsu.epamlab.factory.data.TrackActorFactory;
import by.gsu.epamlab.factory.data.TrackTagFactory;
import by.gsu.epamlab.factory.info.ActorFactory;
import by.gsu.epamlab.factory.info.TagFactory;
import by.gsu.epamlab.factory.info.TrackFactory;
import by.gsu.epamlab.factory.user.UserActorFactory;
import by.gsu.epamlab.factory.user.UserTagFactory;
import by.gsu.epamlab.factory.user.UserTrackFactory;

public class EditDatabaseAction {

    public static String getJsp(final HttpServletRequest request) {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertiesConstants.ACTION);

        switch (action) {
            case USER:
                return JspConstants.Forms.REGISTRATION_FORM_JSP;
            case TRACK:
                return JspConstants.Forms.ADD_TRACK_FORM_JSP;
            case ACTOR:
                return JspConstants.Forms.ADD_ACTOR_FORM_JSP;
            case TAG:
                return JspConstants.Forms.ADD_TAG_FORM_JSP;
            case TRACK_ACTOR:
                return JspConstants.Forms.ADD_TRACK_ACTOR_FORM_JSP;
            case TRACK_TAG:
                return JspConstants.Forms.ADD_TRACK_TAG_FORM_JSP;
            case ACTOR_TAG:
                return JspConstants.Forms.ADD_ACTOR_TAG_FORM_JSP;
            case DELETE_TRACK:
                return JspConstants.Forms.DELETE_TRACK_FORM_JSP;
            case DELETE_ACTOR:
                return JspConstants.Forms.DELETE_ACTOR_FORM_JSP;
            case DELETE_TAG:
                return JspConstants.Forms.DELETE_TAG_FORM_JSP;
            case DELETE_TRACK_ACTOR:
                return JspConstants.Forms.DELETE_TRACK_ACTOR_FORM_JSP;
            case DELETE_TRACK_TAG:
                return JspConstants.Forms.DELETE_TRACK_TAG_FORM_JSP;
            case DELETE_ACTOR_TAG:
                return JspConstants.Forms.DELETE_ACTOR_TAG_FORM_JSP;
            default:
                return JspConstants.Pages.TRACK_PAGE_JSP;
        }
    }

    public static void edit(final HttpServletRequest request, final HttpServletResponse response)
            throws IllegalDataException, DatabaseException, IOException {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertiesConstants.ACTION);

        switch (action) {
            case USER:
                addUser(request);
                break;
            case TRACK:
                addTrack(request);
                break;
            case ACTOR:
                addActor(request);
                break;
            case TAG:
                addTag(request);
                break;
            case TRACK_ACTOR:
                addTrackActor(request);
                break;
            case TRACK_TAG:
                addTrackTag(request);
                break;
            case ACTOR_TAG:
                addActorTag(request);
                break;
            case DELETE_TRACK:
                deleteTrack(request);
                break;
            case DELETE_ACTOR:
                deleteActor(request);
                break;
            case DELETE_TAG:
                deleteTag(request);
                break;
            case DELETE_TRACK_ACTOR:
                deleteTrackActor(request);
                break;
            case DELETE_TRACK_TAG:
                deleteTrackTag(request);
                break;
            case DELETE_ACTOR_TAG:
                deleteActorTag(request);
                break;
            case USER_TRACK:
                likeUserTrack(request, response);
                break;
            case USER_ACTOR:
                likeUserActor(request, response);
                break;
            case USER_TAG:
                likeUserTag(request, response);
                break;
            default:
                break;
        }
    }

    private static void setResponse(final HttpServletResponse response, final int rating)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.print(rating);
        out.flush();
        out.close();
    }

    private static void addUser(final HttpServletRequest request) throws DatabaseException {
        try (IUserDAO userDAO = new UserDatabaseEditor()) {
            final String login = request.getParameter(UserColumns.LOGIN.toString());
            final String password = request.getParameter(UserColumns.PASSWORD.toString());
            userDAO.addUser(login, password);
            CheckData.setUser(request, userDAO);
        }
    }

    private static void addTrack(final HttpServletRequest request) throws DatabaseException {
        try (ITrackDAO trackDAO = TrackFactory.getEditor()) {
            final String name = request.getParameter(TrackColumns.NAME.toString());
            final String image = request.getParameter(TrackColumns.IMAGE.toString());
            final String url = request.getParameter(TrackColumns.URL.toString());

            if (CheckData.checkValue(image)) {
                trackDAO.addTrack(name, image, url);
            } else {
                trackDAO.addTrack(name, url);
            }
        }
    }

    private static void addActor(final HttpServletRequest request) throws DatabaseException {
        try (IActorDAO actorDAO = ActorFactory.getEditor()) {
            final String name = request.getParameter(ActorColumns.NAME.toString());
            final String image = request.getParameter(ActorColumns.IMAGE.toString());

            if (CheckData.checkValue(image)) {
                actorDAO.addActor(name, image);
            } else {
                actorDAO.addActor(name);
            }
        }
    }

    private static void addTag(final HttpServletRequest request) throws DatabaseException {
        try (ITagDAO tagDAO = TagFactory.getEditor()) {
            final String name = request.getParameter(TagColumns.NAME.toString());
            tagDAO.addTag(name);
        }
    }

    private static void addTrackActor(final HttpServletRequest request) throws DatabaseException {
        try (ITrackActorDAO trackActorDAO = TrackActorFactory.getEditor()) {
            final String trackName = request.getParameter(TrackColumns.NAME.toString());

            @SuppressWarnings("rawtypes")
            final Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                final String actorName = (String) paramNames.nextElement();
                if (request.getParameter(actorName).equals(PropertiesConstants.ON)) {
                    trackActorDAO.addTrackActor(trackName, actorName);
                }
            }
        }
    }

    private static void addTrackTag(final HttpServletRequest request) throws DatabaseException {
        try (ITrackTagDAO trackTagDAO = TrackTagFactory.getEditor()) {
            final String trackName = request.getParameter(TrackColumns.NAME.toString());

            @SuppressWarnings("rawtypes")
            final Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                final String tagName = (String) paramNames.nextElement();
                if (request.getParameter(tagName).equals(PropertiesConstants.ON)) {
                    trackTagDAO.addTrackTag(trackName, tagName);
                }
            }
        }
    }

    private static void addActorTag(final HttpServletRequest request) throws DatabaseException {
        try (IActorTagDAO actorTagDAO = ActorTagFactory.getEditor()) {
            final String actorName = request.getParameter(ActorColumns.NAME.toString());

            @SuppressWarnings("rawtypes")
            final Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                final String tagName = (String) paramNames.nextElement();
                if (request.getParameter(tagName).equals(PropertiesConstants.ON)) {
                    actorTagDAO.addActorTag(actorName, tagName);
                }
            }
        }
    }

    private static void deleteTrack(final HttpServletRequest request) throws DatabaseException {
        try (ITrackDAO trackDAO = TrackFactory.getEditor()) {
            final String name = request.getParameter(TrackColumns.NAME.toString());
            trackDAO.deleteTrack(name);
        }
    }

    private static void deleteActor(final HttpServletRequest request) throws DatabaseException {
        try (IActorDAO actorDAO = ActorFactory.getEditor()) {
            final String name = request.getParameter(ActorColumns.NAME.toString());
            actorDAO.deleteActor(name);
        }
    }

    private static void deleteTag(final HttpServletRequest request) throws DatabaseException {
        try (ITagDAO tagDAO = TagFactory.getEditor()) {
            final String name = request.getParameter(TagColumns.NAME.toString());
            tagDAO.deleteTag(name);
        }
    }

    private static void deleteTrackActor(final HttpServletRequest request)
            throws DatabaseException {
        try (ITrackActorDAO trackActorDAO = TrackActorFactory.getEditor()) {
            final String trackName = request.getParameter(TrackColumns.NAME.toString());

            @SuppressWarnings("unchecked")
            final Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                final String actorName = paramNames.nextElement();
                if (request.getParameter(actorName).equals(PropertiesConstants.ON)) {
                    trackActorDAO.deleteTrackActor(trackName, actorName);
                }
            }
        }
    }

    private static void deleteTrackTag(final HttpServletRequest request)
            throws IllegalDataException, DatabaseException {
        try (ITrackTagDAO trackTagDAO = TrackTagFactory.getEditor()) {
            final String trackName = request.getParameter(TrackColumns.NAME.toString());

            @SuppressWarnings("unchecked")
            final Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                final String tagName = paramNames.nextElement();
                if (request.getParameter(tagName).equals(PropertiesConstants.ON)) {
                    trackTagDAO.deleteTrackTag(trackName, tagName);
                }
            }
        }
    }

    private static void deleteActorTag(final HttpServletRequest request)
            throws IllegalDataException, DatabaseException {
        try (IActorTagDAO actorTagDAO = ActorTagFactory.getEditor()) {
            final String actorName = request.getParameter(ActorColumns.NAME.toString());

            @SuppressWarnings("unchecked")
            final Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                final String tagName = paramNames.nextElement();
                if (request.getParameter(tagName).equals(PropertiesConstants.ON)) {
                    actorTagDAO.deleteActorTag(actorName, tagName);
                }
            }
        }
    }

    private static void likeUserTrack(final HttpServletRequest request,
            final HttpServletResponse response) throws DatabaseException, IOException {
        try (IUserTrackDAO userTrackDAO = UserTrackFactory.getEditor()) {
            final User user = (User) request.getSession().getAttribute(PropertiesConstants.USER);
            final String trackName = request.getParameter(TrackColumns.NAME.toString());

            final int rating = userTrackDAO.likeUserTrack(user.getId(), trackName);
            setResponse(response, rating);
        }
    }

    private static void likeUserActor(final HttpServletRequest request,
            final HttpServletResponse response) throws DatabaseException, IOException {
        try (IUserActorDAO userActorDAO = UserActorFactory.getEditor()) {
            final User user = (User) request.getSession().getAttribute(PropertiesConstants.USER);
            final String actorName = request.getParameter(ActorColumns.NAME.toString());

            final int rating = userActorDAO.likeUserActor(user.getId(), actorName);
            setResponse(response, rating);
        }
    }

    private static void likeUserTag(final HttpServletRequest request,
            final HttpServletResponse response) throws DatabaseException, IOException {
        try (IUserTagDAO userTagDAO = UserTagFactory.getEditor()) {
            final User user = (User) request.getSession().getAttribute(PropertiesConstants.USER);
            final String tagName = request.getParameter(TagColumns.NAME.toString());

            final int rating = userTagDAO.likeUserTag(user.getId(), tagName);
            setResponse(response, rating);
        }
    }

}
