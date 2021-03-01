package sstu.diploma.ivory.exception.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import sstu.diploma.ivory.api.data.ErrorResponseData;
import sstu.diploma.ivory.exception.IvoryAccountException;
import sstu.diploma.ivory.exception.IvorySecurityException;

import java.time.ZonedDateTime;

@Log4j2
@RestControllerAdvice
public class IvoryExceptionHandler {

    @ExceptionHandler(IvorySecurityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseData ivorySecurityException(IvorySecurityException e, WebRequest r) {
        log.warn(String.format("A security exception occurred with message %s and request details %s",
                e.getMessage(), r.getDescription(true)));
        return new ErrorResponseData(ZonedDateTime.now(), e.getMessage(), r.getDescription(false));
    }

    @ExceptionHandler(IvoryAccountException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseData ivoryAccountException(IvoryAccountException e, WebRequest r) {
        return new ErrorResponseData(ZonedDateTime.now(), e.getMessage(), r.getDescription(true));
    }
}
