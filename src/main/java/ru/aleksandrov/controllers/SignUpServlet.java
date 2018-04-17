package ru.aleksandrov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.service.Handler;
import ru.aleksandrov.service.SignUpService;
import ru.aleksandrov.util.ThymeleafHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(SignUpServlet.class);
    private String template = "signup/signup";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handler(new SignUpService(request, response, getServletContext()
                , new HashMap<>(), template)).performPost();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handler(new SignUpService(request, response, getServletContext()
                , new HashMap<>(), template)).performGet();
    }
}
