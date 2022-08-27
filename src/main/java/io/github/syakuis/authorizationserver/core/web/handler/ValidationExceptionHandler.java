package io.github.syakuis.authorizationserver.core.web.handler;

import io.github.syakuis.authorizationserver.core.web.ErrorResponseBody;
import io.github.syakuis.authorizationserver.core.web.ValidationErrorResponseBody;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

/**
 * @author Seok Kyun. Choi.
 * @since 2022-07-13
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE + 1)
@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
        ValidationErrorResponseBody body = new ValidationErrorResponseBody(
                e.getLocalizedMessage(),
            HttpStatus.BAD_REQUEST,
            e.getConstraintViolations().stream().map(it -> new ValidationErrorResponseBody.Details(
                it.getPropertyPath().toString(),
                it.getMessage(),
                it.getMessageTemplate()
            )).toList()
        );

        return ResponseEntity.status(body.getHttpStatus()).body(body.wrapper());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        BindingResult bindingResult = e.getBindingResult();

        ValidationErrorResponseBody body = new ValidationErrorResponseBody(
                e.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST,
            bindingResult.getFieldErrors().stream().map(it -> new ValidationErrorResponseBody.Details(
                it.getField(),
                it.getDefaultMessage(),
                it.getCode()
            )).toList()
        );

        return ResponseEntity.badRequest().body(body.wrapper());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException e, WebRequest request) {
        ErrorResponseBody body = new ErrorResponseBody(
            e.getMessage(),
            HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(body.wrapper());
    }
}
