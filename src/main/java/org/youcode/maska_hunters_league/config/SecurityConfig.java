package org.youcode.maska_hunters_league.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.web.exception.user.EmailNotFoundException;

import static org.springframework.http.HttpMethod.*;
import static org.youcode.maska_hunters_league.domain.enums.Permission.*;
import static org.youcode.maska_hunters_league.domain.enums.Role.*;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final  UserRepository userRepository;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserRepository userRepository) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new EmailNotFoundException("user not found"));
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = """
        ROLE_ADMIN > ROLE_JURY
        ROLE_JURY > ROLE_MEMBER
        """;

        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/v1/hunt").hasAnyRole(ADMIN.name(), JURY.name())

                .requestMatchers(POST,"/api/v1/participation/**").hasAuthority(CAN_PARTICIPATE.name())
                .requestMatchers(GET,"/api/v1/participation/**").hasAuthority(CAN_VIEW_RANKINGS.name())

                .requestMatchers("/api/v1/users/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
