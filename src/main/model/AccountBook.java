package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//An accountbook which can show the costs on specific date,total costs and list of costs
public class AccountBook implements Writable {
    private String name;
    private ArrayList<Cost> costs;

    //EFFECTS:construct a new AccountBook consists of list of costs
    public AccountBook(String name) {
        this.name = name;
        costs = new ArrayList<>();
    }

    //MODIFIES:this
    //EFFECTS:add a cost to the accountbook list
    public void addcost(Cost c) {
        costs.add(c);
    }

    //EFFECTS: get the total amount of cost up till now to 2 decimal places
    public double getCost() {
        double totalamount = 0.00;
        for (Cost c : costs) {
            totalamount = totalamount + c.getamount();
        }
        return totalamount;
    }

    //EFFECTS: show a list of costs up till now
    public ArrayList<String> showCost() {
        ArrayList<String> costlist = new ArrayList<>();
        for (Cost c : costs) {
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
            for (Cost c : costs) {
                String date = c.getdate();
                Date newdate = sdf.parse(date);
                if (newdate.before(newgivendate)) {
                    toRemove.add(c);
                }
            }
        } catch (ParseException e) {
            return false;
        }
        return costs.removeAll(toRemove);
    }

    public int size() {
        return costs.size();
    }

    public boolean contains(Cost c) {
        return costs.contains(c);
    }

    public String getname() {
        return name;
    }

    // EFFECTS: returns an unmodifiable list of costs in the accountbook
    public List<Cost> getcosts() {
        return Collections.unmodifiableList(costs);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("costs", costsToJson());
        return json;
    }

    private JSONArray costsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cost c : costs) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;

    }

}

































