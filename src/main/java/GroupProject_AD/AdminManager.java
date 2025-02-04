/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupProject_AD;

/**
 *
 * @author Diana
 */
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


//The class is named AdminManager and is part of the GroupProject_AD package.
//It extends the User class, suggesting that AdminManager inherits properties and methods from the User class.
public class AdminManager extends User {

    
    
    //The class has a constructor that takes username, password, and userType 
    //as parameters and calls the constructor of the superclass (User) using super().
    public AdminManager(String username, String password, int userType) {
        super(username, password, userType);
    }

    
    
    // Method to display a menu for admin actions
    //This method is used to perform various admin actions.
    //It takes a list of users, a boolean flag indicating whether it's an admin page, and a Scanner object for user input.
    //It displays a menu for admin actions and processes the user's choice until the user decides to sign out.
    public boolean performAdminActions(List<User> users, boolean isAdminPage, Scanner scanner) {
        int choice;
        //It validates user input to ensure that only valid integers are accepted as choices.
        boolean exitAdminPage = false;

        // Menu loop for admin actions uses a loop to repeatedly display the admin menu until the admin chooses to sign out (option 4).
        while (!exitAdminPage) {
            System.out.println("Hi '" + getUsername() + "', welcome to Admin Page!");
            System.out.println("----------------------------------");
            System.out.println("1. View all users");
            System.out.println("2. Change the details of a current user");
            System.out.println("3. Delete a user");
            System.out.println("4. Sign Out");
            System.out.println("----------------------------------");
            System.out.print("Please choose an option (1-4): ");

            // The loop validates user input to ensure that only valid integers are accepted as choices.
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear invalid input
            }

            // Get user choice
            try {
                choice = scanner.nextInt();

                scanner.nextLine(); // Consume the newline character

                // Exit condition check
                if (choice == 4) {
                    System.out.println("Exiting the Admin Page....");
                    exitAdminPage = true; // Exit the loop
                    break;

                } else {

                    // Switch statement to handle user choice
                    switch (choice) {
                        case 1:
                            viewAllUsers(users);
                            break;
                        case 2:
                            changeUser(users, scanner);
                            break;
                        case 3:
                            deleteUser(users, scanner);
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return exitAdminPage;
    }

    // This method is responsible for displaying details of all users in the system.
    public void viewAllUsers(List<User> users) {
        System.out.println("List of all users:");

        // Iterate through the list of users and print their details
        for (User user : users) {
            String userTypeString = (user.getUserType() == 0) ? "Admin" : "Regular User";
            System.out.println("| USERNAME: " + user.getUsername() + ",| PASSWORD: " + user.getPassword() + ",| TYPE: " + userTypeString);
        }
    }


    //This method allows the admin to change details (username, password, userType) of a specific user.
    private void changeUser(List<User> users, Scanner scanner) {
        System.out.print("Enter the username of the user to change details: ");

        // Get the username to change
        String usernameToChange = scanner.nextLine();

        // Find the user with the specified username
        User userToChange = null;
        for (User user : users) {
            if (user.getUsername().equals(usernameToChange)) {
                userToChange = user;
                break;
            }
        }

        // Check if the user was found
        if (userToChange == null) {
            System.out.println("User not found. Unable to change details.");
            return;
        }

        // Menu loop for changing user details
        while (true) {
            // Display current user details
            System.out.println("____________________________________________________");
            System.out.println("Current details for user '" + userToChange.getUsername() + "':");
            System.out.println("1. Username: " + userToChange.getUsername());
            System.out.println("2. Password: " + userToChange.getPassword());
            System.out.println("3. UserType: " + userToChange.getUserType());
            System.out.println("4. Back");
            System.out.println("____________________________________________________");

            // Prompt for the field to change
            System.out.print("Enter the number of the field to change (1-3): ");
            int fieldChoice;
            while (true) {
                try {
                    fieldChoice = Integer.parseInt(scanner.nextLine());
                    if (fieldChoice >= 1 && fieldChoice <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            }

            // Check if the user chose to go back
            if (fieldChoice == 4) {
                System.out.println("Going back to Admin Page....");
                return;
            }

            // Prompt for the new value with context
            switch (fieldChoice) {
                case 1:
                    System.out.print("Enter the new Username: ");
                    String newUsername = scanner.nextLine();
                    String oldUsername = userToChange.getUsername();
                    userToChange.setUsername(newUsername);
                    System.out.println("___________________________________________________________________________________");
                    System.out.println("*** Username '" + oldUsername + "' has been successfully changed to '" + newUsername + "'. ***");
                    System.out.println("___________________________________________________________________________________");
                    break;
                case 2:
                    System.out.print("Enter the new Password: ");
                    String newPassword = scanner.nextLine();
                    String oldPassword = userToChange.getPassword();
                    userToChange.setPassword(newPassword);
                    System.out.println("___________________________________________________________________________________");
                    System.out.println("*** Password '" + oldPassword + "' has been successfully changed to '" + newPassword + "'. ***");
                    System.out.println("___________________________________________________________________________________");
                    break;
                case 3:
                    System.out.print("Enter the new UserType (1 for Regular User, 0 for Admin): ");
                    int newUserType;
                    while (true) {
                        try {
                            newUserType = Integer.parseInt(scanner.nextLine());

                            if (newUserType >= 1 && newUserType <= 2) {
                                break;
                            } else {
                                System.out.println("Invalid choice! Please select 1 for Regular User and 0 for Admin.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number between 1 and 2.");
                        }
                    }

                    int oldUserType = userToChange.getUserType();
                    // Set the new user type for the user
                    userToChange.setUserType(newUserType);

                    String userTypeString = (oldUserType == 1) ? "Regular User" : "Admin";
                    String newUserTypeString = (newUserType == 1) ? "Regular User" : "Admin";
                    System.out.println("___________________________________________________________________________________");
                    System.out.println("*** User type '" + userTypeString + "' has been successfully changed to '" + newUserTypeString + "'. ***");
                    System.out.println("___________________________________________________________________________________");
                    break;
                default:
                    System.out.println("Invalid field choice. No changes made.");
                    break;
            }
        }
    }

    
    // The following method "deleteUser" enables the admin to delete a user from the system.
    private boolean deleteUser(List<User> users, Scanner scanner) {
        System.out.print("Enter the username of the user to delete: ");
        String usernameToDelete = scanner.nextLine();

        // Find the user with the specified username
        User userToDelete = null;
        for (User user : users) {
            if (user.getUsername().equals(usernameToDelete)) {
                userToDelete = user;
                break;
            }
        }

        // Check if the user was found
        if (userToDelete == null) {
            System.out.println("User not found. Unable to delete.");
            return false; // Deletion unsuccessful
        }

        // Get the username before removing the user
        String deletedUsername = userToDelete.getUsername();

        // Remove the user from the list
        users.remove(userToDelete);

        // Display deletion success message with the username
        System.out.println("*********************************");
        System.out.println("** User '" + deletedUsername + "' has been successfully Deleted! **");
        System.out.println("*********************************");
        return true; // Deletion successful
    }
}
