package persistence;

import model.Cost;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCost(String date, Double amount, String usage, Cost cost){
        assertEquals(date,cost.getdate());
        assertEquals(amount,cost.getamount());
        assertEquals(usage,cost.getusage());
    }

}
