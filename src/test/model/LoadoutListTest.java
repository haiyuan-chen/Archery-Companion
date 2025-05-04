package model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoadoutListTest {

    private LoadoutList list;
    private Loadout l1;
    private Loadout l2;
    private Loadout l3;

    @BeforeEach
    public void setup() {
        list = new LoadoutList();
        l1 = new Loadout("loadout1");
        l2 = new Loadout("loadout2");
        l3 = new Loadout("loadout3");
        Bow testBow = new Bow("1", 72, 23, 9.0, 28);
        Bow testBow2 = new Bow("2", 76, 25, 10.0, 29);
        Bow testBow3 = new Bow("3", 78, 30, 10.5, 27);
        Arrow arrow1 = new Arrow(28, 600, 5, "E1");
        Arrow arrow2 = new Arrow(28, 600, 5, "E2");
        Arrow arrow3 = new Arrow(28, 600, 5, "E3");
        l3.setBow(testBow3);
        l3.addArrow(arrow1);
        l3.addArrow(arrow2);
        l3.addArrow(arrow3);
        l2.setBow(testBow2);
        l2.addArrow(arrow1);
        l2.addArrow(arrow2);
        l2.addArrow(arrow3);
        l1.setBow(testBow);
        l1.addArrow(arrow1);
        l1.addArrow(arrow2);
        l1.addArrow(arrow3);
    }

    @Test
    public void addLoadoutTest() {
        setup();
        list.getSize();
        list.addLoadout(l1);
        assertEquals(1, list.getSize());
        list.addLoadout(l3);
        list.addLoadout(l2);
        assertEquals(3, list.getSize());
    }

    @Test
    public void getLoadoutTest() {
        setup();
        list.addLoadout(l3);
        list.addLoadout(l2);
        assertEquals(l3, list.getLoadout(0));
        assertEquals(l2, list.getLoadout(1));
        assertTrue(list.getLoadouts().contains(l2));
        assertTrue(list.getLoadouts().contains(l3));

    }

    @Test
    public void getLoadoutDeeperTest() {
        setup();
        list.addLoadout(l1);
        Arrow a1 = l1.getArrow("E1");
        assertEquals(a1, l1.getArrow(0));
        assertNull(l1.getArrow("A2"));
        assertEquals("1", l1.getBow().getName());
        assertEquals(3, l1.arrowsCount());
        assertEquals("loadout1", l1.getName());
        l1.changeName("changedLoadout");
        assertEquals("changedLoadout", l1.getName());

    }

    @Test
    public void getLoadoutBowTest() {
        setup();
        Bow testBow2 = new Bow("2", 76, 25, 10.0, 29);
        Bow testBow3 = new Bow("3", 78, 30, 10.5, 27);
        assertEquals(28, l1.getBow().getDrawLength(), 0.01);
        assertEquals(9, l1.getBow().getBraceHeight(), 0.01);
        assertEquals(25, l2.getBow().getDrawWeight());
        assertEquals(78, l3.getBow().getLength());
        assertEquals("3", l3.getBow().getName());

        String stat = "Name: " + "2" + "\n"
                + "Length: " + 76 + " inches" + "\n"
                + "Draw weight: " + 25 + " lb" + "\n"
                + "Draw length: " + 29.0 + " inches" + "\n"
                + "Brace Height: " + 10.0 + " inches" + "\n";

        assertEquals(stat, l2.getBow().showAllStat());
        l3.setBow(testBow2);
        assertTrue(stat.equals(l3.getBow().showAllStat()));
        assertEquals(stat, l3.getBow().showAllStat());
        l3.setBow(testBow3);
        assertFalse(stat.equals(l3.getBow().showAllStat()));
    }

    @Test
    public void getloadoutArrowsTest() {
        setup();
        Arrow cloneArrow = l1.getArrow(2);
        String stat = cloneArrow.showArrowStat();
        assertEquals(stat, l1.getArrow(2).showArrowStat());
        assertEquals("E3", cloneArrow.getName());
        cloneArrow.setName("clone3");
        assertEquals("clone3", cloneArrow.getName());
        cloneArrow.setName("E3");

        l2.removeArrow("E2");
        assertEquals(2, l2.arrowsCount());
        assertNull(l2.getArrow("E2"));
        assertEquals("E1", l2.getArrow(0).getName());
        assertEquals("E3", l2.getArrow(1).getName());
        String a = "[E1, E3]";
        assertEquals(a, l2.listAllArrows());

    }

    @Test
    public void removeLoadoutTest() {
        setup();
        list.addLoadout(l1);
        list.addLoadout(l2);
        list.addLoadout(l3);

        list.removeLoadout(l1);
        assertEquals(l2, list.getLoadout(0));
        assertEquals(l3, list.getLoadout(1));
        list.removeLoadout(l2);
        assertEquals(l3, list.getLoadout(0));
        list.addLoadout(l1);
        assertEquals(l1, list.getLoadout(1));

    }

    @Test
    public void finLoadoutTest() {
        setup();
        list.addLoadout(l1);
        assertEquals(l1, list.findLoadout("loadout1"));
        assertNull(list.findLoadout("loadout2"));
        list.addLoadout(l2);
        list.addLoadout(l3);
        assertEquals(l2, list.findLoadout("loadout2"));
        assertEquals(l3, list.getLoadout(2));
    }
}
