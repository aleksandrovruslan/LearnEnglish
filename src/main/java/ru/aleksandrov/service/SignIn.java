package ru.aleksandrov.service;

import ru.aleksandrov.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private User user;

    public SignIn(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
}
