package io.github.syakuis.authorizationserver.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.UUID;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-09-30
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
            .username("syaku")
            .password("1234")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        return new InMemoryRegisteredClientRepository(RegisteredClient.withId(UUID.randomUUID().toString())
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .clientId("IkskO25LLi46NjJDV2Jr")
            .clientSecret("0f16a684fa168e42d46e46c379ae707926bd31bcdc6bb000dc7564d5cec0185ddd5ec9bcf8e6e9e6")
            .clientName("test app")
            .authorizationGrantTypes(authorizationGrantTypes -> {
                authorizationGrantTypes.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                authorizationGrantTypes.add(AuthorizationGrantType.PASSWORD);
                authorizationGrantTypes.add(AuthorizationGrantType.CLIENT_CREDENTIALS);
                authorizationGrantTypes.add(AuthorizationGrantType.REFRESH_TOKEN);
            })
            .redirectUri("http://localhost")
            .build()
        );
    }
}
