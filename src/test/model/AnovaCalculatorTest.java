package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnovaCalculatorTest {
    private ScoreList scoreList;
    private Arrow arrow1;
    private Arrow arrow2;
    private Arrow arrow3;
    private AnovaCalculator anova;

    @BeforeEach
    public void setup() {
        scoreList = new ScoreList();
        arrow1 = new Arrow(30, 300, 50, "Arrow1");
        arrow2 = new Arrow(30, 300, 50, "Arrow2");
        arrow3 = new Arrow(30, 300, 50, "Arrow3");

        scoreList.addScoresToList(arrow1, 8);
        scoreList.addScoresToList(arrow1, 9);
        scoreList.addScoresToList(arrow1, 7);
        scoreList.addScoresToList(arrow1, 8);

        scoreList.addScoresToList(arrow2, 6);
        scoreList.addScoresToList(arrow2, 7);
        scoreList.addScoresToList(arrow2, 6);
        scoreList.addScoresToList(arrow2, 7);

        scoreList.addScoresToList(arrow3, 9);
        scoreList.addScoresToList(arrow3, 9);
        scoreList.addScoresToList(arrow3, 8);
        scoreList.addScoresToList(arrow3, 9);

        anova = new AnovaCalculator(scoreList);
    }

    @Test
    public void testGrandMean() {
        assertEquals(7.75, anova.getGrandMean(), 0.01);
    }

    @Test
    public void testSSB() {
        assertEquals(10.5, anova.getSumOfSquaresBetween(), 0.01);
    }

    @Test
    public void testSSW() {
        assertEquals(3.75, anova.getSumOfSquaresWithin(), 0.01);
    }

    @Test
    public void testDegreesOfFreedom() {
        assertEquals(2, anova.getDfBetween());
        assertEquals(9, anova.getDfWithin());
    }

    @Test
    public void testMeanSquares() {
        assertEquals(5.25, anova.getMeanSquaredBetween(), 0.01);
        assertEquals(0.41667, anova.getMeanSquaredWithin(), 0.01);
    }

    @Test
    public void testFValue() {
        assertEquals(12.6, anova.getFstat(), 0.1);
    }

    @Test
    public void testSameScores() {
        ScoreList uniformList = new ScoreList();
        Arrow a1 = new Arrow(30, 300, 50, "A1");
        Arrow a2 = new Arrow(30, 300, 50, "A2");
        Arrow a3 = new Arrow(30, 300, 50, "A3");
        uniformList.addScoresToList(a1, 7);
        uniformList.addScoresToList(a1, 7);
        uniformList.addScoresToList(a1, 7);
        uniformList.addScoresToList(a2, 7);
        uniformList.addScoresToList(a2, 7);
        uniformList.addScoresToList(a2, 7);
        uniformList.addScoresToList(a3, 7);
        uniformList.addScoresToList(a3, 7);
        uniformList.addScoresToList(a3, 7);
        AnovaCalculator calcUniform = new AnovaCalculator(uniformList);
        assertEquals(7, calcUniform.getGrandMean(), 0.01);
        assertEquals(0, calcUniform.getSumOfSquaresBetween(), 0.01);
        assertEquals(0, calcUniform.getFstat(), 0.001);
    }
}
