package org.example.springauthorizationserver.config;

import org.example.springauthorizationserver.service.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class DefaultSecurityConfig {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //Tutte le richieste che arrivano qui, devono essere autenticate
        http.authorizeHttpRequests(authorizeRequest->
                authorizeRequest.anyRequest().authenticated()
        )
                .formLogin(Customizer.withDefaults()); //Utilizziamo il default login per l'authorization server
        return http.build();
    }

    //Metodo per fare il bind tra il customAuthenticationProvider e il nostro authenticationManagerBuilder
    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider);
    }
}
