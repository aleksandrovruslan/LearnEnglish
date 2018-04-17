package ru.aleksandrov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.service.Handler;
import ru.aleksandrov.service.SignInService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "SignInServlet", urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(SignInServlet.class);
    private String template = "signin/signin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handler(new SignInService(request, response, getServletContext()
                , new HashMap<>(), template)).performPost();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handler(new SignInService(request, response, getServletContext()
                , new HashMap<>(), template)).performGet();
    }
}
