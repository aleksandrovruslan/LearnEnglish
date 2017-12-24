package ru.aleksandrov.Controllers;

import java.beans.PropertyVetoException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ru.aleksandrov.DAO.EnglishWordDAO;
import ru.aleksandrov.DAO.RussianWordDAO;
import ru.aleksandrov.DAO.WordDAO;
import ru.aleksandrov.Models.*;
import ru.aleksandrov.Util.Validation;

@WebServlet(name = "AddWordServlet", urlPatterns = "/addword")
public class AddWordServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AddWordServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String message = "Word not added!";
        if(user != null && user.getUserId() > 0){
            String english = request.getParameter("english");
            String[] russian = request.getParameterValues("russian");
            Validation valid = new Validation();
            if(valid.isVerifyWords(russian) && valid.isVerifyWord(english)){
                try {
                    EnglishWordDAO englishDAO = new EnglishWordDAO();
                    english = valid.getWord();
                    EnglishWord englishWord = new EnglishWord();
                    englishWord.setEnglishWord(english);
                    int englishId = englishDAO.addEnglishWord(englishWord);
                    englishWord.setEnglishId(englishId);

                    RussianWordDAO russianDAO = new RussianWordDAO();
                    Set<String> russianStrings = valid.getWords();
                    List<RussianWord> russianWords = russianDAO.addRussianWords(russianStrings);

                    WordDAO wordDAO = new WordDAO();
                    Word word = new Word();
                    word.setEnglish(englishWord);
                    word.setRussian(russianWords);
                    word.setUser(user);
                    word.setCollection(new WordsCollection(1, "default"));
                    word.setNumberAnswers(0);
                    word.setCorrectAnswers(0);

                    if(wordDAO.isAddWords(word)) {
                        message = "Word added!";
                    }
                } catch (PropertyVetoException e) {
                    log.error("doPost(): ", e);
                } catch (SQLException e) {
                    log.error("doPost(): ", e);
                }
            }
        } else {
            message = "Login or register, please.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/views/addWord.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/addWord.jsp").forward(request, response);
    }
}
