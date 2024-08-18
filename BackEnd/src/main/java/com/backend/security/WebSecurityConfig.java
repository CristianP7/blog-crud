package com.backend.security;


import com.backend.security.jwt.JwtAuthorizationEntryPoint;
import com.backend.security.jwt.JwtRequestFilter;
import com.backend.security.jwt.JwtUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


//@Profile(value = {"dev", "prod"})
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUserDetailService jwtUserDetailService;
    private final JwtAuthorizationEntryPoint jwtAuthorizationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //csrf -> csrf.disable()
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/blog/userRoles/create").permitAll()
                        .requestMatchers("/blog/user/getAllUsers").permitAll()
                        .requestMatchers("/blog/articles/all").permitAll()
                        .requestMatchers("/blog/articles/topic/**").permitAll()
                        .requestMatchers("/blog/user/create").permitAll()
                        .requestMatchers("/blog/articles/create").permitAll()
                        .requestMatchers("/blog/articles/all").permitAll()
                        .requestMatchers("/blog/topics/create").permitAll()
                        .requestMatchers("/blog/articleContents/create").permitAll()
                        .requestMatchers("/blog/login").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthorizationEntryPoint))
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(Customizer.withDefaults());

        //Lista de cambios deprecated -> https://docs.spring.io/spring-security/site/docs/current/api/deprecated-list.html

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
