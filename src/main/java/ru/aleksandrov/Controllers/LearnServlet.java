package ru.aleksandrov.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.DAO.WordDAO;
import ru.aleksandrov.Models.RussianWord;
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
import java.util.*;

@WebServlet(name = "LearnServlet", urlPatterns = "/learn")
public class LearnServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LearnServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int rand = (int) session.getAttribute("rand");
        Word word = (Word) session.getAttribute("randWord");
        String message;
        Validation valid = new Validation();
        boolean correct = false;
        if(rand == 0){
            String[] russian = request.getParameterValues("russian");
            if(valid.isVerifyWords(russian)){
                Set<String> verifiedRus =
                        new HashSet<String>(Arrays.asList(valid.getWords()));
                if(verifiedRus.size() == word.getRussian().size()) {
                    correct = true;
                    for(RussianWord r:word.getRussian()) {
                        if (!verifiedRus.contains(r.getRussianWord())) {
                            correct = false;
                        }
                    }
                }
            }
        } else if(rand == 1){
            String english = request.getParameter("english");
            if(valid.isVerifyWord(english)){
                if(valid.getWord().equals(word.getEnglish().getEnglishWord())){
                    correct = true;
                }
            }
        }
        word.setNumberAnswers(word.getNumberAnswers() + 1 );
        if(correct){
            word.setCorrectAnswers(word.getCorrectAnswers() + 1);
            message = "answer is true";
        } else {
            message = "answer is incorrect";
        }
        try {
            WordDAO wordDAO = new WordDAO();
            wordDAO.isUpdateWordsAnswers(word);
        } catch (PropertyVetoException e) {
            log.error("doPost(): ", e);
        } catch (SQLException e) {
            log.error("doPost(): ", e);
        }
        request.setAttribute("message", message);
        doGet(request, response);
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
            List<Word> words = (List<Word>) session.getAttribute("words");
            if(words == null) {
                try {
                    WordDAO wordDAO = new WordDAO();
                    words = wordDAO.getWords(userId);
                    if(words == null){
                        request.setAttribute("message", "add words, please");
                        request.getRequestDispatcher("/views/addWord.jsp").forward(request, response);
                    }
                    session.setAttribute("words", words);
                    request.setAttribute("words", words);
                } catch (PropertyVetoException e) {
                    log.error("doGet(): ", e);
                } catch (SQLException e) {
                    log.error("doGet(): ", e);
                }
            }
            Word randWord = words.get((int) (Math.random() * words.size()));
            session.setAttribute("randWord", randWord);
            int rand = (int) (Math.random() * 2);
            session.setAttribute("rand", rand);
            request.getRequestDispatcher("/views/learn.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
}
