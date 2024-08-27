package Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfigurerAdapter {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
            auth.jdbcAuthentication().dataSource(dataSource)
                    .authoritiesByUsernameQuery("SELECT email , 'user' FROM user WHERE email = ?")
                    .usersByUsernameQuery("SELECT email , password , true FROM user WHERE email = ?");
        }

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
                            .requestMatchers("/bidList/**").hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/curvePoint/**").hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/admin/home").hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/rating/**").hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/ruleName/**").hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/trade/**").hasAnyRole("USER", "ADMIN")
                            .anyRequest().authenticated()


            );
            http.formLogin(form -> {
                        form
                                .defaultSuccessUrl("/bidList/list", true)
                                .isCustomLoginPage();
                    }
            );
            http.oauth2Login(Customizer.withDefaults());
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

