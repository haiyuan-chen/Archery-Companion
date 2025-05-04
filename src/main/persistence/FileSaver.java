package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.JSONObject;
import model.LoadoutList;
import model.ScoreList;

// CITATION: class and methods modeled after JsonSerializationDemo example program

public class FileSaver {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String path;

    // EFFECTS: constructs writer to write to destination file
    public FileSaver(String fileName) {
        this.path = "./data/" + fileName + ".json";
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(LoadoutList storage) {
        JSONObject json = storage.toJson();
        saveToFile(json.toString(TAB));
    }

    public void write(LoadoutList loadouts, ScoreList scores) {
        JSONObject json = new JSONObject();
        JSONObject loadoutJson = loadouts.toJson();
        json.put("Loadouts", loadoutJson.getJSONArray("Loadouts"));
        json.put("Scores", scores.toJson().getJSONArray("Scores"));
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
