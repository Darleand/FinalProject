package by.webproject.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AuthenticationException.class, SessionAuthenticationException.class})
    public ResponseEntity<ErrorInfo> handleAuthenticationException(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex){
        ErrorInfo errorInfo = new ErrorInfo(UrlUtils.buildFullRequestUrl(req), "error.authorization");
        return new ResponseEntity<>(errorInfo, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NameOfUserEngagedException.class)
    public ResponseEntity<ErrorInfo> handleNameEngagedException(HttpServletRequest req, HttpServletResponse res, NameOfUserEngagedException ex){
        ErrorInfo errorInfo = new ErrorInfo(UrlUtils.buildFullRequestUrl(req), "username is already in use!");
        return new ResponseEntity<>(errorInfo,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CredentialsException.class)
    public ResponseEntity<ErrorInfo> handleCredentialsException(HttpServletRequest req){
        ErrorInfo errorInfo = new ErrorInfo(UrlUtils.buildFullRequestUrl(req), "password does not match with passwordconfirm!");
        return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NewsAlreadyExists.class)
    public ResponseEntity<ErrorInfo> handleNewsAlreadyExistsException(HttpServletRequest req, HttpServletResponse res, NewsAlreadyExists ex){
        ErrorInfo errorInfo = new ErrorInfo(UrlUtils.buildFullRequestUrl(req), "The news is already exists!");
        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }


}
