package mission.firstmission.manager;

public class SessionManager {

    private static SessionManager sessionManager;

    private SessionManager() {}


    public static SessionManager getInstance() {
        if(sessionManager == null) {
            return new SessionManager();
        }
        return sessionManager;
    }
}
