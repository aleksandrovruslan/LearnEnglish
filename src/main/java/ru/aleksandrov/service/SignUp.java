package ru.aleksandrov.service;

import ru.aleksandrov.DAO.RoleDAO;
import ru.aleksandrov.DAO.UserDAO;
import ru.aleksandrov.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private User user;

    public SignUp(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String runner() {
        extractData();
        return new UserDAO().addUser(user);
    }

    private void extractData() {
        //TODO add data verification and remove all "if" in lambdas
        String name = request.getParameter("name").trim().toLowerCase();
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("The name is not entered correctly.");
        }
        String login = request.getParameter("login").trim().toLowerCase();
        if (login == null || login.isEmpty()) {
            throw  new IllegalStateException("The login is not entered correctly.");
        }
        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            throw new IllegalStateException("The email is not entered correctly.");
        }
        String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
            throw new IllegalStateException("The password is not entered correctly.");
        }
        user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(RoleDAO.getUserRole());
    }
}
