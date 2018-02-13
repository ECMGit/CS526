package com.company;

public class Artwork {
    String id;
    String artist;
    int year;
    String location;

        //constructor
    public Artwork(String id, String artist, int year, String location) {
        if (year <= 0)  //preconditions: year must be a postive number;
            throw new IllegalArgumentException("Plz enter a positive number of year~");

        this.id = id;
        this.artist = artist;
        this.year = year;
        this.location = location;
    }

    //getter of each variables
    public String getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }


    //and setter
    public void setId(String id) {
        this.id = id;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setYear(int year) {
        if (year <= 0)
            throw new IllegalArgumentException("Plz enter a positive number of year~");

        this.year = year;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //print artwork info method
    public static void printSummary(Artwork work) {
        System.out.println("ID = " + work.id+" artist = " + work.artist+" year = " + work.year+" location = " + work.location+"\n");


    }
}
