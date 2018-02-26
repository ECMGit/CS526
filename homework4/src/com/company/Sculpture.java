package com.company;

public class Sculpture extends Artwork {
    String material;
    double height;
    double weight;

    public Sculpture(String id, Person artist, int year, String location, String material, double height, double weight) {
        super(id, artist, year, location);
        if (height <= 0)  //preconditions: height must be a postive number;
            throw new IllegalArgumentException("Plz enter a positive number of height~");
        if (weight <= 0)  //preconditions: year must be a postive number;
            throw new IllegalArgumentException("Plz enter a positive number of weight~");

        this.material = material;
        this.height = height;
        this.weight = weight;
    }
    /**get variable material, height, weight*/

    public String getMaterial() {
        return material;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    /**set variable material, height, weight*/
    public void setMaterial(String material) {
        this.material = material;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static void printSummary(Sculpture sculpture){
        System.out.println("ID: " + sculpture.id+
                "\nartwork type: sculpture" +
                "\nartist: " + sculpture.artist.name+
                "\nartist's birth year: " + sculpture.artist.birth_Year+
                "\nyear: " + sculpture.year+
                "\nlocation: " + sculpture.location+
                "\nmaterial: " + sculpture.material+
                "\nsculpture type: " + sculpture.height+
                "\nsculpture type: " + sculpture.weight+"\n");

    }

}
