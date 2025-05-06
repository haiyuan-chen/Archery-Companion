# Archery Companion

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
- I want to compare performance both within and between loadouts **(WIP: better implemented using R)**
- I want to analyze my performance with a particular set of arrows.  **(WIP)**
- I want to know which arrows (if any) provide the most or least consistent performance.  **(WIP)**


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