package io.github.syakuis.authorizationserver.core.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-05-21
 */
@Getter
@EqualsAndHashCode
@ToString
public class ErrorResponseBody implements ResultResponseBody {
    private final String message;
    private final String status;
    private final int code;

    @JsonIgnore
    private ResultStatus resultStatus;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ErrorResponseBody(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
        this.httpStatus = resultStatus.httpStatus();
        this.message = resultStatus.message();
        this.status = resultStatus.name();
        this.code = resultStatus.code();
    }

    public ErrorResponseBody(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = httpStatus.getReasonPhrase();
        this.status = httpStatus.name();
        this.code = httpStatus.value();
    }

    public ErrorResponseBody(String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.status = httpStatus.name();
        this.code = httpStatus.value();
    }

    public ErrorResponseBody(String message, String status, int code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }
}
