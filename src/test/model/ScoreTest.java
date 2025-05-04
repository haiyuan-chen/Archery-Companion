// package model;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Stream;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;


// public class ScoreTest {
//     private Score testScores;

//     @BeforeEach
//     void init() {
//         testScores = new Score();
//     }

//     @Test
//     public void addScoresTest() {
//         testScores = new Score();
//         int score = 0;
//         // assertEquals(0, testScores.getScores().size());

//         testScores.addScores(8);
//         score = testScores.getScores().get(0);
//         assertEquals(8, score);

//         testScores.addScores(8);
//         assertEquals(2, testScores.getScores().size());
//         score = testScores.getScores().get(1);
//         assertEquals(8, score);

//         testScores.addScores(0);
//         testScores.addScores(10);
//         score = testScores.getScores().get(2);
//         assertEquals(0, score);
//         score = testScores.getScores().get(3);
//         assertEquals(10, score);
//     }

//     @Test
//     public void getScoresTest() {
//         init();
//         List<Integer> list = new ArrayList<>();
//         list = Stream.of(0, 1, 2, 3, 4, 5).toList();

//         testScores.addScores(0);
//         testScores.addScores(1);
//         testScores.addScores(2);
//         testScores.addScores(3);
//         testScores.addScores(4);
//         testScores.addScores(5);

//         assertEquals(list, testScores.getScores());
//         testScores.addScores(3);
//         testScores.addScores(3);
//         list = Stream.of(0, 1, 2, 3, 4, 5, 3, 3).toList();
//         assertEquals(list, testScores.getScores());
//     }

//     @Test
//     public void scoreAverageTest() {
//         init();
//         double average = 0;
//         testScores.addScores(0);
//         testScores.addScores(0);
//         testScores.addScores(0);
//         assertEquals(0, Double.compare(0, testScores.scoreAverage()));

//         testScores.addScores(10);
//         average = 10.0 / testScores.getSize();
//         assertEquals(average, testScores.scoreAverage(), 0.1);
//     }
// }
