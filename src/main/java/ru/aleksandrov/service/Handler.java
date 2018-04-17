package ru.aleksandrov.service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class Handler implements Service {
    private Service service;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;
    private Map<String, Object> variables;
    private String template;

    public Handler(Service service) {
        this.service = service;
        this.request = service.getRequest();
        this.response = service.getResponse();
        this.context = service.getContext();
        this.variables = service.getVariables();
        this.template = service.getTemplate();
    }

    @Override
    public void performPost() throws ServletException, IOException {
        processHeader(request, response);
        service.performPost();
        processTemplate(request, response, context, variables, template);
    }

    @Override
    public void performGet() throws ServletException, IOException {
        processHeader(request, response);
        service.performGet();
        processTemplate(request, response, context, variables, template);
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    @Override
    public ServletContext getContext() {
        return context;
    }

    @Override
    public Map<String, Object> getVariables() {
        return variables;
    }

    @Override
    public String getTemplate() {
        return template;
    }

    private void processHeader(HttpServletRequest request
            , HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        request.setCharacterEncoding("UTF-8");
    }

    private void processTemplate(HttpServletRequest request
            , HttpServletResponse response, ServletContext context
            , Map<String, Object> variables, String template) throws IOException {
        WebContext webContext = new WebContext(request, response
                , context, request.getLocale() ,variables);

        ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver(context);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setCacheable(true);
        resolver.setCacheTTLMs(600000L);
        resolver.setCharacterEncoding("UTF-8");
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        engine.process(template, webContext, response.getWriter());
    }
}
