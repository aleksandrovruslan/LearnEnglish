package ru.aleksandrov.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.DAO.RoleDAO;
import ru.aleksandrov.DAO.UserDAO;
import ru.aleksandrov.models.*;
import ru.aleksandrov.models.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class SignUpService implements Service {
    private static final Logger log = LogManager.getLogger(SignUpService.class);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;
    private Map<String, Object> variables;
    private String template;
    private List<String> errors = new ArrayList<>();
    private List<String> messages = new ArrayList<>();
    private User user;

    public SignUpService (HttpServletRequest request, HttpServletResponse response
            , ServletContext context, Map<String, Object> variables
            , String template) {
        this.request = request;
        this.response = response;
        this.context = context;
        this.variables = variables;
        this.template = template;
    }

    private String runner() {
        extractData();
        return new UserDAO().addUser(user);
    }

    private void extractData() {
        //TODO add data verification and remove all "if" in lambdas
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("The name is not entered correctly.");
        }
        String login = request.getParameter("login");
        if (login == null || login.isEmpty()) {
            throw  new IllegalStateException("The login is not entered correctly.");
        }
        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            throw new IllegalStateException("The email is not entered correctly.");
        }
        String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
            throw new IllegalStateException("The password is not entered correctly.");
        }
        user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(RoleDAO.getUserRole());

        CollectionType type = new CollectionType();
        type.setName("Public");

        Collection programming = new Collection();
        programming.setName("Programming");
        programming.setType(type);

        Collection travel = new Collection();
        travel.setName("Travel");
        travel.setType(type);

        Word gateway = new Word();
        gateway.setName("gateway");
        Word gatewayT = new Word();
        gatewayT.setName("Шлюз");
        Set<Word> translations = new HashSet<>();
        translations.add(gatewayT);

        Quiz quiz = new Quiz();
        quiz.setWord(gateway);
        quiz.setTranslation(translations);
        Set<Quiz> quizzes = new HashSet<>();
        quizzes.add(quiz);

        programming.setQuizzes(quizzes);
        travel.setQuizzes(quizzes);

        Set<Collection> collections = new HashSet<>();
        collections.add(programming);
        collections.add(travel);
        user.setCollections(collections);
    }

    @Override
    public void performPost() throws ServletException, IOException {
        try {
            messages.add(runner());
            variables.put("messages", messages);
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
        messages = new ArrayList<>();
        messages.add("Enter the data.");
        variables.put("messages", messages);
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
