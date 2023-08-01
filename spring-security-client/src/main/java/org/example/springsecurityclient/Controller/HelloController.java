package org.example.springsecurityclient.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Principal;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
public class HelloController {

    @Autowired
    private WebClient webClient; //utilizzato per ottenere gli utenti dal resource server

    @GetMapping("/hello")
    public String hello(@RegisteredOAuth2AuthorizedClient("api-client-authorization-code")
                            OAuth2AuthorizedClient client) {
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8090/hello")
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @RequestMapping("/home")
    public String showHomePage () {
        return "Home Page";
    }

    @RequestMapping("/protected")
    public String commonPage () {
        return "Page in common";
    }

    @RequestMapping("/user")
    public String userPage () {
        return "Welcome to the User Page";
    }

    @RequestMapping("/manager")
    public String adminPage () {
        return "Welcome to the admin Page";
    }

    @GetMapping("/users")
    public String[] users(
            @RegisteredOAuth2AuthorizedClient("api-client-authorization-code")
            OAuth2AuthorizedClient client){
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8090/users")
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }

    @GetMapping("/managers")
    public String adminPage(
            @RegisteredOAuth2AuthorizedClient("api-client-authorization-code")
            OAuth2AuthorizedClient client){
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8090/managers")
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

