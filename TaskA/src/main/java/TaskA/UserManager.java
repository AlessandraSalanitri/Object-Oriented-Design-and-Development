/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskA;

import TaskA.PointsOfInterest;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alessandra Salanitri USER MANAGER CLASS MANAGES USER INTERACTIONS AND
 * ACTIONS RELATED TO POINTS OF INTEREST. IT TRACKS THE CURRENT POINT OF
 * INTEREST CURRENTLY DISPLAYED THAT THE USER HAS SEARCHED.
 *
 */
public class UserManager {

    private static PointsOfInterest currentPointOfInterest;

    // THIS METHOD FETCH IN THE POINT OF INTEREST DATABASE CLASS, THE POINT OF INTEREST 
    // THAT THE USER IS LOOKING FOR, IT DISPLAYS IT THEN WITH ALL THE INFORMATION STORED IN THE ARRAY.
    public void searchForPointsOfInterest(List<PointsOfInterest> pointsOfInterestList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*************************");
        System.out.println("Enter a Location or a Type to search for a Point of Interest:");
        System.out.println("Location can be Italy, Romania, France, UK...");
        System.out.println("A Type or category can be Restaurant, Museum, Park, Historical Place...");
        System.out.print(": ");
        String searchTerm = scanner.nextLine();

        boolean found = false; // IT SETS AS FALSE UNTIL THE POI IS NOT FOUND IN THE DATABASE.

        for (PointsOfInterest poi : pointsOfInterestList) {

            // BELOW LINE OF CODE AVOID THE PROGRAM TO CRUSH IN CASE THE USER TYPE IN LOWERCASE OR UPPERCASE
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
                        currentPointOfInterest = poi; // CURRENT POINT OF INTEREST SEARCHED FROM THE USER 
                        if (option == 1) {
                            likePointOfInterest();
                        } else {
                            addOrModifyComment();
                        }
                        break;
                    case 3:
                        return; // RETURN TO MAIN MENU IN TASK B CLASS
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
    void likePointOfInterest() {
        if (currentPointOfInterest != null) {
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
    void addOrModifyComment() {
        if (currentPointOfInterest != null) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
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
                        return;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                        break;
                }
            }
        }
    }

    //ADD NEW COMMENT METHOD()
    private void addNewComment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*************************");
        System.out.print("Write your comment: ");
        String newComment = scanner.nextLine();
        currentPointOfInterest.getComments().add(newComment); // ADD THE NEW COMMENT IN THE LIST
        System.out.println("Your comment is now visible in " + currentPointOfInterest.getName());
        System.out.println("*************************");
    }

    // MODIFY PREVIOUS COMMENT METHOD() - AND UPDATE THE LIST
    // INFORM WITH USER FRIENDLY MESSAGES THE USER OF SUCCESSFULL COMMENT MODIFIED.
    private void modifyPreviousComment() {
        Scanner scanner = new Scanner(System.in);
        List<String> comments = currentPointOfInterest.getComments();

        // CHECK IF THE CURRENT USER HAS COMMENT STORED
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
            comments.add(modifiedComment); // ADD THE MODIFIED COMMENT TO THE LIST
            currentPointOfInterest.setComments(comments); // UPDATE THE LIST
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
                System.out.println(comment); // THIS CODE PRINTS EACH COMMENT ON A DIFFERENT LINE.
            }
            System.out.println("*************************");
        }
    }
}
