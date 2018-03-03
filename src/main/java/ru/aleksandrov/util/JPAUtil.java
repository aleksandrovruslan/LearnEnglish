package ru.aleksandrov.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory = null;

    private static void setUp() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("ru.aleksandrov.models");
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            setUp();
        }
        return entityManagerFactory;
    }

    public static void close(){
        entityManagerFactory.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        JPAUtil.close();
    }
}
