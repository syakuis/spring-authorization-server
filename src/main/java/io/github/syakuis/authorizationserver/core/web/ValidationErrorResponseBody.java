package io.github.syakuis.authorizationserver.core.web;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-05-21
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class ValidationErrorResponseBody extends ErrorResponseBody {
    private final List<Details> details;

    public ValidationErrorResponseBody(ResultStatus resultStatus, List<Details> details) {
        super(resultStatus);
        this.details = details;
    }

    public ValidationErrorResponseBody(String message, HttpStatus httpStatus, List<Details> details) {
        super(message, httpStatus);
        this.details = details;
    }

    public ValidationErrorResponseBody(String message, String status, int code, List<Details> details) {
        super(message, status, code);
        this.details = details;
    }

    @Getter
    @EqualsAndHashCode
    @ToString
    public static class Details {
        private final String target;
        private final String message;
        private final String code;

        public Details(String target, String message, String code) {
            this.target = target;
            this.message = message;
            this.code = code;
        }
    }
}
