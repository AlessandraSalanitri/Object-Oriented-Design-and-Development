/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupProject_AD;

import java.util.List;

/**
 *
 * @author Alessandra Salanitri
 * THIS CLASS, REPRESENT THE STRUCTURE OF A POINT OF INTEREST AND PROVIDES METHODS TO ACCESS AND MODIFY ITS ATTRIBUTE
 * this class has been integrated fully without modifications from the individual code task A.
 */



public class PointsOfInterest {
    
    //ATTRIBUTES:

    private int id;
    private String type;
    private String location;
    private String name;
    private List<String> comments; //the comment are stored in a list in PoiDatabase class.
                                   // it is a list because multiple comments are stored.
    private int likes;

    
    //THE CONTRACTOR INITIALIZES THE ATTRIBUTE FOR EACH POINT OF INTEREST
    
    public PointsOfInterest(int id, String type, String location, String name, List<String> comments, int likes) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.name = name;
        this.comments = comments;
        this.likes = likes;
    }

    // GETTER AND SETTER METHODS
    // ALLOW OTHER CLASSES TO RETRIEVE OR MODIFY THE VALUES FROM THE PRIVATE FIELDS AND UPDATE THE ATTRIBUTES
    
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

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) { //the comment are stored in a list in PoiDatabase class.
                                                     // it is a list because multiple comments are stored.
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
