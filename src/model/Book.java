package model;

public class Book {
    private int ID;
    private String name;
    private String author;
    private String publisher;
    private String genre;
    private String ISBN;
    private long year;
    //private static int booksAdded = 0;

    /*
    Purpose: create a new instance of the model.Book class

    params:
    name - String name of the book
    author - String author of the book
    publisher - String publisher of the book
    genre - String genre of the book
    isbn - String isbn of the book
    year - long representing the year of the book

     */
    public Book(String name, String author, String publisher, String genre, String ISBN, long year) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.ISBN = ISBN;
        this.year = year;

        //Not needed because the id is auto generated
        //this.ID = booksAdded;
        //booksAdded++;
    }
    public Book(int id, String name, String author, String publisher, String genre, String ISBN, long year){
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.ISBN = ISBN;
        this.year = year;
        this.ID = id;
    }

    /*
    Purpose: used to get the books id
    return: integer that represents the id number
     */
    public int getID(){
        return this.ID;
    }

    /*
    Purpose: Used to determine if two instances of the book class are equal. They are considered equal if the id numbers match.
    Params:
    o - Base object so that any object can be passed

    return: Boolean, returns true if ids match, returns false if ids don't match or if object given isn't a book
     */
    public boolean equals(Object o){
        //Check if object given is a book
        if(o instanceof Book){
            //Cast the object to a book to get the ID
            Book b = (Book) o;
            return b.getID() == this.getID();
        }
        else
            return false;
    }


    /*
    Purpose: used to get the books name
    return: String that represents the book name
     */
    public String getName() {
        return name;
    }

    /*
    Purpose: used to get the books author
    return: String that represents the book author
     */
    public String getAuthor() {
        return author;
    }

    /*
    Purpose: used to get the books publisher
    return: String that represents the book publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /*
    Purpose: used to get the books genre
    return: String that represents the book genre
     */
    public String getGenre() {
        return genre;
    }

    /*
    Purpose: used to get the books isbn
    return: String that represents the book isbn
     */
    public String getISBN() {
        return ISBN;
    }

    /*
    Purpose: used to get the books year
    return: Long that represents the books year
     */
    public long getYear() {
        return year;
    }

    /*
    Purpose: used to set the books name
    Params:
    name - String representing the books name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    Purpose: used to set the books author
    Params:
    author - String representing the books author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /*
    Purpose: used to set the books author
    Params:
    author - String representing the books author
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /*
    Purpose: used to set the books genre
    Params:
    genre - String representing the books genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /*
    Purpose: used to set the books isbn
    Params:
    ISBN - String representing the books isbn
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /*
    Purpose: used to set the books year
    Params:
    year - Long representing the books year
     */
    public void setYear(long year) {
        this.year = year;
    }

    /*
    Purpose: Used to print the info of a book
    return: returns the id and name of the book
     */
    @Override
    public String toString() {
        return "ID: "+this.ID +" Name: "+this.name;
    }
}
