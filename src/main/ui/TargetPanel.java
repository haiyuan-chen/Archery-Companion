package ui;

import model.ArrowCoordinate;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// CITATION: https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
//           https://stackoverflow.com/questions/35299786/draw-circle-on-jpanel-after-mouse-click
//           https://stackoverflow.com/questions/72085836/draw-point-on-mouse-click-with-java-swing
//           https://stackoverflow.com/questions/12396066/how-to-get-location-of-a-mouse-click-relative-to-a-swing-window

// A panel containing image of an archery target face
public class TargetPanel extends JPanel {
    private Image targetImage;
    private List<ArrowCoordinate> coordinates;
    protected static int WIDTH = 1000;
    protected static int HEIGHT = 1000;

    // EFFECTS: Constructs a panel with the target face image and a list of
    // ArrowCoordinate
    public TargetPanel() {
        coordinates = new ArrayList<>();
        loadTargetImage();
    }

    // EFFECTS: helper method to read target face image
    private void loadTargetImage() {
        try {
            targetImage = ImageIO.read(new File("src/main/ui/targetface.png"));
        } catch (Exception e) {
            targetImage = null;
        }
    }

    // MODIFIES: this
    // EFFECTS: set the default size of the image
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    // REQUIRES: target face image exists
    // MODIFIES: this
    // EFFECTS: Draws and paint coloured dots for each ArrowCoordinate on the target
    // face
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (targetImage != null) {
            g.drawImage(targetImage, 0, 0, WIDTH, HEIGHT, this);
        }
        g.setColor(Color.GREEN);
        for (ArrowCoordinate ac : coordinates) {
            int dotSize = 8;
            int cx = ac.getXcoord() - dotSize / 2;
            int cy = ac.getYcoord() - dotSize / 2;
            g.fillOval(cx, cy, dotSize, dotSize);
        }
    }

    // MODIFIES: this
    // EFFECTS: replaces the current coordinates list and repaint the dots.
    public void setCoordinates(List<ArrowCoordinate> coords) {
        coordinates.clear();
        coordinates.addAll(coords);
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: adds a new arrow coordinate and repaint the dots.
    public void addCoordinate(ArrowCoordinate ac) {
        coordinates.add(ac);
        repaint();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
