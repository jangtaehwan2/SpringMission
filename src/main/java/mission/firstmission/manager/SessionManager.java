package mission.firstmission.manager;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Component
public class SessionManager {

    public String setKey(HttpSession session, String key) {
        if(session.getAttribute("Authorization") == null) {
            session.setAttribute("Authorization", key);
            return key;
        } else {
            return null;
        }
    }
    public String getKey(HttpSession session) {
        return session.getAttribute("Authorization").toString();
    }
    // session 정보 날리기
    public boolean deleteKey(HttpSession session) {
        session.invalidate();
        return true;
    }
}
