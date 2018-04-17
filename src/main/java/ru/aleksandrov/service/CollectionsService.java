package ru.aleksandrov.service;

import ru.aleksandrov.DAO.CollectionDAO;
import ru.aleksandrov.models.Collection;
import ru.aleksandrov.models.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class CollectionsService implements Service{
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;
    private Map<String, Object> variables;
    private String template;
    private HttpSession session;
    private User user;

    public CollectionsService (HttpServletRequest request, HttpServletResponse response
            , ServletContext context, Map<String, Object> variables
            , String template) {
        this.request = request;
        this.response = response;
        this.context = context;
        this.variables = variables;
        this.template = template;
        session = request.getSession();
        user = (User) session.getAttribute("user");
    }

    public void initUserCollections() {
        variables.put("collections"
                , new CollectionDAO().initUserCollections(user));
    }

    public Collection getCollection() {
        long id = Long.parseLong(request.getParameter("id"));
        return new CollectionDAO().getCollection(id);
    }

    public void addCollectionPost() {
        int collectionTypeId;
        try {
            collectionTypeId = Integer.parseInt(request.getParameter("collectionTypeId"));
        } catch (NumberFormatException e) {
            collectionTypeId = CollectionTypeService.getDefaultTypeId();
        }
        String collectionName = request.getParameter("collectionName");
        addCollectionGet();
        new CollectionDAO().addCollection(user, collectionTypeId, collectionName, variables);
    }

    public void addCollectionGet() {
        variables.put("types", CollectionTypeService.getTypes());
    }

    public void removeCollection(Collection collection) {

    }

    @Override
    public void performPost() throws IOException {

    }

    @Override
    public void performGet() throws IOException {
        initUserCollections();
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
}
