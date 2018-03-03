package ru.aleksandrov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.models.*;
import ru.aleksandrov.service.AddWord;
import ru.aleksandrov.service.CommonThymeleafHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddWordServlet", urlPatterns = "/addword")
public class AddWordServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AddWordServlet.class);
    private String template = "addword/addword";
    private Map<String, Object> variables = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommonThymeleafHandler.process(request, response);
        AddWord addWord = new AddWord(request, response);
        variables.clear();
        try {
            variables.put("message", addWord.runner());
        } catch (IllegalStateException e){
            variables.put("messageError", e.getMessage());
        }
        catch (Exception e) {
            log.error("doPost():", e);
            variables.put("messageError", "uppps");
        }
        CommonThymeleafHandler.processTemplate(request, response, template, variables, getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommonThymeleafHandler.process(request, response);
        HttpSession session = request.getSession();
        //TODO delete and transfer to the login
        User user = new User();
        user.setName("Admin");
        Role role = new Role();
        role.setName("Administrator");
        user.setRole(role);
        session.setAttribute("user", user);
        CommonThymeleafHandler.processTemplate(request, response, template, getServletContext());
    }
}