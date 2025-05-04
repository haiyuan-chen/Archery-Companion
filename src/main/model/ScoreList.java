package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// A record containing lists of scores
public class ScoreList implements Writable {
    private Map<Arrow, List<Integer>> scores;

    // Constructs a empty record with no arrows nor any associated scores
    public ScoreList() {
        this.scores = new HashMap<>();
    }

    // Modifies: this
    // Effect: Add scores of an arrow to a hashmap for record keeping
    public void addScoresToList(Arrow arrow, int score) {
        if (!scores.containsKey(arrow)) {
            List<Integer> s = new ArrayList<>();
            s.add(score);
            scores.putIfAbsent(arrow, s);
            EventLog.getInstance().logEvent(new Event(score + " points recorded for arrow: " + arrow.getName()));
        } else {
            List<Integer> scoreList = scores.get(arrow);
            scoreList.add(score);
            EventLog.getInstance().logEvent(new Event(score + " points recorded for arrow: " + arrow.getName()));
        }

    }

    // Reqiures: existing arrow in the map
    // Effect: return a list of the scores of the given arrow in the order it was
    // recorded
    public List<Integer> getArrowScores(Arrow arrow) {
        return scores.get(arrow);
    }

    // Effect: return the arrows contained in this record
    public Set<Arrow> getArrows() {
        return scores.keySet();
    }

    public void clear() {
        EventLog.getInstance().logEvent(new Event("Scores deleted from: "
                + scores.keySet()));
        scores.clear();

    }

    // Reqiures: existing arrow with at least 1 score in the map
    // Effects: produce the average score of the given arrow
    public double arrowScoreAverage(Arrow arrow) {
        double sum = 0;

        List<Integer> list = scores.get(arrow);
        int size = list.size();
        for (Integer s : list) {
            sum += s;
        }
        double avg = sum / (double) size;
        return avg;
    }

    // Reqiures: existing arrow in the map
    // Effect: produce the median score of the given arrow
    public double arrowScoreMedian(Arrow arrow) {
        double median = 0.0;
        List<Integer> list = scores.get(arrow);

        list = new ArrayList<>(list);
        int indexPosition = 0;
        int size = list.size();
        list.sort(null);
        if (size % 2 == 0) {
            int indexPosition1 = size / 2 - 1;
            int indexPosition2 = size / 2;
            median = ((double) list.get(indexPosition1) + list.get(indexPosition2)) / 2;
        } else {
            indexPosition = size / 2;
            median = list.get(indexPosition);
        }
        return median;
    }

    // Effects: convert Score List to a JSON object for saving or loading
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray scoreArray = new JSONArray();
        for (Arrow arrow : getArrows()) {
            JSONObject arrowScore = new JSONObject();
            arrowScore.put("Arrow", arrow.toJson());
            arrowScore.put("Scores", getArrowScores(arrow));
            scoreArray.put(arrowScore);
        }
        json.put("Scores", scoreArray);
        return json;
    }

}
