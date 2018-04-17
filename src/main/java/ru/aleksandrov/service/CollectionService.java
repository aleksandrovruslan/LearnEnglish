package ru.aleksandrov.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.DAO.CollectionDAO;
import ru.aleksandrov.models.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionService implements Service{
    public static final Logger log = LogManager.getLogger(CollectionService.class);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;
    private Map<String, Object> variables;
    private String template;

    public CollectionService (HttpServletRequest request, HttpServletResponse response
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

    }

    @Override
    public void performGet() throws IOException {
        action();
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

    private void action() {
        String action = request.getParameter("action");
        List<String> errors = new ArrayList<>();
        if (action != null && !action.equals("")) {
            if ("show".equals(action)) {
                try {
                    Long id = Long.parseLong(request.getParameter("id"));
                    if (id == null || id > 0) {
                        Collection collection = new CollectionDAO().getCollection(id);
                        variables.put("collection", collection);
                    } else {
                        variables.put("errors", errors.add("Collection not found"));
                    }
                } catch (Exception e) {
                    log.error("action(): " + e);
                    variables.put("errors", errors.add("Collection not found"));
                }
            }
        }
    }
}
