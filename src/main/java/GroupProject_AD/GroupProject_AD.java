/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package GroupProject_AD;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alessandra & Diana 
 * THIS IS THE MAIN PAGE OF A SIMPLE CONSOLE-MODE OF
 * HIT TASTIC APP HANDLES THE AUTHENTICATION SERVICE WITH THE MAIN MENU
 * INTEGRATING BOTH TASKS A & B ,AND CREATING A FLOWLESS CONSOLE APPLICATION
 */
public class GroupProject_AD {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        UserManager userManager = new UserManager(); // Instantiate UserManager
        AuthenticationService authService = new AuthenticationService(userManager);
        boolean isLoggedIn = false; //THIS CODE IS BEEN INTEGRATED TO ALLOW THE USER TO PERFORM SOME ACTIONS ONLY 
        // IF IS LOGGED IN. 
        //IT TRACKS THE LOG IN STATUS OF A USER THROUGHOUT THE APP.

        try (scanner) {
            int choice;
            do {
                System.out.println("----------------------------------");
                System.out.println("Welcome to Hit Tastic!");
                System.out.println("1. Search for Point of Interest");
                System.out.println("2. Sign Up");
                System.out.println("3. Sign In");
                System.out.println("4. Exit");
                System.out.println("----------------------------------");
                System.out.print("Please choose an option (1-4): ");

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    //THIS CODE HAS INTEGRATED BOTH TASK A & B ALLOWING THE USER TO SEARCH FOR A POINT
                    //OF INTEREST.
                    // THE PERFORM USER ACTION METHOD IS IN THE USER MANAGER CLASS
                    // AND ALLOWS TO PERFORM DIFFERENT ACTION IF THE USER NAVIGATE THE PROGRAM
                    //AS A GUEST OR LOGGED IN.
                    switch (choice) {
                        case 1:
                            searchForPointsOfInterest(isLoggedIn);
                            break;
                        case 2:
                            signUp(authService);
                            break;
                        case 3:
                            isLoggedIn = signIn(authService, userManager);
                            if (isLoggedIn) {
                                performUserActions(isLoggedIn, userManager);
                            }
                            break;
                        case 4:
                            System.out.println("Exiting the program. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); 
                    choice = 0; // RESET CHOICE TO AVOID AN INFINITE LOOP
                }
            } while (choice != 4); //THIS STATEMENT CHECK THE USER CHOICE, UNTIL IS NOT 4, THE LOOP
                                    //KEEP LOOPING IN THE MENU DISPLAYED AND IT STOPS WHEN USER CHOICE IS 4: EXIT.
        }
    }

// this code call the method which is implemented in UserManager Class 
// retrive the point of interest selected by the user
// Alessandra Salanitri
    private static void searchForPointsOfInterest(boolean isLoggedIn) {
        List<PointsOfInterest> pointsOfInterestList = PointsOfInterestDatabase.getAllPointsOfInterest();
        UserManager userManager = new UserManager();
        userManager.searchForPointsOfInterest(pointsOfInterestList, isLoggedIn);
    }

    private static void signUp(AuthenticationService authService) {
        authService.signUp();
    }

    private static boolean signIn(AuthenticationService authService, UserManager userManager) {
        List<User> users = UserDatabase.getUsers(); // Assuming you have a UserDatabase class
        return authService.signIn(users); // Pass the users list to the signIn method
    }

    // This code call the method Perform user actions from the UserManager Class
    // Track the log in status of a User and display the menu from which the User
    // Can decide what action perform.
    // Alessandra Salanitri
    private static void performUserActions(boolean isLoggedIn, UserManager userManager) {
        List<PointsOfInterest> pointsOfInterestList = PointsOfInterestDatabase.getAllPointsOfInterest();
        userManager.setLoggedIn(isLoggedIn);
        userManager.performUserActions(pointsOfInterestList, true);
    }
}
