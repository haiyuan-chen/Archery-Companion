package model;


// Represents the position of the arrow on the target face and its associated score
public class ArrowCoordinate {
    private Arrow arrow;
    private int xcoord;
    private int ycoord;
    private int score;

    // Constructs an arrow coordinate with an arrow and its x and y position on the
    // target face. Computes the score according to the position
    public ArrowCoordinate(Arrow arrow, int x, int y) {
        this.arrow = arrow;
        this.xcoord = x;
        this.ycoord = y;
        this.score = TargetFace.calculateScore(x, y);
    }

    public Arrow getArrow() {
        return this.arrow;
    }

    public int getXcoord() {
        return this.xcoord;
    }

    public int getYcoord() {
        return this.ycoord;
    }

    public int getScore() {
        return this.score;
    }
}
