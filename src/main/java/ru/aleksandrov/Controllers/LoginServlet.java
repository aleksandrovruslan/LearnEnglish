package ru.aleksandrov.Controllers;

import java.beans.PropertyVetoException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.DAO.UserDAO;
import ru.aleksandrov.Models.User;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        if(!login.isEmpty() && !password.isEmpty()) {
            try {
                UserDAO userDAO = new UserDAO();
                User userVerified = userDAO.verifyUser(user);
                if (userVerified.getUserId() > 0) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", userVerified);
                    request.getRequestDispatcher("/views/home.jsp").forward(request, response);
                }
            } catch (PropertyVetoException e) {
                log.error("doPost(): ", e);
            } catch (SQLException e) {
                log.error("doPost(): ", e);
            }
        }
        request.setAttribute("message", "Incorrect username or password.");
        request.getRequestDispatcher("/views/login.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request,response);
    }
}
