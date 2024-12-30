package aston.intensive.authentication_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
//        AuthenticationWebFilterConfigurer.apply(http);
        http.authorizeHttpRequests(auth->auth.anyRequest().permitAll());
//        http.authorizeExchange(auth -> auth
//                .pathMatchers("/api/**").permitAll()
//                .pathMatchers("/swagger-ui/**", "/swagger-ui.html",
//                        "/api-docs/**").permitAll()
//                .anyExchange().authenticated());

        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder(25);
    }
}
