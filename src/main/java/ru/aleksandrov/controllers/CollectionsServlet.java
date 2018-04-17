package ru.aleksandrov.controllers;

import ru.aleksandrov.service.CollectionService;
import ru.aleksandrov.service.Handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CollectionsServlet", urlPatterns = "/collections")
public class CollectionsServlet extends HttpServlet {
    private String template = "collections/collections";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handler(new CollectionService(request, response, getServletContext()
                , new HashMap<>(), template)).performGet();
    }
}
