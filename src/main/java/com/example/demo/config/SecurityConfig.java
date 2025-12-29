// // // package com.example.demo.config;
// // // import com.example.demo.security.JwtAuthenticationFilter;
// // // import org.springframework.beans.factory.annotation.Autowired;
// // // import org.springframework.context.annotation.Bean;
// // // import org.springframework.context.annotation.Configuration;
// // // import org.springframework.security.authentication.AuthenticationManager;
// // // import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// // // import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // import org.springframework.security.web.SecurityFilterChain;
// // // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// // // @Configuration
// // // @EnableMethodSecurity(prePostEnabled = true)
// // // public class SecurityConfig {

// // //     @Autowired
// // //     private JwtAuthenticationFilter jwtFilter;

// // //     @Bean
// // //     public PasswordEncoder passwordEncoder() {
// // //         return new BCryptPasswordEncoder();
// // //     }

// // //     @Bean
// // //     public AuthenticationManager authenticationManager(
// // //             AuthenticationConfiguration authenticationConfiguration) throws Exception {
// // //         return authenticationConfiguration.getAuthenticationManager();
// // //     }

// // //     @Bean
// // //     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

// // //         http
// // //             .csrf(csrf -> csrf.disable())
// // //             .authorizeHttpRequests(auth -> auth
// // //                 .requestMatchers("/auth/**").permitAll()
// // //                 .requestMatchers(
// // //                     "/swagger-ui/**",
// // //                     "/v3/api-docs/**"
// // //                 ).permitAll()
// // //                 .anyRequest().authenticated()
// // //             )
// // //             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

// // //         return http.build();
// // //     }
// // // }

// // package com.example.demo.config;

// // import com.example.demo.repository.AppUserRepository;
// // import com.example.demo.security.JwtAuthenticationFilter;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.context.annotation.Lazy;
// // import org.springframework.security.authentication.AuthenticationManager;
// // import org.springframework.security.authentication.AuthenticationProvider;
// // import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// // import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// // import org.springframework.security.config.http.SessionCreationPolicy;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.security.web.SecurityFilterChain;
// // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// // import org.springframework.web.cors.CorsConfiguration;
// // import org.springframework.web.cors.CorsConfigurationSource;
// // import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// // import java.util.List;

// // @Configuration
// // @EnableWebSecurity
// // public class SecurityConfig {

// //     private final JwtAuthenticationFilter jwtAuthFilter;
// //     private final AppUserRepository userRepository;

// //     public SecurityConfig(@Lazy JwtAuthenticationFilter jwtAuthFilter, AppUserRepository userRepository) {
// //         this.jwtAuthFilter = jwtAuthFilter;
// //         this.userRepository = userRepository;
// //     }

// //     @Bean
// //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// //         http
// //             .csrf(AbstractHttpConfigurer::disable)
// //             .cors(cors -> cors.configurationSource(corsConfigurationSource()))
// //             .authorizeHttpRequests(auth -> auth
// //                 // ✅ 1. Allow Public Auth Endpoints
// //                 .requestMatchers("/auth/**").permitAll()
                
// //                 // ✅ 2. Allow ALL Swagger UI and OpenAPI components
// //                 // Missing any of these usually causes the 403 error
// //                 .requestMatchers(
// //                     "/v3/api-docs/**",
// //                     "/v3/api-docs.yaml",
// //                     "/swagger-ui/**",
// //                     "/swagger-ui.html",
// //                     "/webjars/**",
// //                     "/swagger-resources/**",
// //                     "/configuration/ui",
// //                     "/configuration/security"
// //                 ).permitAll()
                
// //                 // ✅ 3. Secure everything else
// //                 .anyRequest().authenticated()
// //             )
// //             .sessionManagement(session -> session
// //                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
// //             )
// //             .authenticationProvider(authenticationProvider())
// //             .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

// //         return http.build();
// //     }

// //     @Bean
// //     public AuthenticationProvider authenticationProvider() {
// //         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
// //         authProvider.setUserDetailsService(userDetailsService());
// //         authProvider.setPasswordEncoder(passwordEncoder());
// //         return authProvider;
// //     }

// //     @Bean
// //     public UserDetailsService userDetailsService() {
// //         return username -> userRepository.findByUsername(username)
// //                 .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
// //     }

// //     @Bean
// //     public PasswordEncoder passwordEncoder() {
// //         return new BCryptPasswordEncoder();
// //     }

// //     @Bean
// //     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
// //         return config.getAuthenticationManager();
// //     }

// //     @Bean
// //     public CorsConfigurationSource corsConfigurationSource() {
// //         CorsConfiguration configuration = new CorsConfiguration();
// //         configuration.setAllowedOrigins(List.of("*"));
// //         configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
// //         configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
// //         configuration.setExposedHeaders(List.of("Authorization"));
// //         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
// //         source.registerCorsConfiguration("/**", configuration);
// //         return source;
// //     }
// // }
// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final JwtAuthenticationFilter jwtAuthFilter;

//     public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
//         this.jwtAuthFilter = jwtAuthFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 // ✅ PUBLIC PATHS
//                 .requestMatchers("/api/auth/**", "/auth/**").permitAll()
//                 .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**").permitAll()
//                 // ✅ SECURE PATHS
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }
package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/auth/**").permitAll()
                .requestMatchers("/v3/api-docs/**", "/v3/api-docs.yaml", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}