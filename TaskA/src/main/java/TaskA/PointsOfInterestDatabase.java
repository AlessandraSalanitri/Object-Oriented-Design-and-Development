/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskA;

/**
 *
 * @author Alessandra Salanitri
 * THIS CLASS, ACT AS A DATABASE TO STORE AND RETRIVE DATA, 
 * IT HAS A STATIC INITIALIZER BLOCK WHICH HOLDS ALL THE HARDCODED POINT OF INTERESTS
 * AND AN ARRAY LIST WITH ALL THE COMMENT LINKED TO THE POINT OF INTEREST OF INTEREST.
 */
import TaskA.PointsOfInterest;
import java.util.ArrayList;
import java.util.List;

public class PointsOfInterestDatabase {

    /**
     * Point of interest is a static list that holds all the data
     *
     */
    public static final List<PointsOfInterest> pointsOfInterestList = new ArrayList<>();

    // data hardcoded
    static {

        List<String> colosseumComments = new ArrayList<>();
        colosseumComments.add("millions of visitors each year");

        List<String> moulinRougeComments = new ArrayList<>();
        moulinRougeComments.add("a living entertainment venue that continues to host performances and captivate audiences with its unique and vibrant shows.");

        List<String> caruCuBereComments = new ArrayList<>();
        caruCuBereComments.add("traditional Romanian cuisine, historic ambiance, and vibrant atmosphere");

        List<String> britishMuseumComments = new ArrayList<>();
        britishMuseumComments.add("World-famous museum with a vast collection of art and artifacts");

        List<String> hydeParkComments = new ArrayList<>();
        hydeParkComments.add("One of London's largest and most famous parks, perfect for a leisurely stroll");

        List<String> westEndTheatreComments = new ArrayList<>();
        westEndTheatreComments.add("Renowned theater district featuring top-notch performances");

        pointsOfInterestList.add(new PointsOfInterest(1, "Historical Place", "Italy", "Colosseum", colosseumComments, 30));
        pointsOfInterestList.add(new PointsOfInterest(2, "Cabaret", "France", "Moulin Rouge", moulinRougeComments, 20));
        pointsOfInterestList.add(new PointsOfInterest(3, "Restaurant", "Romania", "Caru cu Bere", caruCuBereComments, 10));
        pointsOfInterestList.add(new PointsOfInterest(4, "Museum", "UK", "British Museum", britishMuseumComments, 15));
        pointsOfInterestList.add(new PointsOfInterest(5, "Park", "UK", "Hyde Park", hydeParkComments, 20));
        pointsOfInterestList.add(new PointsOfInterest(6, "Theater", "UK", "West End Theatre District", westEndTheatreComments, 18));
    }

    
    // Method to returns the list containing all points of interest stored.
    
    public static List<PointsOfInterest> getAllPointsOfInterest() {
        return pointsOfInterestList;
    }

}
