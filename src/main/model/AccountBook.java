package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//An accountbook which can show the costs on specific date,total costs and list of costs
public class AccountBook {
    private ArrayList<Cost> accountbook;

    //EFFECTS:construct a new AccountBook consists of list of costs
    public AccountBook() {
        accountbook = new ArrayList<>();
    }

    //MODIFIES:this
    //EFFECTS:add a cost to the accountbook list
    public void addcost(Cost c) {
        accountbook.add(c);
    }

    //EFFECTS: get the total amount of cost up till now to 2 decimal places
    public double getCost() {
        double totalamount = 0.00;
        for (Cost c : accountbook) {
            totalamount = totalamount + c.getamount();
        }
        return totalamount;
    }

    //EFFECTS: show a list of costs up till now
    public ArrayList<String> showCost() {
        ArrayList<String> costlist = new ArrayList<>();
        for (Cost c : accountbook) {
            String coststr = c.toString();
            costlist.add(coststr);
        }
        return costlist;
    }

    //EFFECTS: For a given date, all the costs before that day(itself not included) will be deleted
    public boolean clearCost(String givendate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Cost> toRemove = new ArrayList<>();
        try {
            Date newgivendate = sdf.parse(givendate);
            for (Cost c : accountbook) {
                String date = c.getdate();
                Date newdate = sdf.parse(date);
                if (newdate.before(newgivendate)) {
                    toRemove.add(c);
                }
            }
        } catch (ParseException e) {
            return false;
        }
        return accountbook.removeAll(toRemove);
    }

    public int size() {
        return accountbook.size();
    }

    public boolean contains(Cost c) {
        return accountbook.contains(c);
    }


}

































