package org.example.springsecurityclient.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    //Lista di URL che è possibile chiamare senza essere autenticati
    private static final String[] WHITE_LIST_URLS = {
            "/register",
            "/verifyRegistration*",
            "/resendVerifyToken*"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(WHITE_LIST_URLS).permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/protected").hasAnyRole("MANAGER", "USER")
                .antMatchers("/manager").hasAnyRole("MANAGER")
                .antMatchers("/user").hasRole("USER")
                //.antMatchers("/managers").hasRole("MANAGER")//togliere o rimettere questa istruzione cambia
                .antMatchers("/users").hasAnyRole("USER", "MANAGER")
                .and()
                .formLogin()
                .and()
                .oauth2Login(oauth2login ->
                        oauth2login.loginPage("/oauth2/authorization/api-client-oidc"))
                .oauth2Client(Customizer.withDefaults());

        return http.build();
    }
}
