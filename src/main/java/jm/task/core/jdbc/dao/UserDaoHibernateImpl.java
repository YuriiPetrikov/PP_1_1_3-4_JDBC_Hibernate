package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
           session.getTransaction().begin();

            String querySQL = "CREATE TABLE IF NOT EXISTS User" +
                    "(id BIGINT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " lastname VARCHAR (50), " +
                    " age TINYINT not NULL, " +
                    " PRIMARY KEY (id))";

            session.createSQLQuery(querySQL).executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            User user = new User(name, lastName, age);
            session.persist(user);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createQuery("DELETE FROM User WHERE id = " + id).executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
       try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
       }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
}
