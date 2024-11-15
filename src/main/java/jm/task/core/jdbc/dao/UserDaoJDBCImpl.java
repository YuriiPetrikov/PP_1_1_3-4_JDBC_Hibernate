package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String querySQL = "CREATE TABLE IF NOT EXISTS users " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastname VARCHAR (50), " +
                " age TINYINT not NULL, " +
                " PRIMARY KEY (id))";

        try (Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(querySQL);
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }
    }

    @Override
    public void dropUsersTable() {
        String querySQL = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(querySQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String querySQL = "INSERT users(name, lastname, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatementt = connection.prepareStatement(querySQL);
            preparedStatementt.setString(1, name);
            preparedStatementt.setString(2, lastName);
            preparedStatementt.setByte(3, age);
            preparedStatementt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        String querySQL = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String querySQL = "SELECT * FROM users";

        try (Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        String querySQL = "DELETE FROM users";

        try (Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(querySQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
