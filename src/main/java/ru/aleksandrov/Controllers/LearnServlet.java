package ru.aleksandrov.Controllers;

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

@WebServlet(name = "LearnServlet", urlPatterns = "/learn")
public class LearnServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int rand = (int) session.getAttribute("rand");
        Word word = (Word) session.getAttribute("randWord");
        String message = "answer is empty";
        if(rand == 0){
            String[] russian = request.getParameterValues("russian");
        }
        if(rand == 1){
            String engish = request.getParameter("english");
            Validation valid = new Validation();
            if(valid.isVerifyWord(engish)){
                if(valid.getWord().equals(word.getEnglish().getEnglishWord())){
                    message = "answer is true";
                } else {
                    message = "answer is incorrect";
                }
            }
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
            WordDAO wordDAO;
            List<Word> words = (List<Word>) session.getAttribute("words");
            if(words == null) {
                try {
                    wordDAO = new WordDAO();
                    words = wordDAO.getWords(userId);
                    if(words == null){
                        request.setAttribute("message", "add words, please");
                        request.getRequestDispatcher("/views/addWord.jsp").forward(request, response);
                    }
                    session.setAttribute("words", words);
                    request.setAttribute("words", words);
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
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
