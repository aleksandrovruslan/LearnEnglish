package ru.aleksandrov.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

abstract public class WrapperService implements Service {
    protected Service service;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;
    private Map<String, Object> variables;
    private String template;

    public WrapperService(Service service) {
        this.service = service;
        this.request = service.getRequest();
        this.response = service.getResponse();
        this.context = service.getContext();
        this.variables = service.getVariables();
        this.template = service.getTemplate();
    }

    @Override
    public void performPost() throws ServletException, IOException {
        service.performPost();
    }

    @Override
    public void performGet() throws ServletException, IOException {
        service.performGet();
    }
}
