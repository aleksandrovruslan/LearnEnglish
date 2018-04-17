package ru.aleksandrov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.models.CollectionType;
import ru.aleksandrov.service.Handler;
import ru.aleksandrov.service.HomeService;
import ru.aleksandrov.service.Service;
import ru.aleksandrov.util.CommonThymeleafHandler;
import ru.aleksandrov.util.JPAUtil;
import ru.aleksandrov.util.ThymeleafHandler;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", "/"}, loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
    public static final Logger log = LogManager.getLogger(HomeServlet.class);
    private String template = "home/home";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        new Handler(new HomeService(request, response, getServletContext()
                , new HashMap<>(), template)).performGet();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        EntityManager manager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        manager.getTransaction().begin();
        try {
            CollectionType publicType = new CollectionType();
            publicType.setName("Public");
            manager.persist(publicType);

            CollectionType privateType = new CollectionType();
            privateType.setName("Private");
            manager.persist(privateType);

            CollectionType friendlyType = new CollectionType();
            friendlyType.setName("Friendly");
            manager.persist(friendlyType);

            CollectionType protectedType = new CollectionType();
            protectedType.setName("Protected");
            manager.persist(protectedType);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            log.error("init(): " + e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        JPAUtil.close();
    }
}
