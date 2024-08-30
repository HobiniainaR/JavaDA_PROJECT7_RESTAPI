package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Web security configuration for the application.
 * This class configures authentication and authorization management
 * for different routes in the application using Spring Security.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurerAdapter {

    /**
     * Bean for password encoding using BCrypt.
     * @return a {@link PasswordEncoder} object configured with BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures global authentication with a JDBC data source.

     * This method sets up authentication using SQL queries to retrieve users
     * and their roles from the database.
     *
     * @param auth      the {@link AuthenticationManagerBuilder} object used to configure authentication.
     * @param dataSource the data source for JDBC authentication.
     * @throws Exception if an error occurs while configuring authentication.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username ,role FROM users WHERE username = ?")
                .usersByUsernameQuery("SELECT username , password , true FROM users WHERE username = ?");
    }

    /**
     * Configures the security filter chain for authorization and authentication management.

     * This method disables CSRF protection, configures security headers, and defines authorization
     * rules for various routes in the application. It also sets up the login page and logout management.
     *
     * @param http the {@link HttpSecurity} object used to configure HTTP security.
     * @return a {@link SecurityFilterChain} object representing the configured security filter chain.
     * @throws Exception if an error occurs while configuring HTTP security.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers((headers) ->
                headers
                        .frameOptions((frameOptions) -> frameOptions.sameOrigin().disable()));
        http.authorizeHttpRequests((authorize) ->
                authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/bidList/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/curvePoint/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/admin/home").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/rating/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/ruleName/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/trade/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated()
        );
        http.formLogin(form -> {
                    form
                            .defaultSuccessUrl("/bidList/list", true)
                            .isCustomLoginPage();
                }
        );
        /* http.oauth2Login(Customizer.withDefaults()); */
        http.logout(
                logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/login.html")
                        .invalidateHttpSession(true)
        );
        return http.build();
    }
}
