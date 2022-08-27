package io.github.syakuis.authorizationserver.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.syakuis.authorizationserver.configuration.support.SimpleObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfiguration {
    @ConditionalOnMissingBean
    @Bean
    public ObjectMapper objectMapper() {
        return SimpleObjectMapper.of(new ObjectMapper());
    }
}

