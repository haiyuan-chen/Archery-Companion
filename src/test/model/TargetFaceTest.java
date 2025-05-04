package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TargetFaceTest {

    @Test
    public void fieldTest() {
        assertEquals(500, TargetFace.CENTER_X);
        assertEquals(500, TargetFace.CENTER_Y);
        assertEquals(10, TargetFace.RING_BOUNDARIES.length);
        assertEquals(10, TargetFace.SCORES.length);

    }
    
    @Test
    public void distanceFromCenterTest() {
        assertEquals(0, TargetFace.distanceFromCenter(500, 500), 0.1);
        assertEquals(500, TargetFace.distanceFromCenter(0, 500), 0.1);
        assertEquals(500, TargetFace.distanceFromCenter(500, 0), 0.1);
        assertEquals(Math.sqrt(2), TargetFace.distanceFromCenter(501, 501), 0.1);
    }

    @Test
    public void calculateScoreTest() {
        assertEquals(0, TargetFace.calculateScore(0, 501));
        assertEquals(0, TargetFace.calculateScore(501, 0));
        assertEquals(10, TargetFace.calculateScore(501, 501));
        assertEquals(3, TargetFace.calculateScore(220, 220));
        assertEquals(2, TargetFace.calculateScore(200, 200));
        assertEquals(4, TargetFace.calculateScore(250, 250));
        assertEquals(5, TargetFace.calculateScore(300, 300));
    }

}
