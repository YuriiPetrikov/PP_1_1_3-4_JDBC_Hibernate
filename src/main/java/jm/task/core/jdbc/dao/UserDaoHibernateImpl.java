package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            String querySQL = "CREATE TABLE IF NOT EXISTS User" +
                    "(id BIGINT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " lastname VARCHAR (50), " +
                    " age TINYINT not NULL, " +
                    " PRIMARY KEY (id))";

            session.createSQLQuery(querySQL).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.fillInStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.fillInStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            User user = new User(name, lastName, age);
            session.persist(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.fillInStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.createQuery("DELETE FROM User WHERE id = " + id).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.fillInStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            users = session.createQuery("FROM User", User.class).list();
            session.close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.fillInStackTrace();
        }
    }
}
