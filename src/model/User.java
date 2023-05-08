package model;

import java.time.LocalDate;

public class User {
    private int ID;
    private String name;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private boolean isStudent;
    private double balance;
    //private static int usersAdded = 0;

    /*
    Purpose: create a new instance of the model.User class

    params:
    name - String name of the user
    email - String email of the user
    address - String address of the user
    dateOfBirth - LocalDate that represents the user birthday
    isStudent - Boolean that represents if the user is a student or faculty
     */
    public User(String name, String email, String address, LocalDate dateOfBirth, boolean isStudent) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.isStudent = isStudent;

        //Set the initial balance to 0
        this.balance = 0.0;

        //Not needed because the id is auto generated
        //this.ID = usersAdded;
        //usersAdded++;
    }
    public User(int id, String name, String email, String address, LocalDate dateOfBirth, boolean isStudent, double balance){
        this.name = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.isStudent = isStudent;
        this.ID = id;
        this.balance = balance;
    }


    /*
    Purpose: Used to determine if two instances of the user class are equal. They are considered equal if the id numbers match.
    Params:
    o - Base object so that any object can be passed

    return: Boolean, returns true if ids match, returns false if ids don't match or if object given isn't a user
     */
    @Override
    public boolean equals(Object o) {
        //check if object given is a model.User object
        if(o instanceof User){
            //Cast to user object to get the id
            User u = (User) o;
            return u.getID() == this.getID();
        }
        else
            return false;

    }


    /*
    Purpose: used to get the users ID
    return: integer that represents the id number
     */
    public int getID() {
        return ID;
    }

    /*
    Purpose: used to get the users name
    return: String that represents the users name
     */
    public String getName() {
        return name;
    }

    /*
    Purpose: used to get the users email
    return: String that represents the users email
     */
    public String getEmail() {
        return email;
    }

    /*
    Purpose: used to get the users address
    return: String that represents the users address
     */
    public String getAddress() {
        return address;
    }

    /*
    Purpose: used to get the users date of birth
    return: LocalDate that represents the users date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /*
    Purpose: used to get whether the user is a student or faculty
    return: Boolean that represents whether the user is a student or faculty
     */
    public boolean getStudent() {
        return isStudent;
    }

    /*
    Purpose: used to get the users balance
    return: Double that represents the users balance
     */
    public double getBalance() {
        return balance;
    }



    /*
    Purpose: used to set the users name
    Params:
    name - String representing the users name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    Purpose: used to set the users email
    Params:
    email - String representing the users email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /*
    Purpose: used to set the users address
    Params:
    address - String representing the users address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /*
    Purpose: used to set the users date of birth
    Params:
    dateOfBirth - LocalDate representing the users date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /*
    Purpose: used to set whether the user is a student or faculty
    Params:
    student - Boolean representing whether the user is a student or faculty
     */
    public void setStudent(boolean student) {
        isStudent = student;
    }

    /*
    Purpose: used to set the users balance
    Params:
    balance - Double representing the users balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /*
    Purpose: Used to print the info of a user
    return: returns the id and name of the user
     */
    @Override
    public String toString() {
        return "ID: "+this.ID+" Name: "+this.name;
    }
}
