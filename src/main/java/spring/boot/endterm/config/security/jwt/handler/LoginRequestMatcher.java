package spring.boot.endterm.config.security.jwt.handler;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class LoginRequestMatcher implements RequestMatcher {
    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        if(httpServletRequest.getRequestURI().contains("/error"))
            return false;
        if(httpServletRequest.getRequestURI().contains("/public"))
            return false;
        if (httpServletRequest.getRequestURI().contains("/book"))
            return false;
        return true;

    }
}
