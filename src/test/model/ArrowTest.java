package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrowTest {
    Arrow arrow1;
    Arrow arrow2;
    Arrow arrow3;

    @BeforeEach
    void runBefore() {
        arrow1 = new Arrow(28, 600, 100, "E1");
        arrow2 = new Arrow(28, 600, 100, "E2");
        arrow3 = new Arrow(28, 600, 100, "E3");
    }

    @Test
    public void setNameTest() {
        assertEquals("E1", arrow1.getName());
        arrow1.setName("GE1");
        assertEquals("GE1", arrow1.getName());
        arrow1.setName("HE1");
        assertEquals("HE1", arrow1.getName());
    }

    @Test
    public void showArrowStatTest() {
        String stat = "Length: " + 28 + " inches" + "\n"
                + "Spine: " + 600 + "\n"
                + "Weight: " + 100 + " grains";

        assertEquals(stat, arrow1.showArrowStat());
        assertEquals(stat, arrow2.showArrowStat());
    }

    @Test
    public void arrowToStringTest() {
        assertEquals(arrow1.getName(), arrow1.toString());
        assertEquals(arrow2.getName(), arrow2.toString());
        assertEquals(arrow3.getName(), arrow3.toString());
    }
}
