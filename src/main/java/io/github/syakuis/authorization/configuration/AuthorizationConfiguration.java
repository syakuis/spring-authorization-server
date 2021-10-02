package io.github.syakuis.authorization.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.UUID;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-09-30
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationConfiguration {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        /*RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("")
            .clientSecret()
            .clientName()
            .authorizationGrantType()
            .authorizationGrantType()*/
        return new InMemoryRegisteredClientRepository();
    }
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
            new OAuth2AuthorizationServerConfigurer<>();
        RequestMatcher endpointsMatcher = authorizationServerConfigurer
            .getEndpointsMatcher();

        http
            .requestMatcher(endpointsMatcher)
            .authorizeRequests(authorizeRequests ->
                authorizeRequests.anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
            .apply(authorizationServerConfigurer);

        return http.formLogin(Customizer.withDefaults()).build();
    }
}
