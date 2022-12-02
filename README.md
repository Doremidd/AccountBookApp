# *I'm on budget!*

### ***An app help managing your consumptions***

**User Story:**
- *As a user,I want to be able to add my cost in different types of usage to the accountbook list*
- *As a user,I want to be able to see the list of my costs*
- *As a user,I want be able to get the amount of my total costs up till now*
- *As a user,I want to be able to clear all costs before a specific date*
- *As a user,when I select the quit option,I want to save my work automatically when I quit*
- *As a user,I want to be able to reload the accountbook from file and continue exactly where it was left off last time*

**Instructions for Grader**
- You can generate the first required event related to adding Xs to a Y by entering the date,amount and usage of a 
  cost in the corresponding column and then click the "Add" button.
- You can generate the second required event related to adding Xs to a Y by entering the date before which  you want 
  all costs be deleted and then click the "Clear" button.
- You can locate my visual component by looking at the image added to the Jlabel. 
- You can save the state of my application by clicking the "Save" button.
- You can reload the state of my application by clicking the "Load" button.

**Phase 4: Task 2:**
- *Thu Dec 01 19:01:38 PST 2022  100.0 is added to Selina's AccountBook*
- *Thu Dec 01 19:02:05 PST 2022  30.0 is added to Selina's AccountBook*
- *Thu Dec 01 19:02:17 PST 2022  Costs before 2022-11-18 are cleared*

**Phase 4: Task 3:**
- *In the AccountBookGUI class,there are many duplicate codes in those loading picture methods,it's necessary to 
  extract the duplicated parts into a different method, and call it each time loading a new picture*
- *AccountBookGUI class and AccountBookApp class both use load and save methods and contain fields of JsonReader and 
  JsonWriter. Therefore, it's necessary to build a new abstract class with the load and save methods, then 
  AccountBookGUI and AccountBookApp can just extends the abstract class to use these methods.*

