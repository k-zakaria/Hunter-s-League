package org.youcode.maska_hunters_league.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.youcode.maska_hunters_league.domain.enums.Permission.CAN_PARTICIPATE;
import static org.youcode.maska_hunters_league.domain.enums.Permission.CAN_VIEW_RANKINGS;
import static org.youcode.maska_hunters_league.domain.enums.Role.ADMIN;
import static org.youcode.maska_hunters_league.domain.enums.Role.JURY;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityOAuth2Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/v1/hunt").hasAnyRole(ADMIN.name(), JURY.name())
                        .requestMatchers(POST, "/api/v1/participation/**").hasAuthority(CAN_PARTICIPATE.name())
                        .requestMatchers(GET, "/api/v1/participation/**").hasAuthority(CAN_VIEW_RANKINGS.name())
                        .requestMatchers("/api/v1/users/**").hasRole(ADMIN.name())
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix(""); // Remove default "SCOPE_" prefix if not needed
        authoritiesConverter.setAuthoritiesClaimName("permissions"); // Customize according to your JWT structure

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }
}
