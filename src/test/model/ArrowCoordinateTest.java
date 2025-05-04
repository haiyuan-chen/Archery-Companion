package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrowCoordinateTest {
    private Arrow arrow1;
    private Arrow arrow2;
    private Arrow arrow3;
    private Arrow arrow4;
    private ArrowCoordinate c1;
    private ArrowCoordinate c2;
    private ArrowCoordinate c3;
    private ArrowCoordinate c4;

    @BeforeEach
    public void runBefore() {
        arrow1 = new Arrow(31, 600, 200, "a1");
        arrow2 = new Arrow(31, 600, 200, "a2");
        arrow3 = new Arrow(31, 600, 200, "a3");
        arrow4 = new Arrow(31, 600, 200, "a4");
        c1 = new ArrowCoordinate(arrow1, 0, 0);
        c2 = new ArrowCoordinate(arrow2, 500, 0);
        c3 = new ArrowCoordinate(arrow3, 0, 500);
        c4 = new ArrowCoordinate(arrow4, 300, 300);

    }

    @Test
    public void constructArrowCoordinateTest() {
        Arrow arrow5 = new Arrow(31, 600, 200, "a5");
        ArrowCoordinate c5 = new ArrowCoordinate(arrow5, 200, 300);
        assertFalse(c5.equals(null));
        assertTrue(c5.getArrow().equals(arrow5));
        assertTrue(c5.getXcoord() == 200);
        assertTrue(c5.getYcoord() == 300);
    }

    @Test
    public void scoreTest() {
        assertEquals(0, c1.getScore());
        assertEquals(1, c2.getScore());
        assertEquals(1, c3.getScore());
        assertEquals(5, c4.getScore());

        Arrow arrow5 = new Arrow(31, 600, 200, "a5");
        ArrowCoordinate c5 = new ArrowCoordinate(arrow5, 500, 500);

        assertEquals(10, c5.getScore());
    }
}
