package ru.aleksandrov.DAO;

import ru.aleksandrov.models.CollectionType;
import ru.aleksandrov.util.JPAUtil;

import javax.persistence.EntityManager;

public class CollectionTypeDAO {
    private static String defName = "Default";
    private static CollectionType defType;

    public static CollectionType getDefaultType() {
        if (defType == null) {
            initType(defName);
        }
        return defType;
    }

    private static CollectionType initType(String name) {
        CollectionType type = new CollectionType();
        type.setName(name);
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(type);
        entityManager.getTransaction().commit();
        return type;
    }
}
