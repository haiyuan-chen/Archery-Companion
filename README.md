
# project-c6i7u
=================================
# Recurve Arrow Curator

## A tool to keep track of recurve bow setup and find the best arrows

**Purpose**  
I want to create a program to help solve a specific real-world problem.

For target archery, arrows need to be custom-made to match a bow's particular draw length and draw weight in order to shoot straight.  
However, arrows are consumable items that inevitably need replacing as they get damaged.

Even when replacing arrows with ones of the exact same model from the same manufacturer, variance still exists within the same batch of arrows. This means some arrows in a batch will perform better or worse compared to the rest of the batch.  
Thus, I want this program to help me calculate the variance between arrows, allowing greater control over this specific variable.
This will be achieved by performing statistical analysis on the scoring data recorded from user inputs. These scoring data would also allow further performance analysis on topics such as performance across different loadouts. 


**Target Audience**  
This program is designed for two groups of people:

1. People who want to curate a set of the best-performing arrows for competition purposes.  
2. People who are in denial and want to gather empirical evidence to (fail to)support their hypothesis that their subpar performance is due to sub-optimal equipment and not their subpar skills. 

**Functions**  
- **Loadout Management**
    - Save your recurve bow setup as a "loadout" of equipment consisting of:

        - **Bow parameters:**
            - Riser and limb length
            - Limb draw weight at 28''
            - Brace height
             - Personal draw length

        - **Arrow parameters:**
          - Individual labels
          - Arrow length and spine
          - Arrow fletching type
          - Individually labeled arrows

- **Score Keeping**
    - Record scores on a virtual target face in sets of 3 
    - visualize the grouping of arrows on the target over the last *x* rounds of shooting.
    - Store and manage historical scores for data analysis

- **Data Analysis:**
    - Calculate variations between arrows:
        - Identify outlier arrows that do not shoot close to others (worst performers).
        - Determine which arrow is the most consistent (best performer).
        - Identify which set of arrows shoot closest to each other (most consistent batch).  



# User Stories
- I want to add specific bow and arrows to a loadout **(complete)**
- I want to be able make changes to a loadout, especially being able to change arrows **(complete)**
- I want to be able to delete the entire loadout **(complete)**
- I want to be able to have multiple loadouts with different bows but possibly same arrows **(complete)**
- I want to keep track of my arrow specifications, mainly shaft length, spine, and tip weight.  **(complete)**
- I want to be able to review my loadout to see the specification of my equipments **(complete)**
- I want to be able to save my loadouts after I have created them **(complete)**
- I want to be able to load my saved loadouts **(complete)**
- I want to record the location of where my arrows hit the target using a virtual target face.  **(complete)**
- I want to compare performance both within and between loadouts **(WIP: Statistic is hard)**
- I want to analyze my performance with a particular set of arrows.  **(WIP: Math is hard)**
- I want to know which arrows (if any) provide the most or least consistent performance.  **(WIP: Statistic is hard)**


# Instructions for End User
**How to Save**
-   Click the "Save" button in the main menu

**How to load**
-   Click the "Load" button in the main menu

**How to manage loadouts**
- Click "Loadout Management" in the main menu
- Click to Select the loadout you want to manage in the list of loadouts in the selection box
- Click on the Create button and follow prompts to create a new loadout
- Click on the Modify button to make changes to either the bow or the arrows in the loadout
- Click on the Delete button after selecting a loadout to delete it from the list
- Click on the View button to view details about the selected loadout
- Click on the Return button to close the Loadout Management window

**How to Record Scores**
- Click the "Record Scores" button in the main menu
- This will bring up an interactive target face
- Follow the instructions given by the top right panel to record scores from each arrows

# Phase 4: Task 2

Fri Mar 28 13:45:37 PDT 2025
Bow: bb added to the laodout: l1

Fri Mar 28 13:45:37 PDT 2025
Arrow Etwo added to the loadout: l1

Fri Mar 28 13:45:37 PDT 2025
Loadout: l1 added to the loadout list.

Fri Mar 28 13:45:37 PDT 2025
6 points recorded for arrow: Etwo

Fri Mar 28 13:45:37 PDT 2025
10 points recorded for arrow: Etwo

Fri Mar 28 13:45:37 PDT 2025
7 points recorded for arrow: Etwo

Fri Mar 28 13:45:37 PDT 2025
2 points recorded for arrow: Etwo

Fri Mar 28 13:45:37 PDT 2025
1 points recorded for arrow: Etwo

Fri Mar 28 13:46:06 PDT 2025
Bow: sample bow added to the laodout: sample loadout

Fri Mar 28 13:46:26 PDT 2025
Arrow arrow 1 added to the loadout: sample loadout

Fri Mar 28 13:46:26 PDT 2025
Arrow arrow 2 added to the loadout: sample loadout

Fri Mar 28 13:46:26 PDT 2025
Arrow arrow 3 added to the loadout: sample loadout

Fri Mar 28 13:46:26 PDT 2025
Loadout: sample loadout added to the loadout list.

Fri Mar 28 13:46:48 PDT 2025
Arrow a1 added to the loadout: l1

Fri Mar 28 13:46:48 PDT 2025
Arrow a2 added to the loadout: l1

Fri Mar 28 13:46:48 PDT 2025
Arrow a3 added to the loadout: l1

Fri Mar 28 13:46:56 PDT 2025
8 points recorded for arrow: arrow 1

Fri Mar 28 13:46:57 PDT 2025
6 points recorded for arrow: arrow 1

Fri Mar 28 13:46:57 PDT 2025
4 points recorded for arrow: arrow 1

Fri Mar 28 13:47:00 PDT 2025
6 points recorded for arrow: arrow 2

Fri Mar 28 13:47:01 PDT 2025
10 points recorded for arrow: arrow 2

Fri Mar 28 13:47:04 PDT 2025
0 points recorded for arrow: arrow 2

Fri Mar 28 13:47:06 PDT 2025
1 points recorded for arrow: arrow 3

Fri Mar 28 13:47:08 PDT 2025
4 points recorded for arrow: arrow 3

Fri Mar 28 13:47:09 PDT 2025
6 points recorded for arrow: arrow 3

Fri Mar 28 13:47:13 PDT 2025
6 points recorded for arrow: a3

Fri Mar 28 13:47:15 PDT 2025
5 points recorded for arrow: a2

Fri Mar 28 13:47:35 PDT 2025
Bow: b added to the laodout: l1

# Phase 4: Task 3

**Reflection**

If I had more time there are a few things I would like to do to reduce coupling between classes and enforce stricter single responsibility principles. In the current design, LoadoutList class is called by CuratorGUI class, but it is also called upon by ScoreWindow and LoadoutWindow class. Since ScoreWindow and LoadoutWindow are already called upon by the CuratorGUI class to create the user interface, ideally there should only need one call from CuratorGUI to access and pass down necessary information.

In my current implementation, ArrowCoordinate class took on additional role of calculating Arrow scores based on the coordinates. This functionality should be off-loaded to another class, and have ArrowCoordinate focus on a singular purpose. Relatedly, ArrowCoordinate is called by both ScoreWindow and TargetPanel. I would like to decouple this relationship and instead have something like

ScoreWindow --> TargetPanel --> ArrowCoordinate + ArrowScore (decoupled new class)

Finally, due to time constraints, ScoreWindow and LoadoutWindow classes have too many different functionalities. In the current implementation, They not only display the new windows but also perform all of the UI actions, such as adding load outs, changing arrows, recording scores, etc. These two classes would be my main focus in refactoring various functionalities into distinct classes, for example a LoadoutManagement class with to handle UI actions called by the GUI. 
