package io.github.syakuis.authorizationserver.core.web.handler;

import io.github.syakuis.authorizationserver.core.web.ErrorResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-08-31
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
@ControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e, WebRequest request) {
        log.error(e.getMessage(), e);

        ErrorResponseBody body = new ErrorResponseBody(e.getMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(body.getHttpStatus()).body(body.wrapper());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseEntity<Object> responseEntity = super.handleExceptionInternal(ex, body, headers, status, request);
        return new ResponseEntity<>(new ErrorResponseBody(
            ex.getMessage(),
            responseEntity.getStatusCode()).wrapper(), responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
}
