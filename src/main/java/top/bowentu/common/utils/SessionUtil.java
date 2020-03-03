package top.bowentu.common.utils;

import top.bowentu.pojo.User;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    private static final String USER_SESSION="user_data";
    public static User getUserSession(HttpServletRequest request){
        return (User)request.getSession().getAttribute(USER_SESSION);
    }

    public static void setUserSession(HttpServletRequest request, User user){
        request.getSession().setAttribute(USER_SESSION, user);
    }

    public static void removeUserSession(HttpServletRequest request){
        request.getSession().removeAttribute(USER_SESSION);
    }
}
