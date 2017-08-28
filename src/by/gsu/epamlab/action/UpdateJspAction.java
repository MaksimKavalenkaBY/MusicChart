package by.gsu.epamlab.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.gsu.epamlab.bean.Actor;
import by.gsu.epamlab.bean.Tag;
import by.gsu.epamlab.bean.Track;
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
import by.gsu.epamlab.database.dao.user.IUserTagDAO;
import by.gsu.epamlab.database.dao.user.IUserTrackDAO;
import by.gsu.epamlab.database.structure.tables.InfoTables;
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

public class UpdateJspAction {

    public static String getJsp(final HttpServletRequest request) {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertiesConstants.ACTION);

        switch (action) {
            case TRACK:
                return JspConstants.Pages.TRACK_PAGE_JSP;
            case ACTOR:
                return JspConstants.Pages.ACTOR_PAGE_JSP;
            case TAG:
                return JspConstants.Pages.TAG_PAGE_JSP;
            case TRACK_ACTOR:
                return JspConstants.Pages.TRACK_PAGE_JSP;
            case TRACK_TAG:
                return JspConstants.Pages.TRACK_PAGE_JSP;
            case ACTOR_TAG:
                return JspConstants.Pages.ACTOR_PAGE_JSP;
            case USER_TRACK:
                return JspConstants.Pages.TRACK_PAGE_JSP;
            case USER_ACTOR:
                return JspConstants.Pages.ACTOR_PAGE_JSP;
            case USER_TAG:
                return JspConstants.Pages.TAG_PAGE_JSP;
            default:
                return JspConstants.Pages.TRACK_PAGE_JSP;
        }
    }

    public static void update(final HttpServletRequest request)
            throws DatabaseException, IllegalDataException {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertiesConstants.ACTION);

        switch (action) {
            case TRACK:
                updateTrack(request);
                break;
            case ACTOR:
                updateActor(request);
                break;
            case TAG:
                updateTag(request);
                break;
            case TRACK_ACTOR:
                updateTrackActor(request);
                break;
            case TRACK_TAG:
                updateTrackTag(request);
                break;
            case ACTOR_TAG:
                updateActorTag(request);
                break;
            case USER_TRACK:
                updateUserTrack(request);
                break;
            case USER_ACTOR:
                updateUserActor(request);
                break;
            case USER_TAG:
                updateUserTag(request);
                break;
            default:
                updateTrack(request);
                break;
        }
        request.setAttribute(PropertiesConstants.ACTION, action);
    }

    private static void updateTrack(final HttpServletRequest request) throws DatabaseException {
        try (ITrackDAO trackDAO = TrackFactory.getEditor()) {
            final int page = (int) request.getAttribute(PropertiesConstants.PAGE);
            final List<Track> tracks = trackDAO.getCountTracks(page);
            request.setAttribute(InfoTables.TRACK.toString(), tracks);
        }
    }

    private static void updateActor(final HttpServletRequest request) throws DatabaseException {
        try (IActorDAO actorDAO = ActorFactory.getEditor()) {
            final int page = (int) request.getAttribute(PropertiesConstants.PAGE);
            final List<Actor> actors = actorDAO.getCountActors(page);
            request.setAttribute(InfoTables.ACTOR.toString(), actors);
        }
    }

    private static void updateTag(final HttpServletRequest request) throws DatabaseException {
        try (ITagDAO tagDAO = TagFactory.getEditor()) {
            final List<Tag> tags = tagDAO.getTags();
            Collections.reverse(tags);
            request.setAttribute(InfoTables.TAG.toString(), tags);
        }
    }

    private static void updateTrackActor(final HttpServletRequest request)
            throws DatabaseException {
        final String DATA = "'s tracks";

        try (ITrackActorDAO trackActorDAO = TrackActorFactory.getEditor()) {
            String info = request.getParameter(PropertiesConstants.INFO);

            final int page = (int) request.getAttribute(PropertiesConstants.PAGE);
            final List<Track> tracks = trackActorDAO.getCountTracks(info, page);
            request.setAttribute(InfoTables.TRACK.toString(), tracks);
            request.setAttribute(PropertiesConstants.DATA, info + DATA);
            request.setAttribute(PropertiesConstants.INFO, info);
        }
    }

    private static void updateTrackTag(final HttpServletRequest request) throws DatabaseException {
        final String DATA = " tracks";

        try (ITrackTagDAO trackTagDAO = TrackTagFactory.getEditor()) {
            String info = request.getParameter(PropertiesConstants.INFO);

            final int page = (int) request.getAttribute(PropertiesConstants.PAGE);
            final List<Track> tracks = trackTagDAO.getCountTracks(info, page);
            request.setAttribute(InfoTables.TRACK.toString(), tracks);
            request.setAttribute(PropertiesConstants.DATA, info + DATA);
            request.setAttribute(PropertiesConstants.INFO, info);
        }
    }

    private static void updateActorTag(final HttpServletRequest request) throws DatabaseException {
        final String DATA = " actors";

        try (IActorTagDAO actorTagDAO = ActorTagFactory.getEditor()) {
            String info = request.getParameter(PropertiesConstants.INFO);

            final int page = (int) request.getAttribute(PropertiesConstants.PAGE);
            final List<Actor> actors = actorTagDAO.getCountActors(info, page);
            request.setAttribute(InfoTables.ACTOR.toString(), actors);
            request.setAttribute(PropertiesConstants.DATA, info + DATA);
            request.setAttribute(PropertiesConstants.INFO, info);
        }
    }

    private static void updateUserTrack(final HttpServletRequest request)
            throws DatabaseException, IllegalDataException {
        final String DATA = "My tracks";

        try (IUserTrackDAO userTrackDAO = UserTrackFactory.getEditor()) {
            final User user = (User) request.getSession().getAttribute(PropertiesConstants.USER);
            final int page = (int) request.getAttribute(PropertiesConstants.PAGE);
            final List<Track> tracks = userTrackDAO.getCountTracks(user.getId(), page);
            request.setAttribute(InfoTables.TRACK.toString(), tracks);
            request.setAttribute(PropertiesConstants.DATA, DATA);
        }
    }

    private static void updateUserActor(final HttpServletRequest request)
            throws DatabaseException, IllegalDataException {
        final String DATA = "My actors";

        try (IUserActorDAO userActorDAO = UserActorFactory.getEditor()) {
            final User user = (User) request.getSession().getAttribute(PropertiesConstants.USER);
            final int page = (int) request.getAttribute(PropertiesConstants.PAGE);
            final List<Actor> actors = userActorDAO.getCountActors(user.getId(), page);
            request.setAttribute(InfoTables.ACTOR.toString(), actors);
            request.setAttribute(PropertiesConstants.DATA, DATA);
        }
    }

    private static void updateUserTag(final HttpServletRequest request)
            throws DatabaseException, IllegalDataException {
        final String DATA = "My tags";

        try (IUserTagDAO userTagDAO = UserTagFactory.getEditor()) {
            final User user = (User) request.getSession().getAttribute(PropertiesConstants.USER);
            final List<Tag> tags = userTagDAO.getTags(user.getId());
            Collections.reverse(tags);
            request.setAttribute(InfoTables.TAG.toString(), tags);
            request.setAttribute(PropertiesConstants.DATA, DATA);
        }
    }

}
