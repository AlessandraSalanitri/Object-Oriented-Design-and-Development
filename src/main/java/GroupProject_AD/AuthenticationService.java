package GroupProject_AD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Diana & Alessandra
 */
import java.util.Scanner;
import java.util.List;

//The class AuthenticationService is declared as public.
public class AuthenticationService {

    //scanner: A static Scanner object to read user input from the console.
    private static final Scanner scanner = new Scanner(System.in);
    //userManager is expected to handle user-related operations.
    private UserManager userManager; // Declaring userManager class as a class level field

    //Constructor that takes a UserManager parameter and assigns it to the userManager field.
    public AuthenticationService(UserManager userManager) {
        this.userManager = userManager;
    }

    //SIGN UP INPUT
    public void signUp() { //(user input for username, password, and user type)
        System.out.println("Sign Up");

        // Get user input for username
        System.out.print("Enter your Username: ");
        String username = scanner.nextLine();

        // Get user input for password
        String password1;
        String password2;

        do {
            // Get user input for password twice for verification
            System.out.print("Enter your Password: ");
            password1 = scanner.nextLine();

            System.out.print("Enter your Password again for verification: ");
            password2 = scanner.nextLine();

            // Verify password
            if (!password1.equals(password2)) {
                System.out.println("Passwords do not match. Please try again.");
            }
        } while (!password1.equals(password2));

        // Get user input for user type
        int userType;

        do {
            System.out.print("Choose your user type (0 for Admin, 1 for Regular User): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter 0 for Admin or 1 for User.");
                scanner.next(); // Consume invalid input
            }

            userType = scanner.nextInt();
            // Consume the newline character, if any
            scanner.nextLine();

        } while (userType < 0 || userType > 1);

        // Create a new user and print a success message based on the user type
        UserDatabase.addUser(username, password1, userType);
        System.out.println("****************************************");
        System.out.println((userType == 0 ? "Admin" : "User") + " '" + username + "' Signed Up successfully!");
        System.out.println("****************************************");
        System.out.println("Now you can Sign in with your credentials.");
        System.out.println("****************************************");
        //this last line has been created to ensure the user log in after the sign up
    }

    // SIGN IN
    public boolean signIn(List<User> users) {
        boolean signInSuccessful = false;

        do {
            System.out.print("Enter your Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your Password: ");
            String password = scanner.nextLine();

            // Check if the entered credentials match any existing user.
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    System.out.println("**************************");
                    System.out.println("** Sign in successful! ***");
                    System.out.println("**************************");

                    if (user.getUserType() == 0) {
                        // Admin Type
                        AdminManager adminManager = new AdminManager(username, password, 0);
                        boolean exitAdminPage = adminManager.performAdminActions(users, true, scanner); // Perform admin actions
                        if (exitAdminPage) {
                            System.out.println("Returning to the main menu...");
                            return false; // Return false to indicate not to return to the main menu
                        }
                    } else {
                        // This codeandles the case where the sign-in is successful for a regular user.
                        userManager.setLoggedIn(true);
                        List<PointsOfInterest> userPointsOfInterest = getUserPointsOfInterest(user);
                        userManager.performUserActions(userPointsOfInterest, true);
                    }

                    signInSuccessful = true; // Sign-in successful
                    break; // Break out of the loop since sign-in was successful
                }
            }

            
            //The code is part of the signIn method and is responsible for handling the case when the entered username and password do not match any existing user. 
            if (!signInSuccessful) {
                System.out.println("Invalid username or password. Please try again.");
                System.out.println("_______________________________________________");
            }

        } while (!signInSuccessful);

        return signInSuccessful;
    }

    
    // This method get points of interest relevant to the user
    //Alessandra 
    private List<PointsOfInterest> getUserPointsOfInterest(User user) {
        return PointsOfInterestDatabase.getAllPointsOfInterest();
    }
}
