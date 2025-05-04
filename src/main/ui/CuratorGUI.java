package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import model.Event;
import model.EventLog;
import model.LoadoutList;
import model.ScoreList;
import persistence.FileSaver;
import persistence.FileLoader;

// CITATION: 210 class project examples, https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application, 
// various other stack overflow posts

// Main window for the graphical interface
public class CuratorGUI extends JFrame implements ActionListener, WindowListener {

    private LoadoutList loadoutList;
    private ScoreList scoreList;
    private FileSaver saver;
    private FileLoader loader;

    // Constructs a new CuratorGUI and initializes the user interface.
    public CuratorGUI() {
        super("Curator App");
        loadoutList = new LoadoutList();
        scoreList = new ScoreList();
        saver = new FileSaver("curatorData");
        loader = new FileLoader("curatorData");
        initializeGUI();
    }

    // MODIFIES: this
    // EFFECTS: sets up windows and adds the main panel
    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    // EFFECTS: creates and returns the main panel with menu buttons
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        addMenuButtons(panel);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: adds menu buttons to the given panel
    private void addMenuButtons(JPanel panel) {
        String[] options = { "Loadout Management", "Record Scores", "Save", "Load", "Exit" };
        for (String option : options) {
            JButton button = new JButton(option);
            button.setActionCommand(option);
            button.addActionListener(this);
            panel.add(button);
        }
    }

    // EFFECTS: handles action events based on the button pressed
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Loadout Management".equals(command)) {
            openLoadoutWindow();
        } else if ("Record Scores".equals(command)) {
            openScoreWindow();
        } else if ("Save".equals(command)) {
            saveLoadouts();
        } else if ("Load".equals(command)) {
            loadLoadouts();
        } else if ("Exit".equals(command)) {
            windowClosed(null);
            exitApp();
        }
    }

    // EFFECTS: opens the loadout window, passing the persistent loadoutList
    private void openLoadoutWindow() {
        new LoadoutWindow(loadoutList);
    }

    // EFFECTS: opens the score window (not detailed here)
    private void openScoreWindow() {
        new ScoreWindow(loadoutList, scoreList);
    }

    // EFFECTS: saves the loadout list to file
    private void saveLoadouts() {
        try {
            saver.open();
            saver.write(loadoutList, scoreList);
            saver.close();
            JOptionPane.showMessageDialog(this, "Loadouts saved.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving loadouts.");
        }
    }

    // MODIFIES: this.loadoutList
    // EFFECTS: loads the loadout list from file and updates the model
    private void loadLoadouts() {
        try {
            loadoutList = loader.read();
            scoreList = loader.readScores();
            JOptionPane.showMessageDialog(this, "Loadouts loaded.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading loadouts.");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Logged events: ");
        for (Event ev: EventLog.getInstance()) {
            System.out.println(ev.toString() + "\n");

        }
    }

    // EFFECTS: exits the application
    private void exitApp() {
        System.exit(0);
    }


    // Unused interface methods
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }


    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
