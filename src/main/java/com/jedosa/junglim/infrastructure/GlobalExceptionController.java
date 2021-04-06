package com.jedosa.junglim.infrastructure;

import com.jedosa.junglim.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgumentException(HttpServletResponse response, IllegalArgumentException ex, Model model) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NoLoginException.class)
    public void noLoginException(HttpServletResponse response, NoLoginException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NotAdminException.class)
    public void notAdminException(HttpServletResponse response, NotAdminException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NoOnePageMenuException.class)
    public void noOnePageMenuException(HttpServletResponse response, NoOnePageMenuException ex) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(NoArticleException.class)
    public void noArticleException(HttpServletResponse response, NoArticleException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NoAccountException.class)
    public void noAccountException(HttpServletResponse response, NoAccountException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NotOwnException.class)
    public void notOwnException(HttpServletResponse response, NotOwnException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NoCommentException.class)
    public void noCommentException(HttpServletResponse response, NoCommentException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(AlreadyOrderedException.class)
    public void alreayOrderedException(HttpServletResponse response, AlreadyOrderedException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
