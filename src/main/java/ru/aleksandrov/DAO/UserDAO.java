package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.models.User;
import ru.aleksandrov.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO {
    private static final Logger log = LogManager.getLogger(UserDAO.class);

    public String addUser(User user) throws IllegalStateException {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("from User u where" +
                " u.login like :uLogin OR u.email like :uEmail", User.class)
                .setParameter("uLogin", user.getLogin())
                .setParameter("uEmail", user.getEmail()).getResultList();
        try {
            for (User u : users) {
                if (user.getLogin().equals(u.getLogin())) {
                    throw new IllegalStateException("Login is busy!");
                }
                if (user.getEmail().equals(u.getEmail())) {
                    throw new IllegalStateException("Email is busy!");
                }
            }
            entityManager.merge(user);
            return "Registration successful!";
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public User checkUser(User user) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        User userData = entityManager.createQuery
                ("from User u where u.login like :uLogin", User.class)
                .setParameter("uLogin", user.getLogin()).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        if (userData == null || !userData.getPassword().equals(user.getPassword())) {
            throw new IllegalStateException("Login or password are incorrect!");
        }
        return userData;
    }
}
