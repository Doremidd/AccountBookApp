package model;

import java.text.SimpleDateFormat;
import java.util.Date;

//Represent a cost with its date,amount(in dollars) and usage
public class Cost {
    private String date;    //the date when the cost happened
    private double amount;   //the amount of the cost
    private String usage; //the usage for the consumption

    //REQUIRES: amount>=0
    //EFFECTS: construct a cost with given date,amount and usage
    public Cost(String date, double amount, String usage) {
        this.date = date;
        this.amount = amount;
        this.usage = usage;
    }

    public double getamount() {
        String string = String.format("%.2f",amount);
        double newamount = Double.parseDouble(string);
        return newamount;
    }

    public String getdate() {
        return date;
    }

    public String getusage() {
        return usage;
    }


    public String toString() {
        String amountStr = String.format("%.2f", amount);  // get amount to 2 decimal places as a string
        return "date:" + date + "," + "amount: " + amountStr + ", "
                + "usage:" + usage;
    }

}


















