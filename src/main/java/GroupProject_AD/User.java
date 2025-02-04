/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupProject_AD;

/**
 *
 * @author Diana
 * 
 */
public class User {
    private String username;
    private String password;
    private int userType;

    // CONSTRUCTORS TO INITIALIZE A USER OBJECT

    public User(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    
    //GETTERS AND SETTERS METHODS FOR RETRIEVING AND UPADTING USER
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
       
}
