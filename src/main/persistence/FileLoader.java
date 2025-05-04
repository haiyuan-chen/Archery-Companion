package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Arrow;
import model.Bow;
import model.Loadout;
import model.LoadoutList;
import model.ScoreList;

// CITATION: class and methods modeled after JsonSerializationDemo example program

public class FileLoader {
    private String path;

    // EFFECTS: constructs a loader to read from source file
    public FileLoader(String fileName) {
        this.path = "./data/" + fileName + ".json";
    }

    // EFFECTS: reads data from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LoadoutList read() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLoadoutList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses LoadoutList from JSON object and returns it
    private LoadoutList parseLoadoutList(JSONObject jsonObject) {
        LoadoutList ll = new LoadoutList();
        JSONArray loadoutArray = jsonObject.getJSONArray("Loadouts");

        for (int i = 0; i < loadoutArray.length(); i++) {
            JSONObject j = loadoutArray.getJSONObject(i);
            Loadout l = parseLoadout(j);
            ll.addLoadout(l);
        }
        return ll;
    }

    // EFFECTS: parses Loadout from JSON object and returns it
    public Loadout parseLoadout(JSONObject jsonObject) {
        String name = jsonObject.getString("Loadout name");
        Loadout loadout = new Loadout(name);

        JSONObject jsonBow = jsonObject.getJSONObject("Bow information");
        Bow bow = parseBow(jsonBow);
        loadout.setBow(bow);

        JSONArray arrowList = jsonObject.getJSONArray("Arrows");
        for (int i = 0; i < arrowList.length(); i++) {
            JSONObject jsonArrow = arrowList.getJSONObject(i);
            Arrow arrow = parseArrow(jsonArrow);
            loadout.addArrow(arrow);
        }
        return loadout;
    }

    // EFFECTS: parses Bow from JSON object and returns it
    public Bow parseBow(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int length = jsonObject.getInt("length");
        int drawWeight = jsonObject.getInt("draw weight");
        double braceHeight = jsonObject.getDouble("brace height");
        double drawLength = jsonObject.getDouble("draw length");

        return new Bow(name, length, drawWeight, braceHeight, drawLength);
    }

    // EFFECTS: parses Arrow from JSON object and returns it
    public Arrow parseArrow(JSONObject jsonObject) {
        String label = jsonObject.getString("label");
        int length = jsonObject.getInt("length");
        int spine = jsonObject.getInt("spine");
        int weight = jsonObject.getInt("weight");

        return new Arrow(length, spine, weight, label);
    }

    
    public ScoreList readScores() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScoreList(jsonObject);
    }

    private ScoreList parseScoreList(JSONObject jsonObject) {
        ScoreList scoreList = new ScoreList();
        if (jsonObject.has("Scores")) {
            JSONArray scoreArray = jsonObject.getJSONArray("Scores");
            for (int i = 0; i < scoreArray.length(); i++) {
                JSONObject arrowScore = scoreArray.getJSONObject(i);
                JSONObject arrowJson = arrowScore.getJSONObject("Arrow");
                int length = arrowJson.getInt("length");
                int spine = arrowJson.getInt("spine");
                int weight = arrowJson.getInt("weight");
                String name = arrowJson.getString("label");
                Arrow arrow = new Arrow(length, spine, weight, name);
                JSONArray scoresJson = arrowScore.getJSONArray("Scores");
                for (int j = 0; j < scoresJson.length(); j++) {
                    int score = scoresJson.getInt(j);
                    scoreList.addScoresToList(arrow, score);
                }
            }
        }
        return scoreList;
    }
}
