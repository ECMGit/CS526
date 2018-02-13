package com.company;


/**create class Painting extends Artpainting*/
public class Painting extends Artwork {
    String paintType;
    String material;

    public Painting(String id, String artist, int year, String location, String paintType, String material) {
        super(id, artist, year, location);
        this.paintType = paintType;
        this.material = material;
    }

    public String getPaintType() {
        return paintType;
    }

    public String getMaterial() {
        return material;
    }

    public void setPaintType(String paintType) {
        this.paintType = paintType;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public static void printSummary(Painting painting){
        System.out.println("ID: " + painting.id+
                "\nartwork type: painting" +
                "\nartist: " + painting.artist+
                "\nyear: " + painting.year+
                "\nlocation: " + painting.location+
                "\npainting type: " + painting.paintType+
                "\nmaterial: " + painting.material +"\n");

    }

}
