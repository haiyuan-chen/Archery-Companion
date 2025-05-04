package ui;

import model.Arrow;
import model.ArrowCoordinate;
import model.Loadout;
import model.LoadoutList;
import model.ScoreList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// CITATION: https://stackoverflow.com/questions/20487329/whats-the-best-way-to-organize-jpanels-in-a-list
//           https://stackoverflow.com/questions/7107939/add-jpanel-to-jlist
//           https://stackoverflow.com/questions/13136826/defaultlistmodel-in-java
//           https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html
// Graphical interface for score recording
public class ScoreWindow extends JFrame implements MouseListener {

    private LoadoutList loadoutList;
    private ScoreList scoreList;
    private TargetPanel targetPanel;
    private DefaultListModel<Loadout> loadouts;
    private JList<Loadout> loadoutJList;
    private DefaultListModel<Arrow> arrows;
    private JList<Arrow> arrowJList;
    private JTextArea scoreTextArea;
    private JTextArea infoTextArea;
    private JButton closeButton;
    private List<ArrowCoordinate> shotHistory;
    protected static int WIDTH = 1440;
    protected static int HEIGHT = 1080;

    // EFFECTS: Consctructs a score window with list of loadouts and lists of scores
    public ScoreWindow(LoadoutList loadoutList, ScoreList scoreList) {
        super("Target Scoring");
        this.loadoutList = loadoutList;
        this.scoreList = scoreList;
        shotHistory = new ArrayList<>();
        initializeComponents();
    }

    // MODIFIES: this
    // EFFECTS: sets up the layout, model lists, side panel, target panel, and close
    // button.
    private void initializeComponents() {
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        createTargetPanel();
        createSidePanel();
        createBottomPanel();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: Helper method to create target face panel for the main window
    private void createTargetPanel() {
        targetPanel = new TargetPanel();
        JPanel targetContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        targetContainer.add(targetPanel);
        targetContainer.setPreferredSize(new Dimension(1260, 860));
        add(targetContainer, BorderLayout.CENTER);
        targetPanel.addMouseListener(this);
    }

    // EFFECTS: Helper method to create side panels for the main window
    private void createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(300, 900));
        sidePanel.add(createInfoPanel());
        sidePanel.add(createLoadoutPanel());
        sidePanel.add(createArrowPanel());
        sidePanel.add(createScorePanel());
        add(sidePanel, BorderLayout.EAST);
    }

    // EFFECTS: Helper method to create bottom close button panel for the main
    // window
    private void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout());
        closeButton = new JButton("Close");
        closeButton.addActionListener(_ -> dispose());
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Helper method to create an information panel with instructions.
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        infoTextArea = new JTextArea("Instructions:\n"
                + "- Select a loadout and its arrow from the lists\n"
                + "- Left-click on target to place arrow\n"
                + "- Right-click to undo previous score\n"
                + "- Click anywhere outside of the target face if you missed the target");
        infoTextArea.setEditable(false);
        infoTextArea.setLineWrap(true);
        panel.add(new JLabel("Instructions:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(infoTextArea), BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(300, 150));
        return panel;
    }

    // EFFECTS: Helper method to create a panel for the loadout list.
    private JPanel createLoadoutPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Loadouts:"), BorderLayout.NORTH);
        loadouts = new DefaultListModel<>();
        loadLoadout();
        loadoutJList = new JList<>(loadouts);
        loadoutJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadoutJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Loadout selected = loadoutJList.getSelectedValue();
                if (selected != null) {
                    loadArrowList(selected);
                }
            }
        });
        panel.add(new JScrollPane(loadoutJList), BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(300, 200));
        return panel;
    }

    // EFFECTS: Helper method to create a panel for the arrow list.
    private JPanel createArrowPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Arrows:"), BorderLayout.NORTH);
        arrows = new DefaultListModel<>();
        arrowJList = new JList<>(arrows);
        arrowJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(arrowJList), BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(300, 200));
        return panel;
    }

    // EFFECTS: Helper method to create a panel that displays scores for each arrow.
    private JPanel createScorePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Scores:"), BorderLayout.NORTH);
        scoreTextArea = new JTextArea();
        scoreTextArea.setEditable(false);
        panel.add(new JScrollPane(scoreTextArea), BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(300, 250));
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: loads all loadouts from loadoutList into the list model.
    private void loadLoadout() {
        loadouts.clear();
        for (Loadout l : loadoutList.getLoadouts()) {
            loadouts.addElement(l);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the arrows from the selected loadout into the arrow list
    // model.
    private void loadArrowList(Loadout loadout) {
        arrows.clear();
        for (Arrow a : loadout.getArrows()) {
            arrows.addElement(a);
        }
    }

    // EFFECTS: updates the score display text area based on the current shot
    // history.
    private void updateScoreDisplay() {
        Map<Arrow, List<Integer>> scoreMap = new HashMap<>();
        for (ArrowCoordinate ac : shotHistory) {
            scoreMap.computeIfAbsent(ac.getArrow(), _ -> new ArrayList<>()).add(ac.getScore());
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Arrow, List<Integer>> entry : scoreMap.entrySet()) {
            sb.append(entry.getKey().getName()).append(": ");
            for (Integer s : entry.getValue()) {
                sb.append(s).append(", ");
            }
            if (!entry.getValue().isEmpty()) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("\n");
        }
        scoreTextArea.setText(sb.toString());
    }

    // MODIFIES: this scoreList
    // EFFECTS: clears scoreList and recalculates scores from shotHistory.
    private void updateScoreList() {
        scoreList.clear();
        for (ArrowCoordinate ac : shotHistory) {
            scoreList.addScoresToList(ac.getArrow(), ac.getScore());
        }
    }

    // EFFECTS: On left mouse button, adds a shot, on right mouse button, undo the
    // last shot.
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            addShot(e.getX(), e.getY());
        } else if (SwingUtilities.isRightMouseButton(e)) {
            undoLastShot();
        }
    }

    // MODIFIES: this, targetPanel
    // EFFECTS: creates an ArrowCoordinate from the clicked coordinates for the
    // selected arrow,
    // then adds it to shotHistory, updates scoreList and targetPanel, and updates
    // the
    // score list panel.
    private void addShot(int x, int y) {
        Arrow selectedArrow = arrowJList.getSelectedValue();
        if (selectedArrow == null) {
            JOptionPane.showMessageDialog(this, "Select an arrow from a loadout first.");
            return;
        }
        ArrowCoordinate ac = new ArrowCoordinate(selectedArrow, x, y);
        shotHistory.add(ac);
        scoreList.addScoresToList(selectedArrow, ac.getScore());
        targetPanel.addCoordinate(ac);
        updateScoreDisplay();
    }

    // MODIFIES: shotHistory, scoreList, targetPanel
    // EFFECTS: undoes the most recent shot, recalculates scores, updates the
    // targetPanel and score display.
    private void undoLastShot() {
        if (shotHistory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No shots to undo.");
            return;
        }
        shotHistory.remove(shotHistory.size() - 1);
        updateScoreList();
        targetPanel.setCoordinates(shotHistory);
        updateScoreDisplay();
    }

    // unused and unimplemented MouseListener methods
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
