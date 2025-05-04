package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// A list of loadouts with the ability to access each of the contained loadouts
public class LoadoutList implements Writable {
    private List<Loadout> sets;

    // Constructs an empty list to hold objects from Loadout class
    public LoadoutList() {
        this.sets = new ArrayList<>();
    }

    // Modifies: this
    // Effect: Add a loadout to this list
    public void addLoadout(Loadout loadout) {
        this.sets.add(loadout);
        EventLog.getInstance().logEvent(new Event("Loadout: " + loadout.getName()
                + " added to the loadout list."));
    }

    // Requires: Have at least one loadout in this list
    // Effect: Return the loadout with the given name.
    // Return null otherwise
    public Loadout findLoadout(String name) {
        for (Loadout l : sets) {
            if (l.getName().equals(name)) {
                return l;
            }
        }
        return null;
    }

    public List<Loadout> getLoadouts() {
        return this.sets;
    }

    // Requires: at least one element in this list
    // Effect: getter for this list of loadouts
    public Loadout getLoadout(int index) {
        return sets.get(index);
    }

    // Requires: at least one element in this list
    public void removeLoadout(Loadout lo) {
        EventLog.getInstance().logEvent(new Event("Loadout "
                + lo.getName() + " deleted from the loadout list."));
        sets.remove(lo);
    }

    // Effect: getter for size of list of loadout. However would return
    // a value of 0 if the list is empty instead of null;
    public int getSize() {
        if (this.sets.isEmpty()) {
            return 0;
        }
        return this.sets.size();
    }

    // EFFECTS: construct a JSONOBject that contains all information for all
    // loadouts in this list
    @Override
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("Loadouts", jsonArray);
        for (Loadout l : sets) {
            jsonArray.put(l.toJson());
        }

        return json;
    }

}
