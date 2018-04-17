package ru.aleksandrov.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ServiceHandler implements Service {
    private Service service;

    public ServiceHandler(Service service) {
        this.service = service;
    }

    @Override
    public void performPost() throws ServletException, IOException {

    }

    @Override
    public void performGet() throws ServletException, IOException {

    }

    @Override
    public HttpServletRequest getRequest() {
        return null;
    }

    @Override
    public HttpServletResponse getResponse() {
        return null;
    }

    @Override
    public ServletContext getContext() {
        return null;
    }

    @Override
    public Map<String, Object> getVariables() {
        return null;
    }

    @Override
    public String getTemplate() {
        return null;
    }

//    @Override
//    public void performPost(HttpServletRequest request, HttpServletResponse response, ServletContext context, Map<String, Object> variables, String template) throws ServletException, IOException {
//        processHeader(request, response);
//        service.performPost(request, response, context, variables, template);
//        processTemplate(request, response, context, variables, template);
//    }
//
//    @Override
//    public void performGet(HttpServletRequest request, HttpServletResponse response, ServletContext context, Map<String, Object> variables, String template) throws ServletException, IOException {
//        processHeader(request, response);
//        service.performGet(request, response, context, variables, template);
//        processTemplate(request, response, context, variables, template);
//    }
}
