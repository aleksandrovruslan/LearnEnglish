package ru.aleksandrov.controllers;

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
import ru.aleksandrov.DAO.WordDAO;
import ru.aleksandrov.Entity.*;

@WebServlet(name = "AddWordServlet", urlPatterns = "/addword")
public class AddWordServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AddWordServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String message = null;
        if(user != null && user.getUserId() > 0){
            String english = request.getParameter("english");
            String[] russian = request.getParameterValues("russian");
            //TODO 08.12 class validator and this class
            if(!english.isEmpty() && russian != null && russian.length > 0){
                try {
                    Word word = new Word();
                    word.setEnglish(new EnglishWord(1, english));
                    for(String r:russian){
                        word.setRussian(new RussianWord(1, r));
                    }
                    word.setUser(user);
                    word.setNumberAnswers(0);
                    word.setCorrectAnswers(0);
                    word.setCollection(new WordsCollection(1, "default"));
                    WordDAO wordDAO = new WordDAO();
                    if(wordDAO.isAddWord(word)){
                        message = "Word added!";
                    }
                } catch (PropertyVetoException e) {
                    log.error("doPost(): ", e);
                } catch (SQLException e) {
                    log.error("doPost(): ", e);
                }
            }
        }
        message = "Word not added!";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/views/addword.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/addword.jsp").forward(request, response);
    }
}
