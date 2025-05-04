package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistence.FileLoader;
import persistence.FileSaver;

// CITATION: Tests modeled from provided project examples
public class FileLoaderTest {
    private LoadoutList list;
    private Loadout l1;
    private Loadout l2;
    private Loadout l3;
    private ScoreList scoreList;

    @BeforeEach
    public void setup() {
        setupCreateObjects();
        Bow testBow = new Bow("1", 72, 23, 9.0, 28);
        Bow testBow2 = new Bow("2", 76, 25, 10.0, 29);
        Bow testBow3 = new Bow("3", 78, 30, 10.5, 27);
        Arrow arrow1 = new Arrow(28, 600, 5, "E1");
        Arrow arrow2 = new Arrow(28, 600, 5, "E2");
        Arrow arrow3 = new Arrow(28, 600, 5, "E3");
        for (int i = 1; i < 3; i++) {
            l1.addArrow(new Arrow(28, 600, 5, "E" + i));
            l2.addArrow(new Arrow(28, 600, 5, "E" + i));
            l3.addArrow(new Arrow(28, 600, 5, "E" + i));
        }
        l3.setBow(testBow3);
        l2.setBow(testBow2);
        l1.setBow(testBow);
        list.addLoadout(l1);
        list.addLoadout(l2);
        list.addLoadout(l3);
        scoreList = new ScoreList();
        scoreList.addScoresToList(arrow1, 2);
        scoreList.addScoresToList(arrow2, 4);
        scoreList.addScoresToList(arrow3, 8);

    }

    public void setupCreateObjects() {
        list = new LoadoutList();
        l1 = new Loadout("loadout1");
        l2 = new Loadout("loadout2");
        l3 = new Loadout("loadout3");
    }

    @Test
    public void saveInvalidFileTest() {
        try {
            FileSaver saver = new FileSaver("\00");
            saver.open();
            fail("This is fine, sips tea");
        } catch (IOException e) {
            // empty
        }
    }

    @Test
    public void saveEmptyListTest() {
        try {
            LoadoutList list = new LoadoutList();
            FileSaver saver = new FileSaver("saveEmptyListTest");
            saver.open();
            saver.write(list);
            saver.close();

            FileLoader loader = new FileLoader("saveEmptyListTest");
            list = loader.read();
            assertEquals(0, list.getSize());

        } catch (IOException e) {
            fail("This shouldn't have happened");
        }
    }

    @Test
    public void saveLoadoutListTest() {
        try {
            setup();
            FileSaver saver = new FileSaver("SaveLoadoutListTest");
            saver.open();
            saver.write(list);
            saver.close();

            FileLoader loader = new FileLoader("SaveLoadoutListTest");
            list = loader.read();
            System.out.println(list.getLoadout(0).toString());
            assertEquals(l1.getName(), list.getLoadout(0).getName());
            assertEquals(l2.getBow().showAllStat(), list.getLoadout(1).getBow().showAllStat());
            assertEquals(l3.getArrow(0).showArrowStat(), list.getLoadout(2).getArrow(0).showArrowStat());
            assertEquals(3, list.getSize());

        } catch (Exception e) {
            fail("This shouldn't have happened");
        }
    }

    @Test
    public void scoreListToJsonTest() {
        FileLoader loader = new FileLoader("curatorDataTest");
        try {
            ScoreList testList = loader.readScores();
            assertEquals(testList.getArrows(), scoreList.getArrows());
        } catch (Exception e) {
            fail("failed");
        }
    }

    @Test
    public void parseScoreListEmptyTest() {
        try {
            FileSaver saver = new FileSaver("emptyScoresTest");
            saver.open();
            saver.write(list);
            saver.close();
            FileLoader loader = new FileLoader("emptyScoresTest");
            ScoreList loadedScores = loader.readScores();
            assertEquals(0, loadedScores.getArrows().size());
        } catch (IOException e) {
            fail("This shouldn't happen");
        }
    }
    
}
