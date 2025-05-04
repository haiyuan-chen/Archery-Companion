package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowTest {
    Bow testBow;

    @BeforeEach
    void runBefore() {
        testBow = new Bow("W & W", 72, 23, 9.0, 28);
    }


    @Test
    void showAllStatTest() {
        String stat = "Name: " + "W & W" + "\n"
                + "Length: " + 72 + " inches" + "\n"
                + "Draw weight: " + 23 + " lb" + "\n"
                + "Draw length: " + 28.0 + " inches" + "\n"
                + "Brace Height: " + 9.0 + " inches" + "\n";
        ;
        assertEquals(stat, testBow.showAllStat());
    }
}
