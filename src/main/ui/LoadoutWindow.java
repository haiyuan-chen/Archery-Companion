package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import model.Loadout;
import model.Bow;
import model.Arrow;
import model.LoadoutList;

// CITATION: 210 class project examples, https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application, 
// various other stack overflow posts

// Window for managing loadouts (create, modify, delete, view)
public class LoadoutWindow extends JFrame implements ActionListener {
    private LoadoutList loadoutList;
    private DefaultListModel<Loadout> listModel;
    private JList<Loadout> loadoutJList;
    private JButton createButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JButton returnButton;

    // Constructs a new LoadoutWindow using the given persistent loadout list.
    public LoadoutWindow(LoadoutList loadoutList) {
        super("Loadout Management");
        this.loadoutList = loadoutList;
        initializeComponents();
    }

    // MODIFIES: this
    // EFFECTS: initializes window properties and components
    private void initializeComponents() {
        setSize(600, 400);
        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();
        refreshList();
        loadoutJList = new JList<>(listModel);
        add(new JScrollPane(loadoutJList), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: refreshes the list model from the persistent loadoutList
    private void refreshList() {
        listModel.clear();
        List<Loadout> loads = loadoutList.getLoadouts();
        for (Loadout l : loads) {
            listModel.addElement(l);
        }
    }

    // EFFECTS: creates and returns a panel with action buttons
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 5, 5, 5));
        createButton = new JButton("Create");
        modifyButton = new JButton("Modify");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");
        returnButton = new JButton("Return");

        createButton.setActionCommand("Create");
        modifyButton.setActionCommand("Modify");
        deleteButton.setActionCommand("Delete");
        viewButton.setActionCommand("View");
        returnButton.setActionCommand("Return");

        createButton.addActionListener(this);
        modifyButton.addActionListener(this);
        deleteButton.addActionListener(this);
        viewButton.addActionListener(this);
        returnButton.addActionListener(this);

