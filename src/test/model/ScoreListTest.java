package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreListTest {
    private ScoreList record;
    private Arrow a1;
    private Arrow a2;
    private Arrow a3;

    @BeforeEach
    void setup() {
        record = new ScoreList();
        a1 = new Arrow(30, 500, 200, "a1");
        a2 = new Arrow(30, 500, 200, "a2");
        a3 = new Arrow(30, 500, 200, "a3");
    }

    public void addScoresToArrows() {
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 9);
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 9);
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 10);
        record.addScoresToList(a1, 10);

        record.addScoresToList(a2, 8);
        record.addScoresToList(a2, 8);
        record.addScoresToList(a2, 5);
        record.addScoresToList(a2, 6);
        record.addScoresToList(a2, 7);
        record.addScoresToList(a2, 5);

        record.addScoresToList(a3, 3);
        record.addScoresToList(a3, 2);
        record.addScoresToList(a3, 4);
    }

    @Test
    public void addScoresToListTest() {
        setup();
        // test added to list
        record.addScoresToList(a1, 10);
        assertEquals(1, record.getArrowScores(a1).size());
        record.addScoresToList(a1, 9);
        assertEquals(2, record.getArrowScores(a1).size());
        record.addScoresToList(a1, 8);
        assertEquals(3, record.getArrowScores(a1).size());

        // test added correctly to list
        assertEquals(10, record.getArrowScores(a1).get(0));
        assertEquals(9, record.getArrowScores(a1).get(1));
        assertEquals(8, record.getArrowScores(a1).get(2));

    }

    @Test
    public void getArrowScoresTest() {
        setup();
        addScoresToArrows();
        List<Integer> list = new ArrayList<>();
        list = record.getArrowScores(a2);
        assertEquals(list.size(), record.getArrowScores(a2).size());
        assertEquals(5, list.get(5));
        assertEquals(8, list.get(0));
        assertEquals(8, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(6, list.get(3));
        assertEquals(7, list.get(4));

    }

    @Test
    public void clearScoresTest() {
        addScoresToArrows();
        assertEquals(3, record.getArrows().size());
        record.clear();
        assertTrue(record.getArrows().isEmpty());
    }

    @Test
    public void scoreListAverage() {
        setup();
        addScoresToArrows();
        double avg1 = (10 * 10 + 9 * 2) / 12.0;
        double avg2 = (8 + 8 + 5 + 6 + 7 + 5) / 6.0;
        double avg3 = (3 + 2 + 4) / 3.0;
        // Single score
        Arrow a4 = new Arrow(30, 500, 200, "a4");
        Arrow a5 = new Arrow(30, 500, 200, "a5");
        record.addScoresToList(a4, 0);
        assertEquals(0, record.arrowScoreAverage(a4));
        record.addScoresToList(a5, 10);
        assertEquals(10, record.arrowScoreAverage(a5));

        // Range of scores
        assertEquals(avg1, record.arrowScoreAverage(a1));
        assertEquals(avg2, record.arrowScoreAverage(a2));
        assertEquals(avg3, record.arrowScoreAverage(a3));

    }

    @Test
    public void scoreListMedian() {
        setup();
        addScoresToArrows();
        assertEquals(10, record.arrowScoreMedian(a1));
        assertEquals(6.5, record.arrowScoreMedian(a2));
        assertEquals(3, record.arrowScoreMedian(a3));
    }


}
