package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 25);
        System.out.println("User с именем Иван добалвен в базу данных");
        userService.saveUser("Петр", "Петров", (byte) 28);
        System.out.println("User с именем Петр добалвен в базу данных");
        userService.saveUser("Сергей", "Сергеев", (byte) 56);
        System.out.println("User с именем Сергей добалвен в базу данных");
        userService.saveUser("Сидор", "Сидоров", (byte) 33);
        System.out.println("User с именем Сидор добавлен в базу данных");
        System.out.println();

        List<User> usersList = userService.getAllUsers();
        for(User user : usersList){
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
