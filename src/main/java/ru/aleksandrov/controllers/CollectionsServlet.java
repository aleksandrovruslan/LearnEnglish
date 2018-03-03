package ru.aleksandrov.controllers;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import ru.aleksandrov.service.CommonThymeleafHandler;
import ru.aleksandrov.util.ThymeleafUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CollectionsServlet", urlPatterns = "/collections")
public class CollectionsServlet extends HttpServlet {
    private String template = "collections/collections";
    private Map<String, Object> variables = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommonThymeleafHandler.process(request, response);
        List<String> collections = new ArrayList<String>();
        collections.add("Programmers");
        collections.add("Travel");
        collections.add("Medical");
        collections.add("Common");
        variables.put("collections", collections);
        CommonThymeleafHandler.processTemplate(request, response, template, variables, getServletContext());
    }
}
