package com.jedosa.junglim.infrastructure;

import com.jedosa.junglim.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionController {

    private static final Logger log =  LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";
    }

    @ExceptionHandler(NoLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String noLoginException(final Throwable throwable, final Model model) {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";
    }

    @ExceptionHandler(NotAdminException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String notAdminException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";
    }

    @ExceptionHandler(NoOnePageMenuException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String noOnePageMenuException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/5xx";
    }

    @ExceptionHandler(NoArticleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String noArticleException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";
    }

    @ExceptionHandler(NoAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String noAccountException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";
    }

    @ExceptionHandler(NotOwnException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notOwnException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";
    }

    @ExceptionHandler(NoCommentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String noCommentException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";
    }

    @ExceptionHandler(AlreadyOrderedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String alreayOrderedException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";    }

    @ExceptionHandler(NoFlyleafContentPriceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String noFlyleafContentPriceException(final Throwable throwable, final Model model) throws IOException {
        log.error("예외발생", throwable);
        model.addAttribute("exception", throwable.getClass());
        model.addAttribute("message", throwable.getMessage());
        return "error/4xx";
    }
}
