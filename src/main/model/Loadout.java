package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// A loadout consisting of a bow and a set of 6-12 arrows

public class Loadout implements Writable {
    private String name;
    private Bow bow;
    private List<Arrow> arrows;

    // Effects: construct an empty equipment setup with a name but
    // no bow and an empty list of arrows
    public Loadout(String name) {
        this.name = name;
        this.arrows = new ArrayList<>();
    }

    // Requires: No bow in the loadout. Can have only one bow in a loadout.
    // Modifies: this
    // Effect: adds a bow to the loadout
    public void setBow(Bow bow) {
        this.bow = bow;
        EventLog.getInstance().logEvent(new Event("Bow: "
                + bow.getName() + " added to the laodout: " + this.name));

    }

    // Requires: Cannot have more arrows than 12 arrows
    // in the loadout. Names cannot be identical.
    // All arrows need to have same specification except for the labels
    // Modifies: this
    // Effect: Adds an arrow to a list of arrows
    public void addArrow(Arrow arrow) {
        this.arrows.add(arrow);
        EventLog.getInstance().logEvent(new Event("Arrow "
                + arrow.getName() + " added to the loadout: " + this.name));

    }

    // Requires: Cannot have less than 0 arrow in the loadout
    // Modifies: this
    // Effect: Removes an arrow from this list of arrows
    public void removeArrow(String label) {
        EventLog.getInstance().logEvent(new Event("Arrow "
                + label + " removed from the loadout: " + this.name));
        this.arrows.remove(getArrow(label));
    }

    // Requires: Non-empty set of arrows
    // Effects: Return the arrow from the set with the given label
    public Arrow getArrow(String label) {
        Arrow thisOne = null;
        for (Arrow a : arrows) {
            if (a.getName().equals(label)) {
                return a;
            }
        }
        return thisOne;
    }

    // Requires: Non-empty set of arrows
    // Effects: Return the arrow from the set with the given index position
    public Arrow getArrow(int index) {
        return arrows.get(index);
    }

    // Requires: Non-empty set of arrows
    // Effect: Produce a string containing the label of every arrow in the list
    public String listAllArrows() {
        List<String> list = new ArrayList<>();
        for (Arrow a : arrows) {
            list.add(a.getName());
        }
        return list.toString();
    }

    // Effect: returns the bow registered in this loadout
    public Bow getBow() {
        return this.bow;
    }

    // Effect: returns the number of arrows added to this loadout
    public int arrowsCount() {
        int totalArrows = this.arrows.size();
        return totalArrows;
    }

    // Effect: getter for name
    public String getName() {
        return this.name;
    }

    // Modifies: this
    // Effect: change the name of the loadout to new given name
    public void changeName(String newName) {
        EventLog.getInstance().logEvent(new Event("Changed loadout name from "
                + this.name + " to: " + newName));
        this.name = newName;
    }

    // EFFECTS: construct a JSONOBject that contains all information within this
    // loadout
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Loadout name", name);
        json.put("Bow information", bow.toJson());
        json.put("Arrows", arrowsToJson());

        return json;
    }

    private JSONArray arrowsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Arrow a : arrows) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    public List<Arrow> getArrows() {
        return this.arrows;
    }

    @Override
    public String toString() {
        return getName() + ", Bow: " + getBow() + ", Arrows: " + arrowsCount();
    }

}
