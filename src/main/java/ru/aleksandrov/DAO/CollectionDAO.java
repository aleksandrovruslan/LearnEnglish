package ru.aleksandrov.DAO;

import org.hibernate.Hibernate;
import ru.aleksandrov.models.*;
import ru.aleksandrov.models.Collection;
import ru.aleksandrov.util.JPAUtil;
import ru.aleksandrov.util.ValidatorUtil;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import java.util.*;

public class CollectionDAO {
    public List<Collection> initUserCollections(User user) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        Set<Collection> collections = entityManager
                .find(User.class, user.getId()).getCollections();
        Hibernate.initialize(collections);
        entityManager.getTransaction().commit();
        entityManager.close();
        List<Collection> collections1 = new ArrayList<>(collections);
        collections1.sort(new Comparator<Collection>() {
            @Override
            public int compare(Collection o1, Collection o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return collections1;
    }

    public void addCollection(User user, int collectionTypeId, String collectionName, Map<String, Object> variables) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        User userData = entityManager.find(User.class, user.getId());
        CollectionType type = entityManager
                .find(CollectionType.class, collectionTypeId);
        Collection collection = new Collection();
        collection.setName(collectionName);
        collection.setType(type);
        ValidatorUtil<Collection> validator = new ValidatorUtil<>();
        if (validator.verifyEntity(collection)) {
            userData.getCollections().add(collection);
        } else {
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<Collection> v:validator.getErrors()) {
                errors.add(v.getMessage());
            }
            variables.put("errors", errors);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Collection getCollection(long id) {
        EntityManager manager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        manager.getTransaction().begin();
        Collection col = manager.find(Collection.class, id);
        Hibernate.initialize(col);
        for (Quiz quiz:col.getQuizzes()) {
            Hibernate.initialize(quiz);
        }
        manager.getTransaction().commit();
        manager.close();
        return col;
    }
}
