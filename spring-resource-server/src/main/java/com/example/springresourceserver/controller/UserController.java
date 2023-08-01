package com.example.springresourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/users")
    public String[] getUsers() {
        return new String[]{"Mario", "Luigi", "Pierino"};
    }

    @GetMapping("/managers")
    public String getAdmin(Principal principal){
        String s = "Welcome "+principal.getName()+" to the admin page. This is the list of users: Mario, Luigi, Pierino, Marco";
        return s;
    }
    @GetMapping("/hello")
    public String getHello(Principal principal){
        return "Hello "+principal.getName()+" to the web page";
    }


}