        panel.add(createButton);
        panel.add(modifyButton);
        panel.add(deleteButton);
        panel.add(viewButton);
        panel.add(returnButton);
        return panel;
    }

    // EFFECTS: handles action events for loadouts and refreshes the list
    // model
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Create".equals(command)) {
            createLoadout();
        } else if ("Modify".equals(command)) {
            modifyLoadout();
        } else if ("Delete".equals(command)) {
            deleteLoadout();
        } else if ("View".equals(command)) {
            viewLoadout();
        } else if ("Return".equals(command)) {
            dispose();
        }
        refreshList();
    }

    // MODIFIES: loadoutList
    // EFFECTS: prompts user to create a new loadout and adds it to loadoutList
    private void createLoadout() {
        String name = JOptionPane.showInputDialog(this, "Enter loadout name:");
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        Loadout loadout = new Loadout(name.trim());
        Bow bow = createBowMenu();
        if (bow != null) {
            loadout.setBow(bow);
        }
        List<Arrow> arrows = createArrowMenu();
        if (arrows != null) {
            for (Arrow arrow : arrows) {
                loadout.addArrow(arrow);
            }
        }
        loadoutList.addLoadout(loadout);
        JOptionPane.showMessageDialog(this, "Loadout created successfully.");
    }

    // EFFECTS: prompts user to enter bow details and returns a Bow object
    private Bow createBowMenu() {
        try {
            String name = JOptionPane.showInputDialog(this, "Enter bow name:");
            if (name == null || name.trim().isEmpty()) {
                return null;
            }
            int length = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter bow length (in inches):"));
            int drawWeight = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter draw weight (in pounds):"));
            double braceHeight = Double.parseDouble(JOptionPane
                    .showInputDialog(this, "Enter brace height (in inches):"));
            int drawLength = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter draw length (in inches):"));
            return new Bow(name.trim(), length, drawWeight, braceHeight, drawLength);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for bow. Try again!");
            return null;
        }
    }

    // EFFECTS: prompts user to enter arrow details and returns a list of Arrow
    // objects
    private List<Arrow> createArrowMenu() {
        try {
            int count = Integer.parseInt(JOptionPane.showInputDialog(this, "How many arrows to add?"));
            if (count <= 0) {
                return null;
            }
            int length = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter arrow length (in inches):"));
            int spine = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter arrow spine rating:"));
            int weight = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter arrow weight (in grains):"));
            List<String> labels = arrowNamer(count);
            List<Arrow> arrows = new ArrayList<>();
            for (String label : labels) {
                arrows.add(new Arrow(length, spine, weight, label));
            }
            return arrows;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for arrows. Try again!");
            return null;
        }
    }

    // EFFECTS: prompts user to label the arrows and returns the list of labels
    private List<String> arrowNamer(int count) {
        List<String> labels = new java.util.ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String label = JOptionPane.showInputDialog(this, "Enter label for arrow " + i + ":");
            if (label == null || label.trim().isEmpty()) {
                label = "Arrow" + i;
            }
            labels.add(label.trim());
        }
        return labels;
    }

    // MODIFIES: loadoutList
    // EFFECTS: allows user to modify the selected loadout's bow or arrows
    private void modifyLoadout() {
        Loadout loadout = loadoutJList.getSelectedValue();
        if (loadout == null) {
            JOptionPane.showMessageDialog(this, "Select a loadout to modify.");
            return;
        }
        String[] options = { "Change Bow", "Change Arrows", "Cancel" };
        int choice = JOptionPane.showOptionDialog(this, "What do you want to modify?", "Modify Loadout",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            Bow bow = createBowMenu();
            if (bow != null) {
                loadout.setBow(bow);
                JOptionPane.showMessageDialog(this, "Bow updated.");
            }
        } else if (choice == 1) {
            arrowChanger(loadout);
        }
    }

    // MODIFIES: this loadoutList
    // EFFECTS: allows user to change the arrows in the loadout either by replacing
    // a single arrow or all of the arrows
    private void arrowChanger(Loadout loadout) {
        String[] options = { "Different set", "Replace an arrow", "Cancel" };
        int choice = JOptionPane.showOptionDialog(this, "Choose arrow modification:", "Modify Arrows",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            List<Arrow> arrows = createArrowMenu();
            if (arrows != null) {
                loadout.getArrows().clear();
                for (Arrow arrow : arrows) {
                    loadout.addArrow(arrow);
                }
                JOptionPane.showMessageDialog(this, "Arrows replaced.");
            }
        } else if (choice == 1) {
            arrowReplacer(loadout);
        }
    }

    // EFFECTS: provides drop-down list of arrow labels from the given loadout,
    // updates the selected arrow's label if valid label is provided.
    private void arrowReplacer(Loadout loadout) {
        java.util.List<Arrow> arrows = loadout.getArrows();
        if (arrows.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No arrows available to replace.");
            return;
        }
        String[] arrowOptions = getArrowOptions(loadout);
        String selected = (String) JOptionPane.showInputDialog(this, "Select arrow to replace:", "Replace Arrow",
                JOptionPane.PLAIN_MESSAGE, null, arrowOptions, arrowOptions[0]);
        if (selected == null) {
            return;
        }
        Arrow arrow = loadout.getArrow(selected);
        if (arrow == null) {
            JOptionPane.showMessageDialog(this, "No arrow found with that label.");
            return;
        }
        String newLabel = JOptionPane.showInputDialog(this, "Enter new label:");
        if (newLabel != null && !newLabel.trim().isEmpty()) {
            arrow.setName(newLabel.trim());
            JOptionPane.showMessageDialog(this, "Arrow label updated.");
        }
    }

    // EFFECTS: returns an array of arrow labels from the given loadout
    private String[] getArrowOptions(Loadout loadout) {
        java.util.List<Arrow> arrows = loadout.getArrows();
        String[] options = new String[arrows.size()];
        for (int i = 0; i < arrows.size(); i++) {
            options[i] = arrows.get(i).getName();
        }
        return options;
    }

    // MODIFIES: loadoutList
    // EFFECTS: deletes the selected loadout from the persistent loadoutList
    private void deleteLoadout() {
        Loadout loadout = loadoutJList.getSelectedValue();
        if (loadout == null) {
            JOptionPane.showMessageDialog(this, "Select a loadout to delete.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Delete loadout: " + loadout.getName() + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            loadoutList.removeLoadout(loadout);
        }
    }

    // EFFECTS: displays details of the selected loadout
    private void viewLoadout() {
        Loadout loadout = loadoutJList.getSelectedValue();
        if (loadout == null) {
            JOptionPane.showMessageDialog(this, "Select a loadout to view.");
            return;
        }
        StringBuilder details = new StringBuilder();
        details.append("Loadout: ").append(loadout.getName()).append("\n");
        if (loadout.getBow() != null) {
            details.append("Bow:\n").append(loadout.getBow().showAllStat()).append("\n");
        }
        if (loadout.getArrows() != null && !loadout.getArrows().isEmpty()) {
            details.append("Arrows:\n");
            for (Arrow a : loadout.getArrows()) {
                details.append(a.showArrowStat()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, details.toString());
    }
}
