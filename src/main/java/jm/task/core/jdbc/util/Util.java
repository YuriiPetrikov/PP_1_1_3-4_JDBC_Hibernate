package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_HOSTNAME = "localhost";
    private static final String DB_NAME = "myfirstdatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOSTNAME + "/" + DB_NAME;
    private static Connection connection = null;

    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (SQLException e) {
            e.fillInStackTrace();
        }

        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try  {
                Configuration configuration = new Configuration();
                configuration.setProperties(getProperties());
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

    private static Properties getProperties() {
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.URL, DB_URL);
        settings.put(Environment.USER, DB_USERNAME);
        settings.put(Environment.PASS, DB_PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

        settings.put(Environment.HBM2DDL_AUTO, "create");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.SHOW_SQL, "true");
        return settings;
    }
}
