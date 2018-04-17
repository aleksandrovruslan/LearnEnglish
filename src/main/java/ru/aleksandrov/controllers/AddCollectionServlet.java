package ru.aleksandrov.controllers;

import ru.aleksandrov.service.AddCollectionService;
import ru.aleksandrov.service.Handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AddCollectionServlet", urlPatterns = "/addcollection")
public class AddCollectionServlet extends HttpServlet {
    private static final String TEMPLATE = "addcollection/addcollection";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handler(new AddCollectionService(request, response, getServletContext()
                , new HashMap<>(), TEMPLATE)).performPost();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handler(new AddCollectionService(request, response, getServletContext()
                , new HashMap<>(), TEMPLATE)).performGet();
    }
}
