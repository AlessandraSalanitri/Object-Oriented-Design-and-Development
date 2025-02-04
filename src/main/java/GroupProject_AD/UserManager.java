/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupProject_AD;

/**
 *
 * @author Alessandra Salanitri 
 * USER MANAGER CLASS MANAGES USER INTERACTIONS AND
 * ACTIONS RELATED TO POINTS OF INTEREST. IT TRACKS THE CURRENT POINT OF
 * INTEREST CURRENTLY DISPLAYED THAT THE USER HAS SEARCHED. IT HAS BEEN
 * INTEGRATED FROM THE INDIVIDUAL TASK A ADDING THE LOG IN STATUS TRACKING
 * IN THIS CODE. IN FACT, THE USER IS ALLOWED TO PERFORM CERTAINT OPERATION SUCH AS
 * ADD A LIKE OR A COMMENT ONLY IF LOGGED IN.
 *
 */
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class UserManager {

    // INITIALIZATION
    private boolean isLoggedIn;
    private PointsOfInterest currentPointOfInterest;

    public UserManager() {
        this.isLoggedIn = false; 
        //this code is set to false and it tracks the login status of a user 
        //Thanks to this logic the user is allowed to search for a poi as a guest
        //but perform action as commenting and liking just when "isLoggedIn" is set to true.
        //Alessandra Salanitri
        
        this.currentPointOfInterest = null; 
        //this code store the details of the point of interest a user has searched
        // so whenever a user search for a poi, and he is logged in and would like to add a like
        // or a comment to that specific poi, the program reckognised the poi and update the 
        //action performed by the user in that poi
        // Alessandra Salanitri
    }

    //login status set and return
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void logout() {
        setLoggedIn(false);
    }

    // THIS METHOD DISPLAY THE USER MENU. FETCHES IN THE POINT OF INTEREST DATABASE CLASS, THE POINT OF INTEREST 
    // THAT THE USER IS LOOKING FOR, IT DISPLAYS IT THEN WITH ALL THE INFORMATION STORED IN THE ARRAY.
    // Alessandra Salanitri
    public void performUserActions(List<PointsOfInterest> pointsOfInterestList, boolean isLoggedIn) {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            boolean exit = false; //THIS EXIT VALUE WAS INTEGRATED BECAUSE THE APP WAS NOT EXITING SMOOTHLY FROM USER PAGE.

            do {
                System.out.println("");
                System.out.println("*************************");
                System.out.println("Welcome to User Page!");
                System.out.println("*************************");
                System.out.println("");
                System.out.println("1. Search for points of interest");
                //THIS CODE HAS AN IF CONDITION TO CHECK THE LOGIN STATUS OF A USER
                //THE CODE OUTSIDE THIS CONDITION ARE DISPLAYED IF THE USER IS NAVIGATING
                //THE PROGRAM AS A GUEST. THE CODE INSIDE THE CONDITION ARE DISPLAYED JUST AFTER LOGIN.
                if (isLoggedIn) {
                    System.out.println("2. Like a point of interest");
                    System.out.println("3. Add or Modify your comment on a point of interest");
                }
                System.out.println("4. Exit");
                System.out.println("");
                System.out.print("Please choose a numerical option: ");
                
                //THE WHILE LOOP, LOOPS THROUGH THE USER CHOICE UNTIL EXIT CONDITION IS FALSE
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); 
                    
                //THE TRY-CATCH BLOCK AVOID THE PROGRAM TO CRUSH IN CASE THE INPUT
                //FROM THE USER IS DIFFERENT FROM THE ONE THE PROGRAM EXPECT
                //SUCH AS RECEVING AN INT INSTEAD OF A STRING OR VICE VERSA.    
                }
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            searchForPointsOfInterest(pointsOfInterestList, isLoggedIn);
                            break;
                        case 2:
                            if (isLoggedIn) {
                                likePointOfInterest(pointsOfInterestList, currentPointOfInterest);
                            } else {
                                System.out.println("*************************");
                                System.out.println("Please log in to add your like!");
                            }
                            break;
                        case 3:
                            if (isLoggedIn) {
                                addOrModifyComment(pointsOfInterestList, currentPointOfInterest);
                            } else {
                                System.out.println("*************************");
                                System.out.println("Please log in to add or modify your comment!");
                            }
                            break;
                        case 4:
                            System.out.println("Exiting the program. Goodbye!");
                            System.out.println("*************************");
                            exit = true;
                            scanner.nextLine();
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid number.");
                    }
                    
                    //THIS CATCH EXCEPTION GUIDE THE PROGRAM IN WHAT TO DO IN CASE OF UNEXPECTED INPUT
                    //IN THIS CASE, THE "NOSUCHELEMENTEXCEPTION E" IS USED BECAUSE
                    //WHILE TESTING, THE APP CRUSHED FOR THIS.
                    //AFTER ADDING A TRY-CATCH BLOCK, IF THE PROGRAM RECEIVE UNEXPECTED INPUT, 
                    //IT DISPLAYS "INVALID INPUT. PLEASE ENTER A VALID NUMBER"
                } catch (NoSuchElementException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }
            } while (!exit); //THANKS TO THE EXIT CONDITION THE LOOP ITERATE UNTIL THE USER CHOSE TO EXIT THE PROGRAM
        }
    }

    //THIS METHOD RETRIVES FROM THE POINTS OF INTEREST LIST, THE POI SEARCHED BY THE USER
    public void searchForPointsOfInterest(List<PointsOfInterest> pointsOfInterestList, boolean isLoggedIn) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*************************");
        System.out.println("Enter a Location or a Type to search for a Point of Interest:");
        System.out.println("Location can be Italy, Romania, France, UK...");
        System.out.println("A Type or category can be Restaurant, Museum, Park, Historical Place...");
        System.out.print(": ");
        String searchTerm = scanner.nextLine();

        boolean found = false; //

       //This implementation iterates through the pointsOfInterestList and checks
        //if either the location or type matches the user's search term. If a match
        // is found, it prints the details of the point of interest; otherwise, it
        // informs the user that no match was found. 
        for (PointsOfInterest poi : pointsOfInterestList) {
            
            // BELOW LINE AVOID THE PROGRAM CRUSHES IN CASE THE USER TYPE IN LOWERCASE OR UPPERCASE
            if (poi.getLocation().equalsIgnoreCase(searchTerm) || poi.getType().equalsIgnoreCase(searchTerm)) {
                System.out.println("*************************");
                System.out.println("Are you looking for " + poi.getName() + "?");
                System.out.println("");
                System.out.println("*************************");

                System.out.println("Below all the details of : " + poi.getName());
                System.out.println("Number ID: " + poi.getId());
                System.out.println("Type: " + poi.getType());
                System.out.println("Location: " + poi.getLocation());
                System.out.println("Comments: " + poi.getComments());
                System.out.println("Likes: " + poi.getLikes());
                System.out.println("");
                System.out.println("*************************");
                System.out.println("WE WOULD LOVE TO KNOW WHAT DO YOU THINK.. ");
                System.out.println("Love this place? Add your like!");
                System.out.println("Been here? Let other visitors know your experience with a comment.");
                System.out.println("*************************");
                System.out.println("Choose from the menu below if you want to share your thoughts with us");
                System.out.println("..or you can simply choose to go back and search for other places :) ");
                System.out.println("*************************");
                System.out.println("1. Add your like");
                System.out.println("2. Add or modify your comment");
                System.out.println("3. Go back to the main menu");
                System.out.println("*************************");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                    case 2:
                         //THIS STATEMENT CHECK THE LOG IN STATUS AND ALLOW ONLY LOGGED IN USER TO ADD LIKES OR COMMENTS
                        currentPointOfInterest = poi; // CURRENT POINT OF INTEREST SEARCHED FROM THE USER
                        if (isLoggedIn) {
                            if (option == 1) {
                                likePointOfInterest(pointsOfInterestList, currentPointOfInterest);
                            } else {
                                addOrModifyComment(pointsOfInterestList, currentPointOfInterest);
                            }
                        } else {
                            System.out.println("*************************");
                            System.out.println("Sorry! You need to sign in to add your thoghts...");
                            System.out.println("");
                        }
                        break;
                    case 3:
                        return; // RETURN TO MAIN MENU IN MAIN CLASS
                    default:
                        System.out.println("Search for a point of interest.");
                        break;
                }
                found = true; // THE POINT OF INTEREST HAS BEEN FOUND SO THE FOR LOOP CAN STOP FETCH AND THE BOOLEAN VALUE IS SET TO TRUE
                break;
            }
        }

        // BELOW CODE DISPLAY USER FRIENDLY MESSAGE TO LET THE USER KNOW THAT THE POI HE IS LOOKING FOR IS NOT ON THE LIST
        // AS WELL AS AVOID THE PROGRAM TO CRUSH IN CASE THIS HAPPEN.
        if (!found) {
            System.out.println("*************************");
            System.out.println("Sorry, this point of interest is not currently on our list: " + searchTerm);
            System.out.println("..We are working on adding many places..");
            System.out.println("I will take you back to the menu again, so you can chose another one");
            System.out.println("");

        }
    }

    // LIKE A POINT OF INTEREST METHOD UPDATE THE COUNT OF LIKE 
    private void likePointOfInterest(List<PointsOfInterest> pointsOfInterestList, PointsOfInterest currentPointOfInterest) {
        if (currentPointOfInterest != null) { // this code check if a point of interest is currently displayed
            currentPointOfInterest.setLikes(currentPointOfInterest.getLikes() + 1);
            System.out.println("*************************");
            System.out.println("Thank you!");
            System.out.println("Your like to " + currentPointOfInterest.getName() + " has been added!");
            System.out.println("*************************");
        } else {
            System.out.println("*************************");
            System.out.println("We love your feedback! But not really sure where you'd like to add it");
            System.out.println("Please, search for a point of interest ");

        }
    }

    // ADD OR MODIFY A COMMENT METHOD() 
    private void addOrModifyComment(List<PointsOfInterest> pointsOfInterestList, PointsOfInterest currentPointOfInterest) {
        if (currentPointOfInterest != null) { // this condition check if a point of interest is currently displayed

            Scanner scanner = new Scanner(System.in);

            System.out.println("*************************");
            System.out.println("1. Add a new comment");
            System.out.println("2. Modify your previous comment");
            System.out.println("3. View all your comments");
            System.out.println("4. Exit");
            System.out.println("*************************");

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addNewComment();
                    break;
                case 2:
                    modifyPreviousComment();
                    break;
                case 3:
                    viewAllComments();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Please enter a numeric value");
                    break;
            }
        } else {
            System.out.println("We love your feedback! But not really sure where you'd like to add it");
            System.out.println("Please, search for a point of interest ");
        }
    }

    //ADD NEW COMMENT METHOD()
    private void addNewComment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*************************");
        System.out.print("Write your comment: ");
        String newComment = scanner.nextLine();  //THIS ENSURE EVERY COMMENT ARE STORED IN A DIFFERENT LINE
        currentPointOfInterest.getComments().add(newComment); // ADD THE NEW COMMENT TO THE ARRAY
        System.out.println("Your comment is now visible in " + currentPointOfInterest.getName());
        System.out.println("*************************");
    }

    // MODIFY PREVIOUS COMMENT METHOD() - AND UPDATE THE LIST
    // INFORM WITH USER FRIENDLY MESSAGES THE USER OF SUCCESSFULL COMMENT MODIFIED
    private void modifyPreviousComment() {
        Scanner scanner = new Scanner(System.in);
        List<String> comments = currentPointOfInterest.getComments();

         // CHECK IF THE CURRENT USER HAS COMMENT STORED
        //IF IT DOES, DISPLAY THE CURRENT COMMENT AND PROMPT FOR THE MODIFICATION
        //IF IT DOES NOT, INFORM THE USER.
        if (comments == null || comments.isEmpty()) {
            System.out.println("*************************");
            System.out.println("Sorry! Looks like you've never added a comment.");
            System.out.println("*************************");
        } else {
            System.out.println("*************************");
            System.out.println("Your current comment(s):");
            for (String comment : comments) {
                System.out.println(comment);
            }
            System.out.println("Modify your comment: ");
            System.out.println("*************************");
            String modifiedComment = scanner.nextLine(); 
            comments.add(modifiedComment); // Add the modified comment to the list
            currentPointOfInterest.setComments(comments); // Update the comments list
            System.out.println("*************************");
            System.out.println("Your comment for " + currentPointOfInterest.getName() + " has been modified!");
            System.out.println("*************************");
        }
    }

    // VIEW ALL COMMENTS METHOD() DISPLAYS ALL THE COMMENTS FOR A POINT OF INTEREST
    // THESE COMMENT ARE STORED IN THE ARRAY IN THE POINT OF INTEREST DATABASE CLASS.
    private void viewAllComments() {
        List<String> comments = currentPointOfInterest.getComments();
        if (comments == null || comments.isEmpty()) {
            System.out.println("There are no comments for " + currentPointOfInterest.getName());
        } else {
            System.out.println("All comments for the point of interest: " + currentPointOfInterest.getName());
            System.out.println("");
            for (String comment : comments) {
                System.out.println(comment); // Print each comment on a separate line
            }
            System.out.println("*************************");
        }
    }
}
