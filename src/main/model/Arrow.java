package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an arrow with its shaft length in inches, 
// spine rating, tip weight in grains, and identifying label

public class Arrow implements Writable {
    private int length;
    private int spine;
    private int weight;
    private String name;

    // Required: length, spine, and weight must be > 0
    // Effects: constructs an arrow with the given label, shaft length,
    // spine rating, and weight
    public Arrow(int length, int spine, int weight, String label) {
        this.length = length;
        this.spine = spine;
        this.weight = weight;
        this.name = label;

    }

    // Effects: return length
    public int getLength() {

        return this.length;
    }

    // Effects: return spine
    public int getSpine() {

        return this.spine;
    }

    // Effects: return weight
    public int getWeight() {

        return this.weight;
    }

    // Effects: return name
    public String getName() {

        return this.name;
    }

    // Modifies: this
    // Effects: set label to new given label
    public void setName(String newName) {
        EventLog.getInstance().logEvent(new Event("Arrow "
                + this.name + " replaced with arrow:  " + newName));
        this.name = newName;
    }

    // Effect: Produce a string containing the length, spine rating, and tip weight of this arrow
    public String showArrowStat() {
        String stat = "Length: " + this.getLength() + " inches" + "\n"
                + "Spine: " + this.getSpine() + "\n"
                + "Weight: " + this.getWeight() + " grains";
        return stat;
    }

    // EFFECTS: construct a JSONOBject that contains all information for this arrow
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("label", name);
        json.put("length", length);
        json.put("spine", spine);
        json.put("weight", weight);

        return json;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
