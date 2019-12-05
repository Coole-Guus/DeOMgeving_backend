package org.example.service;

import org.example.persistence.UserDAO;
import org.example.util.CryptographicUtils;

import javax.inject.Inject;

public class UserService {

    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void forgotPassword(String email) {
        String password_reset_token = String.valueOf(CryptographicUtils.generateFourDigitNumber());
        userDAO.setToken(email, password_reset_token);
    }
}
