package ru.aleksandrov.Controllers;

import ru.aleksandrov.DAO.EnglishWordDAO;
import ru.aleksandrov.DAO.WordDAO;
import ru.aleksandrov.Entity.User;
import ru.aleksandrov.Entity.Word;
import ru.aleksandrov.Util.Validation;

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

@WebServlet(name = "WordInfoServlet", urlPatterns = "/wordInfo")
public class WordInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Validation valid = new Validation();
        String  english = request.getParameter("english");
        if(user != null && user.getUserId() > 0 && valid.isVerifyWord(english)) {
            try {
                EnglishWordDAO englishDAO = new EnglishWordDAO();
                int englishId = englishDAO.getEnglishId(valid.getWord());
                WordDAO wordDAO = new WordDAO();
                List<Word> words = wordDAO.getWord(user.getUserId(),englishId);
                request.setAttribute("words", words);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                request.setAttribute("message", "Word mot found!");
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/views/wordInfo.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/wordInfo.jsp").forward(request, response);
    }
}
