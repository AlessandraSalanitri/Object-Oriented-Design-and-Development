/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupProject_AD;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diana
 */
public class UserDatabase {

    private static List<User> users;

    // Make the getUsers method static
    public static List<User> getUsers() {
        if (users == null) {
            // Initialize the users ArrayList only if it's null
            users = new ArrayList<>();
            users.add(new User("admin", "admin", 0));
            users.add(new User("user", "user", 1));
            users.add(new User("alessandra", "user", 1));
            users.add(new User("diana", "admin", 0));
        }
        return users;
    }

//The addUser method is responsible for adding a new user to some form of user storage.
    public static void addUser(String username, String password, int userType) {
        User newUser = new User(username, password, userType);
        getUsers().add(newUser);
    }
}