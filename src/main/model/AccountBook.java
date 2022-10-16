package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//An accountbook which can show the costs on specific date,total costs and list of costs
public class AccountBook {
    private ArrayList<Cost> accountbook;

    public AccountBook() {
        accountbook = new ArrayList<>();
    }

    //EFFECTS:add a cost to the accountbook list
    public void addcost(Cost c) {
        accountbook.add(c);
    }

    //EFFECTS: get the total amount of cost up till now
    public int getCost() {
        int totalamount = 0;
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
    public ArrayList<Cost> clearCost(String givendate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newgivendate = sdf.parse(givendate);
            for (Cost c : accountbook) {
                String date = c.getdate();
                Date newdate = sdf.parse(date);
                if (newdate.before(newgivendate)) {
                    accountbook.remove(c);
                }
            }
        } catch (ParseException e) {
            System.out.println("Wrong date format");
        }
        return accountbook;
    }

    public int size() {
        return accountbook.size();
    }

    public boolean contains(Cost c) {
        return accountbook.contains(c);
    }


}

































