package org.example.springsecurityclient.Event;

import lombok.Getter;
import lombok.Setter;
import org.example.springsecurityclient.Entity.User;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private final User user;
    private final String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
