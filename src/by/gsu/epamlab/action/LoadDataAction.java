package by.gsu.epamlab.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.gsu.epamlab.bean.Actor;
import by.gsu.epamlab.bean.Tag;
import by.gsu.epamlab.bean.Track;
import by.gsu.epamlab.constants.ActionConstants;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.PropertiesConstants;
import by.gsu.epamlab.database.dao.info.IActorDAO;
import by.gsu.epamlab.database.dao.info.ITagDAO;
import by.gsu.epamlab.database.dao.info.ITrackDAO;
import by.gsu.epamlab.database.structure.tables.InfoTables;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.factory.info.ActorFactory;
import by.gsu.epamlab.factory.info.TagFactory;
import by.gsu.epamlab.factory.info.TrackFactory;

public class LoadDataAction {

    public static String getJsp(final HttpServletRequest request) {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertiesConstants.ACTION);

        switch (action) {
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
                request.setAttribute(PropertiesConstants.ERROR, ExceptionConstants.SECRET_MESSAGE);
                return JspConstants.Pages.TRACK_PAGE_JSP;
        }
    }

    public static void load(final HttpServletRequest request) throws DatabaseException {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertiesConstants.ACTION);

        switch (action) {
            case TRACK_ACTOR:
                loadTracks(request);
                loadActors(request);
                break;
            case TRACK_TAG:
                loadTracks(request);
                loadTags(request);
                break;
            case ACTOR_TAG:
                loadActors(request);
                loadTags(request);
                break;
            case DELETE_TRACK:
                loadTracks(request);
                break;
            case DELETE_ACTOR:
                loadActors(request);
                break;
            case DELETE_TAG:
                loadTags(request);
                break;
            case DELETE_TRACK_ACTOR:
                loadTracks(request);
                loadActors(request);
                break;
            case DELETE_TRACK_TAG:
                loadTracks(request);
                loadTags(request);
                break;
            case DELETE_ACTOR_TAG:
                loadActors(request);
                loadTags(request);
                break;
            default:
                break;
        }
    }

    private static void loadTracks(final HttpServletRequest request) throws DatabaseException {
        try (ITrackDAO trackDAO = TrackFactory.getEditor()) {
            final List<Track> tracks = trackDAO.getTracks();
            Collections.sort(tracks);
            request.setAttribute(InfoTables.TRACK.toString(), tracks);
        }
    }

    private static void loadActors(final HttpServletRequest request) throws DatabaseException {
        try (IActorDAO actorDAO = ActorFactory.getEditor()) {
            final List<Actor> actors = actorDAO.getActors();
            Collections.sort(actors);
            request.setAttribute(InfoTables.ACTOR.toString(), actors);
        }
    }

    private static void loadTags(final HttpServletRequest request) throws DatabaseException {
        try (ITagDAO tagDAO = TagFactory.getEditor()) {
            final List<Tag> tags = tagDAO.getTags();
            Collections.sort(tags);
            request.setAttribute(InfoTables.TAG.toString(), tags);
        }
    }

}
