package io.github.syakuis.authorizationserver.core.web;

import org.springframework.http.HttpStatus;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-05-21
 */
public interface ResultStatus {
    HttpStatus httpStatus();
    String message();
    default String name() {
        return httpStatus().name();
    }

    default int code() {
        return httpStatus().value();
    }
    String toString();
}
