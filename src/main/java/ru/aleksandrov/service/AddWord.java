package ru.aleksandrov.service;

import ru.aleksandrov.DAO.WordDAO;
import ru.aleksandrov.models.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

public class AddWord {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private User user;
    private Collection collection;
    private Word englishWord;
    private Set<Word> translations = new HashSet<>(0);
    private Word word;

    public AddWord(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String runner() throws UnsupportedEncodingException {
        extractDataSession();
        return new WordDAO().addWord(word);
    }

    private void extractDataSession() {
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        if (user == null) {
            throw new IllegalStateException("User unknown.");
        }

        String english = request.getParameter("english").trim().toLowerCase();
        if (english.isEmpty()) {
            throw new IllegalStateException("English word empty.");
        }
        englishWord = new Word();
        englishWord.setName(english);

        String[] russian = request.getParameterValues("russian");
        for (String r:russian) {
            if (r != null && !r.trim().isEmpty()) {
                Word translation = new Word();
                translation.setName(r.trim().toLowerCase());
                translations.add(translation);
            }
        }
        if (translations.size() == 0) {
            throw new IllegalStateException("Translation word empty");
        }

        String collectionName = request.getParameter("collection");
        if (collectionName == null || collectionName.isEmpty()) {
            collection = new Collection();
            collection.setName("Default");
        } else {
            collection.setName(collectionName);
        }
    }
}
