package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a bow with name, length in inches, 
// draw weight in pounds at 28'', brace height in inches, 
// and draw length in inches

public class Bow implements Writable {
    private String name;
    private int length;
    private int drawWeight;
    private double braceHeight;
    private double drawLength;

    // REQUIRES: all values must be > 0
    // EFFECTS: constructs a bow with the given parameters
    public Bow(String name, int length, int drawWeight, double braceHeight, double drawLength) {
        this.name = name;
        this.length = length;
        this.drawWeight = drawWeight;
        this.braceHeight = braceHeight;
        this.drawLength = drawLength;
    }

    public String getName() {
        return this.name;
    }

    // Effects: return length value of the bow
    public int getLength() {
        return this.length;
    }

    // Effects: return draw weight of the bow
    public int getDrawWeight() {
        return this.drawWeight;
    }

    // Effects: return the brace height of the bow
    public double getBraceHeight() {
        return this.braceHeight;
    }

    // Effects: return draw length of the user using this bow
    public double getDrawLength() {
        return this.drawLength;
    }

    // Effect: print out strings containing all the parameters of the bow.
    // Each parameter is printed on a seperate line.
    public String showAllStat() {
        String stat = "Name: " + getName() + "\n"
                + "Length: " + getLength() + " inches" + "\n"
                + "Draw weight: " + getDrawWeight() + " lb" + "\n"
                + "Draw length: " + getDrawLength() + " inches" + "\n"
                + "Brace Height: " + getBraceHeight() + " inches" + "\n";

        return stat;
    }

    // EFFECTS: construct a JSONOBject that contains all information for this bow
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("length", length);
        json.put("brace height", braceHeight);
        json.put("draw weight", drawWeight);
        json.put("draw length", drawLength);

        return json;
    }

    @Override
    public String toString() {
        return getName();
    }

}
