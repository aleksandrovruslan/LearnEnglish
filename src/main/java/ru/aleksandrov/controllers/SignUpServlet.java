package ru.aleksandrov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.service.CommonThymeleafHandler;
import ru.aleksandrov.service.SignUp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(SignUpServlet.class);
    private String template = "signup/signup";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommonThymeleafHandler.process(request, response);
        Map<String, Object> variables = new HashMap<>();
        SignUp signUp = new SignUp(request, response);
        try {
            variables.put("message", signUp.runner());
        } catch (IllegalStateException e) {
            variables.put("messageError", e.getMessage());
        } catch (Exception e ) {
            log.error("doPost(): ", e);
            variables.put("messageError", "uppps");
        }
        CommonThymeleafHandler.processTemplate(request, response, template, variables, getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommonThymeleafHandler.process(request, response);
        CommonThymeleafHandler.processTemplate(request, response, template, getServletContext());
    }
}
