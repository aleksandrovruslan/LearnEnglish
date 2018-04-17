package ru.aleksandrov.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.DAO.UserDAO;
import ru.aleksandrov.models.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignInService implements Service {
    private static final Logger log = LogManager.getLogger(SignInService.class);

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;
    private Map<String, Object> variables;
    private String template;
    private List<String> errors;
    private User user;

    public SignInService (HttpServletRequest request, HttpServletResponse response
            , ServletContext context, Map<String, Object> variables
            , String template) {
        this.request = request;
        this.response = response;
        this.context = context;
        this.variables = variables;
        this.template = template;
    }

    private void checkUser() throws ServletException, IOException {
        extractData();
        user = new UserDAO().checkUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        checkUserEmpty();
    }

    private void checkUserEmpty() throws ServletException, IOException {
        if (user != null) {
            request.getRequestDispatcher("/home").forward(request, response);
        }
    }

    private void extractData() {
        String login = request.getParameter("login");
        if (login.trim().isEmpty() || "".equals(login.trim())) {
            throw new IllegalStateException("Login no entered.");
        }
        String password = request.getParameter("password");
        if (password.trim().isEmpty() || "".equals(password.trim())) {
            throw new IllegalStateException("Password no entered.");
        }
        user = new User();
        user.setLogin(login);
        user.setPassword(password);
    }

    @Override
    public void performPost() throws IOException {
        errors = new ArrayList<>();
        try {
            checkUser();
        } catch (IllegalStateException e) {
            errors.add(e.getMessage());
            variables.put("errors", errors);
        } catch (Exception e ) {
            log.error("doPost(): ", e);
            errors.add("Uppps!");
            variables.put("errors", errors);
        }
    }

    @Override
    public void performGet() throws ServletException, IOException {
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        checkUserEmpty();
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
