package org.example.springsecurityclient.Controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springsecurityclient.Service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegistrationController {

    private UserService userService;
}
