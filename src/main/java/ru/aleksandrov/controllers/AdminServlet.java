package ru.aleksandrov.controllers;

import java.beans.PropertyVetoException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Entity.User;
import ru.aleksandrov.DAO.DBConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import ru.aleksandrov.DAO.UserDAO;

//TODO remake until 03.11
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AdminServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = 0;
        try{
            userId = Integer.parseInt(request.getParameter("userId"));
        }catch (NumberFormatException n){
            n.printStackTrace();
        }
        String action = request.getParameter("action");
        try {
            UserDAO userDAO = new UserDAO();
            if ("delete".equals(action) && userId != 0) {
                boolean del = userDAO.isDeleteUser(userId);
                log.info(userId + " - " + del);
            }
            List<User> users = userDAO.getUsers();
            request.setAttribute("users", users);
        } catch (PropertyVetoException e) {
            log.error("doGet(): ", e);
        } catch (SQLException e) {
            log.error("doGet(): ", e);
        }
        request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
    }
}
