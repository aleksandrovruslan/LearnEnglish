package ru.aleksandrov.service;

import ru.aleksandrov.models.CollectionType;
import ru.aleksandrov.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.*;

public class CollectionTypeService {
    private static List<CollectionType> types = null;
    //TODO make initialisation default type
    private static int defaultTypeId = 1;

    public static Set<CollectionType> getTypes() {
        if (types == null) {
            init();
        }
        Set<CollectionType> sortedTypes = new TreeSet<>(new Comparator<CollectionType>() {
            @Override
            public int compare(CollectionType o1, CollectionType o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        sortedTypes.addAll(types);
        return sortedTypes;
    }

    public static void init() {
        EntityManager manager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        manager.getTransaction().begin();
        types = manager.createQuery("from CollectionType c"
                , CollectionType.class).getResultList();
        manager.getTransaction().commit();
        manager.close();
    }

    public static int getDefaultTypeId() {
        return defaultTypeId;
    }
}
