package ru.aleksandrov.Controllers;

import java.beans.PropertyVetoException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Models.Role;
import ru.aleksandrov.Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import ru.aleksandrov.DAO.UserDAO;
import ru.aleksandrov.Util.Validation;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegistrationServlet.class);
    private static final int DEFAULT_ROLE_ID = 3;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role defaultRole = new Role(DEFAULT_ROLE_ID, "User");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Validation valid = new Validation();
        if (valid.isVerifyName(name) && valid.isVerifyLogin(login)
                && valid.isVerifyPassword(password) && valid.isVerifyEmail(email)) {
            try {
                User user = new User(0, valid.getName(), valid.getLogin(),
                        valid.getPassword(), valid.getEmail(), defaultRole);
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
        } else {
            request.getSession().setAttribute("message", "Registration failed.");
            request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
    }
}