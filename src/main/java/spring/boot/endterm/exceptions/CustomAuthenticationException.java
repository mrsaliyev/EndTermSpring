package spring.boot.endterm.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Slf4j
public class CustomAuthenticationException extends AuthenticationServiceException {

    public CustomAuthenticationException(String msg) {
        super(msg);
        fillInStackTrace();
    }

    public CustomAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
