package ru.aleksandrov.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger log = LogManager.getLogger(MyWordsServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/myWords.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String wordAction = request.getParameter("action");
        int userId = user.getUserId();
        try {
            if(userId > 0) {
                WordDAO wordDAO = new WordDAO();
                if("deleteWord".equals(wordAction)){
                    int variableWordEnglishId = Integer.parseInt(request.getParameter("variableWordEnglishId"));
                    wordDAO.isDeleteWord(userId, variableWordEnglishId);
                    log.info(variableWordEnglishId);
                }
                List<Word> words = wordDAO.getWords(userId);
                request.setAttribute("words", words);
            }
        } catch (NumberFormatException e){
            log.error("doGet: ", e);
        } catch (NullPointerException e){
            log.error("doGet(): ", e);
        } catch (PropertyVetoException e) {
            log.error("doGet(): ", e);
        } catch (SQLException e) {
            log.error("doGet(): ", e);
        }
        request.getRequestDispatcher("/views/myWords.jsp").forward(request, response);
    }
}
