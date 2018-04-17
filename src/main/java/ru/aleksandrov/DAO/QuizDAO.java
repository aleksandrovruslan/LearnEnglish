package ru.aleksandrov.DAO;

import ru.aleksandrov.models.Quiz;

public class QuizDAO {
    public String addQuiz(Quiz quiz) {
        //add Hibernate or JDBC
//        throw new IllegalStateException("Word not added.");
        return quiz.toString();
    }
}
