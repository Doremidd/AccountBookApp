package ui;

import model.AccountBook;
import model.Cost;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//The AccountBook application
public class AccountBookApp {
    private AccountBook accountbook;
    private Scanner scanner;


    //initialize accountbook and run the application
    //Source:https://github.students.cs.ubc.ca/CPSC210/TellerApp
    //Learn how to launch the app
    public AccountBookApp() {
        accountbook = new AccountBook();
        scanner = new Scanner(System.in);
        runAccountBook();
    }

    //MODIFIES:this
    //EFFECTS:deal with user input
    //Source:https://github.students.cs.ubc.ca/CPSC210/TellerApp
    public void runAccountBook() {
        boolean keepgoing = true;
        String command = null;

        while (keepgoing) {
            displayMenu();
            command = scanner.next();
            if (command.equals("[5]")) {
                keepgoing = false;
            } else {
                processcommand(command);
            }
        }
        System.out.println("\nThank you. Goodbye!");
    }

    //EFFECTS:show the menu of options to user
    //Source:https://github.students.cs.ubc.ca/CPSC210/TellerApp
    public void displayMenu() {
        System.out.println("\n Please select from:");
        System.out.println("\t [1]:Add cost");
        System.out.println("\t [2]:Get total cost");
        System.out.println("\t [3]:View list of costs");
        System.out.println("\t [4]:Clear costs");
        System.out.println("\t [5]:Save and Quit");
    }


    //EFFECTS: process the user command
    //Source:https://github.students.cs.ubc.ca/CPSC210/TellerApp
    public void processcommand(String command) {
        if (command.equals("[1]")) {
            addcost();
        } else if (command.equals("[2]")) {
            getcost();
        } else if (command.equals("[3]")) {
            viewcost();
        } else if (command.equals("[4]")) {
            clearcost();
        } else {
            System.out.println("Selection not valid");
        }
    }


    //MODIFIES:this
    //EFFECTS:prompt user to add cost of given date,usage and amount to the accountbook
    public void addcost() {
        System.out.println("Please enter cost date:");
        System.out.println("Date format should be yyyy-MM-dd, e.g. '2022-10-15.'");
        scanner.nextLine();
        String date = scanner.nextLine();
        if (validdateformat(date)) {
            System.out.println("Please enter cost usage:");
            String usage = scanner.nextLine();
            System.out.println("Please enter cost amount:");
            double amount = scanner.nextDouble();
            Cost cost = new Cost(date,amount,usage);
            accountbook.addcost(cost);
        } else {
            System.out.println("Please follow the required date format");
        }
    }


   //EFFECTS: show user the total amount of costs up till now
    public void getcost() {
        double totalcost = accountbook.getCost();
        System.out.println("The total amount of your costs is:" + totalcost);
    }

    //EFFECTS:show user the list of costs
    public void viewcost() {
        System.out.println("Here is the list of your costs:\n" + accountbook.showCost());
    }

    //prompt user to enter a date and clear all costs before it
    public void clearcost() {
        System.out.println("Enter the date before which you would like to clear all costs:");
        System.out.println("Date format should be yyyy-MM-dd, e.g. 2022-10-15.");
        String givendate = scanner.next();
        accountbook.clearCost(givendate);
        System.out.println(accountbook.showCost());
    }

    //EFFECTS:produce true if the given date is in the format "yyyy-MM-dd",otherwise false
    //(Here 2022-10-05 and 2022-10-5 are both counted as valid date format)
    //Source:https://blog.csdn.net/X2017_8_26/article/details/78848796
    //Learn how to determine if the give date String is in the required format
    public boolean validdateformat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}








