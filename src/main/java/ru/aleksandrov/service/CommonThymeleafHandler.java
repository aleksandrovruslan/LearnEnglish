package ru.aleksandrov.service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public final class CommonThymeleafHandler {
    //TODO View the common for all servlets or not and fix it
    private static TemplateEngine engine = null;

    public static void process(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        request.setCharacterEncoding("UTF-8");
    }

    public static void processTemplate(HttpServletRequest request, HttpServletResponse response, String template, ServletContext context) throws IOException {
        WebContext webContext = new WebContext(request, response
                , context);
        if (engine == null) {
            initTemplateEngine(context);
        }
        engine.process(template, webContext, response.getWriter());
    }

    public static void processTemplate(HttpServletRequest request, HttpServletResponse response, String template, Map<String, Object> variables, ServletContext context) throws IOException {
        WebContext webContext = new WebContext(request, response
                , context, request.getLocale() ,variables);
        if (engine == null) {
            initTemplateEngine(context);
        }
        engine.process(template, webContext, response.getWriter());
    }

    private static void initTemplateEngine(ServletContext context) {
        ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver(context);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setCacheable(true);
        resolver.setCacheTTLMs(600000L);
        resolver.setCharacterEncoding("UTF-8");
        engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
    }
}
