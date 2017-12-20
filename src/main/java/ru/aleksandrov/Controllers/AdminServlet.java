package ru.aleksandrov.Controllers;

import java.beans.PropertyVetoException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import ru.aleksandrov.DAO.UserDAO;

//TODO add the function of adding and editing roles, editing users and access filter
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AdminServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int roleId = user.getRole().getRoleId();
        String action = request.getParameter("action");
        if(roleId == 1) {
            try {
                UserDAO userDAO = new UserDAO();
                if ("deleteUser".equals(action)) {
                    int variableUserId = Integer.parseInt(request
                            .getParameter("variableUserId"));
                    userDAO.isDeleteUser(variableUserId);
                } else if ("editUser".equals(action)) {

                } else if ("deleteRole".equals(action)) {

                } else if ("editRole".equals(action)) {

                }
                List<User> users = userDAO.getUsers();
                request.setAttribute("users", users);
            } catch (NumberFormatException e) {
                log.error("doGet: ", e);
            } catch (NullPointerException e) {
                log.debug("doGet(): ", e);
            } catch (PropertyVetoException e) {
                log.error("doGet(): ", e);
            } catch (SQLException e) {
                log.error("doGet(): ", e);
            }
            request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
}
