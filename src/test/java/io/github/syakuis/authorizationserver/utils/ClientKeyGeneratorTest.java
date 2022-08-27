package io.github.syakuis.authorizationserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Seok Kyun. Choi.
 * @since 2022-08-28
 */
@Slf4j
@Disabled
class ClientKeyGeneratorTest {
    @Test
    void clientId() {
        log.debug("{}", ClientKeyGenerator.clientId());
    }

    @Test
    void clientSecret() {
        log.debug("{}", ClientKeyGenerator.clientSecret());
    }

}