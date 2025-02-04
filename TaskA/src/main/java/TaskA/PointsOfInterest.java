/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskA;

import java.util.List;

/**
 *
 * @author Alessandra Salanitri
 * THIS CLASS, REPRESENT THE STRUCTURE OF A POINT OF INTEREST AND PROVIDES METHODS TO ACCESS AND MODIFY ITS ATTRIBUTE
 * ATTRIBUTE: ID, NAME, TYPE, LOCATION, COMMENTS AND LIKES.
 * THE CONTRACTOR, THAT INITIALIZES THE ATTRIBUTE FOR EACH POINT OF INTEREST, 
 * GETTER AND SETTER METHODS.
 */
public class PointsOfInterest {
    private int id;
    private String type;
    private String location;
    private String name;
    private List<String> comments; // THE COMMENTS ARE STORED AS LIST BECAUSE MULTIPLE.
    private int likes;

    
    // Constructor
    // Initialize the newly created object: Points of interest
    
    public PointsOfInterest(int id, String type, String location, String name, List<String> comments, int likes) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.name = name;
        this.comments = comments;
        this.likes = likes;
    }

    // GETTER AND SETTER METHOD
    //ALLOW OTHER CLASSES TO RETRIEVE OR MODIFY THE VALUES FROM THE PRIVATE FIELDS.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComments() { // THE COMMENTS ARE STORED AS LIST BECAUSE MULTIPLE.
        return comments;
    }

    public void setComments(List<String> comments) { // THE COMMENTS ARE STORED AS LIST BECAUSE MULTIPLE.
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
