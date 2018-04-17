package ru.aleksandrov.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface Service {
//    void performPost(HttpServletRequest request
//            , HttpServletResponse response, ServletContext context
//            , Map<String, Object> variables, String template) throws ServletException, IOException ;
//
//    void performGet(HttpServletRequest request
//            , HttpServletResponse response, ServletContext context
//            , Map<String, Object> variables, String template) throws ServletException, IOException ;

    void performPost() throws ServletException, IOException;
    void performGet() throws ServletException, IOException;

    HttpServletRequest getRequest();
    HttpServletResponse getResponse();
    ServletContext getContext();
    Map<String, Object> getVariables();
    String getTemplate();
}
