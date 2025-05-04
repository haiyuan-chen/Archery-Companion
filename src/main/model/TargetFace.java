package model;

// A static 1000x1000 dimension for the target face image
public class TargetFace {
    protected static final int CENTER_X = 500;
    protected static final int CENTER_Y = 500;
    protected static final double[] RING_BOUNDARIES = {
            90.0, 135.0, 185.0, 225.0,
            270.0, 320.0, 363.0, 405.0, 453.0, 500.0
    };
    protected static final int[] SCORES = {
            10, 9, 8, 7,
            6, 5, 4, 3, 2, 1
    };

    // EFFECTS: calculate the score of the shot for with the given x, y coordinate
    public static int calculateScore(int x, int y) {
        double dist = distanceFromCenter(x, y);
        for (int i = 0; i < RING_BOUNDARIES.length; i++) {
            if (dist <= RING_BOUNDARIES[i]) {
                return SCORES[i];
            }
        }
        return 0;
    }

    // EFFECTS: helper method to calculate distance from the center point of the
    // target face
    public static double distanceFromCenter(int x, int y) {
        double dx = x - CENTER_X;
        double dy = y - CENTER_Y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
