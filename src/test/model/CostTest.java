package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Test class for Cost class
public class CostTest {
    private Cost testcost;

    @BeforeEach
    public void setup(){
        testcost = new Cost("2022-10-01",200.1234,"buy clothes");
    }

    @Test
    public void testconstructor(){
        assertEquals(testcost.getdate(),"2022-10-01");
        assertEquals(200.12,testcost.getamount());
        assertEquals(testcost.getusage(),"buy clothes");
    }

    @Test
    public void testtoString(){
        assertEquals(testcost.toString(),"date:2022-10-01,amount: 200.12, usage:buy clothes");
    }

    @Test
    public void testgetamount(){
        assertEquals(200.12,testcost.getamount());
    }
}
