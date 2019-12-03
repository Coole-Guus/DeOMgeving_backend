package org.example.resource;

import org.example.service.UserService;

public class UserResource {
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }
}
