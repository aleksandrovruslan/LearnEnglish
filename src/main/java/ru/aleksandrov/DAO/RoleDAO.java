package ru.aleksandrov.DAO;

import ru.aleksandrov.models.Role;
import ru.aleksandrov.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RoleDAO {
    private static String administrator = "Administrator";
    private static String moderator = "Moderator";
    private static String user = "User";
    private static Role adminRole = null;
    private static Role moderatorRole = null;
    private static Role userRole = null;

    static {
        Role adminR = new Role();
        adminR.setName(administrator);
        Role moderR = new Role();
        moderR.setName(moderator);
        Role userR = new Role();
        userR.setName(user);
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(adminR);
        entityManager.merge(moderR);
        entityManager.merge(userR);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Role getAdminRole() {
        if (adminRole == null) {
            adminRole = getRole(administrator);
        }
        return adminRole;
    }

    public static Role getModeratorRole() {
        if (moderatorRole == null) {
            moderatorRole = getRole(moderator);
        }
        return moderatorRole;
    }

    public static Role getUserRole() {
        if (userRole == null) {
            userRole = getRole(user);
        }
        return userRole;
    }

    public void addRole(Role role) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(role);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void addRole(String role) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        Role tempRole = new Role();
        tempRole.setName(role);
        entityManager.merge(tempRole);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static Role getRole(String role) {
        //TODO if there is no role then create
        EntityManager entityManager = JPAUtil.getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        Role roleQuery = entityManager
                .createQuery("from Role r where r.name like :roleName"
                        , Role.class).setParameter("roleName", role).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return roleQuery;
    }
}
