package model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoadoutTest {
    Loadout setup;
    Bow testBow;
    Arrow arrow1;
    Arrow arrow2;
    Arrow arrow3;
    Arrow arrow4;
    Arrow arrow5;
    Arrow arrow6;

    @BeforeEach
    void runBefore() {
        setup = new Loadout("Test loadout");
        testBow = new Bow("W & W", 72, 23, 9, 28);
        arrow1 = new Arrow(28, 600, 5, "E1");
        arrow2 = new Arrow(28, 600, 5, "E2");
        arrow3 = new Arrow(28, 600, 5, "E3");
        arrow4 = new Arrow(28, 600, 5, "E4");
        arrow5 = new Arrow(28, 600, 5, "E5");
        arrow6 = new Arrow(28, 600, 5, "E6");
    }

    @Test
    void changeNameTest() {
        assertEquals("Test loadout", setup.getName());
        setup.changeName("New Test");
        assertEquals("New Test", setup.getName());
        setup.changeName("Test loadout");
        assertEquals("Test loadout", setup.getName());
    }

    @Test
    void addBowTest() {
        setup.setBow(testBow);
        assertEquals(testBow, setup.getBow());
    }

    @Test
    void addArrowTest() {
        // add one arrow
        setup.addArrow(arrow1);
        assertEquals(arrow1, setup.getArrow("E1"));

        // add more arrows
        setup.addArrow(arrow2);
        setup.addArrow(arrow3);
        setup.addArrow(arrow4);
        setup.addArrow(arrow5);
        setup.addArrow(arrow6);
        assertEquals(arrow2, setup.getArrow(1));
        assertEquals(arrow6, setup.getArrow("E6"));
        assertNull(setup.getArrow("E7"));

    }

    @Test
    void arrowsCountTest() {
        assertEquals(0, setup.arrowsCount());
        setup.addArrow(arrow1);
        setup.addArrow(arrow2);
        setup.addArrow(arrow3);
        assertEquals(3, setup.arrowsCount());
        setup.addArrow(arrow4);
        setup.addArrow(arrow5);
        setup.addArrow(arrow6);
        assertEquals(6, setup.arrowsCount());
        assertEquals(6, setup.getArrows().size());

    }

}
