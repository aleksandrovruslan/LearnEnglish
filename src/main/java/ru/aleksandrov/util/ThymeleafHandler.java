package ru.aleksandrov.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import ru.aleksandrov.service.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThymeleafHandler {
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//    private String template;
//    private ServletContext context;
//    private Service service;
//    private Map<String, Object> variables = new HashMap<>();
//
//    public ThymeleafHandler(HttpServletRequest request, HttpServletResponse response
//            , String template, ServletContext context, Service service) {
//        this.request = request;
//        this.response = response;
//        this.template = template;
//        this.context = context;
//        this.service = service;
//    }
//
//    public void processPost() throws ServletException, IOException {
//        processHeader();
//        service.performPost(request, response, variables);
//        processTemplate();
//    }
//
//    public void processGet() throws ServletException, IOException {
//        processHeader();
//        service.performGet(request, response, variables);
//        processTemplate();
//    }
//
//    private void processHeader() throws UnsupportedEncodingException {
//        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        request.setCharacterEncoding("UTF-8");
//    }
//
//    private void processTemplate() throws IOException {
//        WebContext webContext = new WebContext(request, response
//                , context, request.getLocale() ,variables);
//
//        ServletContextTemplateResolver resolver =
//                new ServletContextTemplateResolver(context);
//        resolver.setTemplateMode(TemplateMode.HTML);
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".html");
//        resolver.setCacheable(true);
//        resolver.setCacheTTLMs(600000L);
//        resolver.setCharacterEncoding("UTF-8");
//        TemplateEngine engine = new TemplateEngine();
//        engine.setTemplateResolver(resolver);
//
//        engine.process(template, webContext, response.getWriter());
//    }
}
