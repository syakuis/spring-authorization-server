package io.github.syakuis.authorizationserver.core.web;

import java.util.Map;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-05-21
 */
public interface ResultResponseBody {
    String getMessage();
    String getStatus();
    int getCode();

    default Map<String, ResultResponseBody> wrapper() {
        return JsonRootName.of("error", this);
    }
}
