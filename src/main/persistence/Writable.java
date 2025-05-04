package persistence;

import org.json.JSONObject;

// CITATION: class and methods modeled after JsonSerializationDemo example program

public interface Writable {
    // EFFECTS: returns this as JSON object

    JSONObject toJson();
}
