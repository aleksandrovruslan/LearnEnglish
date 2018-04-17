package ru.aleksandrov.service;

import ru.aleksandrov.DAO.CollectionDAO;
import ru.aleksandrov.models.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class AddCollectionService implements Service {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;
    private Map<String, Object> variables;
    private String template;
    private User user;

    public AddCollectionService(HttpServletRequest request, HttpServletResponse response
            , ServletContext context, Map<String, Object> variables
            , String template) {
        this.request = request;
        this.response = response;
        this.context = context;
        this.variables = variables;
        this.template = template;
    }

    @Override
    public void performPost() throws IOException {
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        addCollection();
    }

    @Override
    public void performGet() throws IOException {
        variables.put("types", CollectionTypeService.getTypes());
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

    private void addCollection() {
        int collectionTypeId;
        try {
            collectionTypeId = Integer.parseInt(request.getParameter("collectionTypeId"));
        } catch (NumberFormatException e) {
            collectionTypeId = CollectionTypeService.getDefaultTypeId();
        }
        String collectionName = request.getParameter("collectionName");
        variables.put("types", CollectionTypeService.getTypes());
        new CollectionDAO().addCollection(user, collectionTypeId, collectionName, variables);
    }
}
