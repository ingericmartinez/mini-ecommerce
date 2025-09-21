package com.ecommerce.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Delegating encoder with bcrypt as default; supports {id} prefixes
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder()
                .username("user")
                .passwordEncoder(encoder::encode)
                .password("user123")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .passwordEncoder(encoder::encode)
                .password("admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173", "http://127.0.0.1:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Location"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Allow preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Public docs and H2 console
                        .requestMatchers(
                                "/", "/index.html",
                                "/swagger-ui.html", "/swagger-ui/**", "/v1/api-docs/**",
                                "/h2-console/**"
                        ).permitAll()
                        // Public GET on API
                        .requestMatchers(HttpMethod.GET, "/api/v1/ecommerce/**").permitAll()
                        // Require auth for write operations on API
                        .requestMatchers(HttpMethod.POST, "/api/v1/ecommerce/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/ecommerce/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/ecommerce/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/ecommerce/**").authenticated()
                        // Any other request -> authenticated by default
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        // For H2 console frames
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return http.build();
    }
}
