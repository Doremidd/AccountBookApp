package model;

import java.text.SimpleDateFormat;
import java.util.Date;

//Represent a cost with its date,amount(in dollars) and usage
public class Cost {
    private String date;    //the date when the cost happened
    private int amount;   //the amount of the cost
    private String usage; //the usage for the consumption

    //REQUIRES: amount>=0
    //EFFECTS: construct a cost with given date,amount and usage
    public Cost(String date, int amount, String usage) {
        this.date = date;
        this.amount = amount;
        this.usage = usage;
    }

    public int getamount() {
        return amount;
    }

    public String getdate() {
        return date;
    }

    public String getusage() {
        return usage;
    }


    public String toString() {
        return "[ date:" + date + "," + "amount: " + amount + ", "
                + "usage:" + usage + "]";
    }

}


















