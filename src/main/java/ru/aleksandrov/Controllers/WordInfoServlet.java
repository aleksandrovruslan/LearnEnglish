package ru.aleksandrov.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.DAO.EnglishWordDAO;
import ru.aleksandrov.DAO.WordDAO;
import ru.aleksandrov.Models.User;
import ru.aleksandrov.Models.Word;
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
    private static final Logger log = LogManager.getLogger(WordInfoServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Validation valid = new Validation();
        String english = request.getParameter("english");
        try {
            if (user != null && user.getUserId() > 0 && valid.isVerifyWord(english)) {
                EnglishWordDAO englishDAO = new EnglishWordDAO();
                int englishId = englishDAO.getEnglishId(valid.getWord());
                WordDAO wordDAO = new WordDAO();
                Word word = wordDAO.getWord(user.getUserId(), englishId);
                request.setAttribute("word", word);
            }
        } catch (NullPointerException e){
            log.error("doPost: ", e);
        } catch (PropertyVetoException e) {
            log.error("doPost: ", e);
        } catch (SQLException e) {
            log.error("doPost: ", e);
        }
        request.setAttribute("message", "Word not found!");
        request.getRequestDispatcher("/views/wordInfo.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/wordInfo.jsp").forward(request, response);
    }
}
