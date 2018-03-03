package ru.aleksandrov.controllers;

import ru.aleksandrov.service.CommonThymeleafHandler;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", "/"}, loadOnStartup = 0)
public class HomeServlet extends HttpServlet {
    private String template = "home/home";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommonThymeleafHandler.process(request, response);
        CommonThymeleafHandler.processTemplate(request, response, template, getServletContext());
    }

//    TODO Hibernate runner, this runner conflicts with hibernate
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//    }
}
