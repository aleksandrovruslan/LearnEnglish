package ru.aleksandrov.Controllers;

import ru.aleksandrov.DAO.WordDAO;
import ru.aleksandrov.Models.User;
import ru.aleksandrov.Models.Word;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "myWordsServlet", urlPatterns = "/mywords")
public class MyWordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/myWords.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId;
        try {
            userId = user.getUserId();
        } catch (NullPointerException e){
            userId = 0;
        }
        if(userId > 0){
            try {
                WordDAO wordDAO = new WordDAO();
                List<Word> words = wordDAO.getWords(userId);
                request.setAttribute("words", words);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/views/myWords.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
}
