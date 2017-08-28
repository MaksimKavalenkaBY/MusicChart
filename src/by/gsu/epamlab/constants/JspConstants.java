package by.gsu.epamlab.constants;

public class JspConstants {

    private static final String PATH = "WEB-INF/jsp/";
    private static final String PAGE = "page/";
    private static final String FORM = "form/";
    private static final String EXT  = ".jsp";

    public static class Pages {

        private static final String FULL_PATH      = PATH + PAGE;

        public static final String  TRACK_PAGE_JSP = FULL_PATH + "trackPage" + EXT;
        public static final String  ACTOR_PAGE_JSP = FULL_PATH + "actorPage" + EXT;
        public static final String  TAG_PAGE_JSP   = FULL_PATH + "tagPage" + EXT;

    }

    public static class Forms {

        private static final String FULL_PATH                   = PATH + FORM;

        public static final String  LOGIN_FORM_JSP              = FULL_PATH + "loginForm" + EXT;
        public static final String  REGISTRATION_FORM_JSP       = FULL_PATH + "registrationForm"
                + EXT;
        public static final String  ADD_TRACK_FORM_JSP          = FULL_PATH + "addTrackForm" + EXT;
        public static final String  ADD_ACTOR_FORM_JSP          = FULL_PATH + "addActorForm" + EXT;
        public static final String  ADD_TAG_FORM_JSP            = FULL_PATH + "addTagForm" + EXT;
        public static final String  ADD_TRACK_ACTOR_FORM_JSP    = FULL_PATH + "addTrackActorForm"
                + EXT;
        public static final String  ADD_TRACK_TAG_FORM_JSP      = FULL_PATH + "addTrackTagForm"
                + EXT;
        public static final String  ADD_ACTOR_TAG_FORM_JSP      = FULL_PATH + "addActorTagForm"
                + EXT;
        public static final String  DELETE_TRACK_FORM_JSP       = FULL_PATH + "deleteTrackForm"
                + EXT;
        public static final String  DELETE_ACTOR_FORM_JSP       = FULL_PATH + "deleteActorForm"
                + EXT;
        public static final String  DELETE_TAG_FORM_JSP         = FULL_PATH + "deleteTagForm" + EXT;
        public static final String  DELETE_TRACK_ACTOR_FORM_JSP = FULL_PATH + "deleteTrackActorForm"
                + EXT;
        public static final String  DELETE_TRACK_TAG_FORM_JSP   = FULL_PATH + "deleteTrackTagForm"
                + EXT;
        public static final String  DELETE_ACTOR_TAG_FORM_JSP   = FULL_PATH + "deleteActorTagForm"
                + EXT;

    }

}
