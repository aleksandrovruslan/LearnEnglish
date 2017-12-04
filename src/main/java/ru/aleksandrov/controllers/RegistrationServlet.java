package ru.aleksandrov.controllers;

import java.beans.PropertyVetoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Entity.Role;
import ru.aleksandrov.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import ru.aleksandrov.DAO.UserDAO;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegistrationServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO remake until 03.11
        Role defaultRole = new Role(3, "User");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (!name.isEmpty() && !login.isEmpty()
                && !password.isEmpty() && !email.isEmpty()) {
            try {
                User user = new User(0, name, login, password, email, defaultRole);
                UserDAO userDAO = new UserDAO();
                if(userDAO.isAddUser(user)){
                    request.setAttribute("message", "Registration successful!");
                    request.getRequestDispatcher("/views/home.jsp").forward(request,response);
                }
            } catch (PropertyVetoException e) {
                log.error("doPost(): ", e);
            } catch (SQLException e) {
                log.error("doPost: ", e);
            }
        }
        request.getSession().setAttribute("message", "Registration failed.");
        request.getRequestDispatcher("/views/registration.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
    }
}