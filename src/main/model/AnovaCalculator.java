package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Performs statistical analysis o determine if there are significant variations between scores of different arrows
public class AnovaCalculator {
    private ScoreList scoreList;
    private Map<Arrow, Double> groupMeans;
    private Map<Arrow, Integer> groupSizes;

    private double grandMean;
    private double sumOfSquaresBetween;
    private double sumOfSquaresWithin;
    private double meanSquaredBetween;
    private double meanSquaredWithin;
    private double fstat;

    private int dfBetween;
    private int dfWithin;

    // Effects: Constructs an AnovaCalculator and calculate all necessary ANOVA
    // statistics.
    public AnovaCalculator(ScoreList scoreList) {
        this.scoreList = scoreList;
        calculate();
    }

    // Requires: scoreList with existing arrow scores
    // Modifies: this groupMeans, this groupSizes
    // Effects: Computes each arrow's average score and the sample size
    private void computeGroupStatistics() {
        groupMeans = new HashMap<>();
        groupSizes = new HashMap<>();
        Set<Arrow> arrows = scoreList.getArrows();
        for (Arrow arrow : arrows) {
            List<Integer> scores = scoreList.getArrowScores(arrow);
            int n = scores.size();
            groupSizes.put(arrow, n);
            double sum = 0;
            for (int s : scores) {
                sum += s;
            }

            double mean = sum / n;
            groupMeans.put(arrow, mean);
        }
    }

    // Requires: groupMeans and groupSizes are not null, at least three arrows with
    // scores
    // Modifies: this grandMean
    // Effects: Calculates the overall average (grand mean) across all arrows
    private void computeGrandMean() {
        double totalSum = 0;
        int totalCount = 0;
        Set<Arrow> arrows = scoreList.getArrows();
        for (Arrow arrow : arrows) {
            double mean = groupMeans.get(arrow);
            int count = groupSizes.get(arrow);
            totalSum += mean * count;
            totalCount += count;
        }
        grandMean = totalSum / totalCount;

    }

    // Requires: groupMeans, groupSizes, and grandMean are not null
    // Modifies: this SSB
    // Effects: Calculates the Sum of Squares Between groups
    private void computeSSB() {
        sumOfSquaresBetween = 0;
        Set<Arrow> arrows = scoreList.getArrows();
        for (Arrow arrow : arrows) {
            int n = groupSizes.get(arrow);
            double mean = groupMeans.get(arrow);
            sumOfSquaresBetween += n * Math.pow(mean - grandMean, 2);
        }
    }

    // Requires: groupMeans is not null
    // Modifies: this SSW
    // Effects: Calculates the Sum of Squares Within groups
    private void computeSSW() {
        sumOfSquaresWithin = 0;
        Set<Arrow> arrows = scoreList.getArrows();
        for (Arrow arrow : arrows) {
            List<Integer> scores = scoreList.getArrowScores(arrow);
            double mean = groupMeans.get(arrow);
            for (int score : scores) {
                sumOfSquaresWithin += Math.pow(score - mean, 2);
            }
        }
    }

    // Requires: groupSizes is not null;
    // Modifies: this dfBetween, this dfWithin
    // Effects: Calculates degrees of freedom:
    // dfBetween = (number of groups - 1)
    // dfWithin = (total number of scores - number of groups)
    private void computeDegreesOfFreedom() {
        int k = groupMeans.size();
        int totalCount = 0;
        for (int count : groupSizes.values()) {
            totalCount += count;
        }
        dfBetween = k - 1;
        dfWithin = totalCount - k;
    }

    // Requires: SSB, SSW, and degrees of freedom. Degrees of freedoms must be > 0
    // Modifies: this MSB, this MSW
    // Effects: Calculates Mean Squares
    private void computeMeanSquares() {
        meanSquaredBetween = sumOfSquaresBetween / dfBetween;
        meanSquaredWithin = sumOfSquaresWithin / dfWithin;
    }

    // Requires: MSW and MSB values. MSW must be > 0
    // Modifies: this F
    // Effects: Calculates the F statistic by dividing MSB by MSW
    private void computeF() {
        if (meanSquaredWithin > 0) {
            fstat = meanSquaredBetween / meanSquaredWithin;
        } else {
            fstat = 0;
        }

    }

    // Requires: A valid scoreList
    // Modifies: this
    // Effects: Performs the ANOVA calculations
    private void calculate() {
        computeGroupStatistics();
        computeGrandMean();
        computeSSB();
        computeSSW();
        computeDegreesOfFreedom();
        computeMeanSquares();
        computeF();
    }

    public double getGrandMean() {
        return grandMean;
    }

    public double getSumOfSquaresBetween() {
        return sumOfSquaresBetween;
    }

    public double getSumOfSquaresWithin() {
        return sumOfSquaresWithin;
    }

    public double getMeanSquaredBetween() {
        return meanSquaredBetween;
    }

    public double getMeanSquaredWithin() {
        return meanSquaredWithin;
    }

    public double getFstat() {
        return fstat;
    }

    public int getDfBetween() {
        return dfBetween;
    }

    public int getDfWithin() {
        return dfWithin;
    }

}
