package ru.aleksandrov.service;

import com.sun.deploy.net.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.DAO.QuizDAO;
import ru.aleksandrov.messages.ErrorMessage;
import ru.aleksandrov.messages.InfoMessage;
import ru.aleksandrov.messages.Message;
import ru.aleksandrov.models.Quiz;
import ru.aleksandrov.models.Word;
import ru.aleksandrov.util.ValidatorUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.*;

public class AddWordService implements Service {
    private static final Logger log = LogManager.getLogger(AddWordService.class);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;
    private Map<String, Object> variables;
    private String template;
    private Quiz quiz;
    private Word englishWord;
    private Set<Word> translation;

    public AddWordService (HttpServletRequest request, HttpServletResponse response
            , ServletContext context, Map<String, Object> variables
            , String template) {
        this.request = request;
        this.response = response;
        this.context = context;
        this.variables = variables;
        this.template = template;
    }

//    @Override
//    public void performPost(HttpServletRequest request, HttpServletResponse response, Map<String, Object> variables)
//            throws ServletException, IOException {
//        this.request = request;
//        this.response = response;
//        this.variables = variables;
//
//        try {
//            extractData();
//            new InfoMessage(variables, addWord());
//        } catch (RuntimeException e){
//            new ErrorMessage(variables, e.getMessage());
//        } catch (Exception e) {
//            log.error("doPost():", e);
//            new ErrorMessage(variables, "Uppps!");
//        }
//    }
//
//    @Override
//    public void performGet(HttpServletRequest request, HttpServletResponse response, Map<String, Object> variables)
//            throws ServletException, IOException {
//        new InfoMessage(variables, "Enter the data.");
//    }

    private String addWord() {
        quiz = new Quiz();
        quiz.setWord(englishWord);
        quiz.setTranslation(translation);
        return new QuizDAO().addQuiz(quiz);
    }

    @Override
    public void performPost() throws IOException {
        try {
            extractData();
            new InfoMessage(variables, addWord());
        } catch (RuntimeException e){
            new ErrorMessage(variables, e.getMessage());
        } catch (Exception e) {
            log.error("doPost():", e);
            new ErrorMessage(variables, "Uppps!");
        }
    }

    @Override
    public void performGet() throws IOException {
        new InfoMessage(variables, "Enter the data.");
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

    private void extractData() {
        ExtractData extractData = (s, r) -> {
            String data = r.getParameter(s);
            data = data.trim();
            if ("".equals(data)) {
                throw new IllegalArgumentException("\"" + s + "\"" + " field empty.");
            }
            return data;
        };

        String english = extractData.validate("english", request);
        try {
            english = english.trim().toLowerCase();
            if (english.equals("")) {
                throw new NullPointerException();
            }
            englishWord = new Word();
            englishWord.setName(english);
            validation(englishWord);
        } catch (NullPointerException e) {
            new ErrorMessage(variables, "English field is empty.");
        }

        translation = new HashSet<>();
        String[] russians = request.getParameterValues("russian");

        System.out.println(Arrays.stream(russians).filter(r -> "".equals(r)));

        for (String s:russians) {
            if (s != null) {
                s = s.trim().toLowerCase();
                if (!"".equals(s)) {
                    Word word = new Word();
                    word.setName(s);
                    translation.add(word);
                }
            }
        }
        if (translation.size() == 0) {
            new ErrorMessage(variables, "Russian fields is empty");
        }
    }

    private void validation(Object o) {
        ValidatorUtil<Object> validator = new ValidatorUtil<>();
        if (!validator.verifyEntity(o)) {
//            Message errors = new ErrorMessage(variables);
            for (ConstraintViolation<Object> v:validator.getErrors()) {
//                errors.putMessage(v.getMessage());
            }
        }
    }
}
