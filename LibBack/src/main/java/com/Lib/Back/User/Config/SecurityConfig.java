package com.Lib.Back.User.Config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
	            .requestMatchers("/api/**").permitAll() // Allow API requests
	            .requestMatchers("/static/**", "/public/**").permitAll() // Allow static resources
	            .anyRequest().authenticated() // Secure other endpoints
	        )
	        .formLogin(formLogin -> formLogin
	            .loginProcessingUrl("/api/users/log-in")
	            .permitAll()
	        )
	        .cors(cors -> cors
	            .configurationSource(corsConfigurationSource()) // Apply CORS configuration directly
	        );

	    return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}




    @Bean
    public PasswordEncoder passwordEncoder() {
        String idForEncode = "argon2";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder(65536, 8, 1, 64, 32));
        encoders.put("argon2", new Argon2PasswordEncoder(16, 32, 1, 65536, 3));
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }
}
