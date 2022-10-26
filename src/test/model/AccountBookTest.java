package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Testclass for AccountBook Class
public class AccountBookTest extends JsonTest {
private AccountBook testab;
private  Cost Cost1 = new Cost("2022-10-01",200.00,"buy clothes");
private  Cost Cost2 = new Cost("2022-01-01",100.00,"go to restaurants");
private  Cost Cost3 = new Cost("2022-09-15",500.50,"go for a trip");

@BeforeEach
    public void setup(){
    testab = new AccountBook("Selina's AccountBook");
}

@Test
    public void testaddonecost(){
    assertEquals(testab.size(),0);
    testab.addcost(Cost1);
    assertTrue(testab.contains(Cost1));
    assertEquals(testab.size(),1);
}

@Test
public void testaddmultiplecost(){
    testab.addcost(Cost1);
    testab.addcost(Cost2);
    assertEquals(testab.size(),2);
    assertTrue(testab.contains(Cost1));
    assertTrue(testab.contains(Cost2));
    assertFalse(testab.contains(Cost3));
}


@Test
    public void testgetcostonecost() {
    assertEquals(testab.getCost(), 0);
    testab.addcost(Cost1);
    assertEquals(testab.getCost(), 200.00);
}

@Test
    public void testgetcostmultipletimes(){
    assertEquals(testab.getCost(), 0);
    testab.addcost(Cost1);
    assertEquals(testab.getCost(), 200.00);
    testab.addcost(Cost2);
    assertEquals(testab.getCost(), 300.00);
    testab.addcost(Cost3);
    assertEquals(testab.getCost(), 800.50);
}

@Test
    public void testshowCostonlyone(){
    testab.addcost(Cost1);
    ArrayList<String> costlist = testab.showCost();
    ArrayList<String> expected = new ArrayList<>();
    assertEquals(1,costlist.size());
    assertEquals(costlist.get(0),"date:2022-10-01,amount: 200.00, usage:buy clothes");
}


@Test
public void testmultipleshowCost(){
    testab.addcost(Cost1);
    testab.addcost(Cost2);
    ArrayList<String> costlist =  testab.showCost();
    assertEquals(2,costlist.size());
    assertEquals(costlist.get(0),"date:2022-10-01,amount: 200.00, usage:buy clothes");
    assertEquals(costlist.get(1),"date:2022-01-01,amount: 100.00, usage:go to restaurants");
}

@Test
    public void testclearcostfornone(){
    testab.addcost(Cost1);
    testab.addcost(Cost2);
    testab.addcost(Cost3);
    testab.clearCost("2019-12-03");
    assertEquals(testab.size(),3);
    assertTrue(testab.contains(Cost1));
    assertTrue(testab.contains(Cost2));
    assertTrue(testab.contains(Cost3));
}


    @Test
    public void testclearcostinvalid(){
        testab.addcost(Cost1);
        assertFalse(testab.clearCost("20220101"));
    }

@Test
    public void testclearcostone(){
    testab.addcost(Cost1);
    testab.addcost(Cost2);
    testab.addcost(Cost3);
    testab.clearCost("2022-01-02");
    assertEquals(testab.size(),2);
    assertTrue(testab.contains(Cost1));
    assertFalse(testab.contains(Cost2));
    assertTrue(testab.contains(Cost3));
}

@Test
public void testclearcostjustthatday(){
    testab.addcost(Cost1);
    testab.addcost(Cost2);
    testab.addcost(Cost3);
    testab.clearCost("2022-01-01");
    assertEquals(testab.size(),3);
    assertTrue(testab.contains(Cost1));
    assertTrue(testab.contains(Cost2));
    assertTrue(testab.contains(Cost3));
}


    @Test
    public void testclearcosttwocost(){
        testab.addcost(Cost1);
        testab.addcost(Cost2);
        testab.addcost(Cost3);
        testab.clearCost("2022-09-16");
        assertEquals(testab.size(),1);
        assertTrue(testab.contains(Cost1));
        assertFalse(testab.contains(Cost2));
        assertFalse(testab.contains(Cost3));
    }

    @Test
    public void testclearcostclearall(){
        testab.addcost(Cost1);
        testab.addcost(Cost2);
        testab.addcost(Cost3);
        testab.clearCost("2022-10-02");
        assertEquals(testab.size(),0);
    }

    @Test
    public void testgetcosts(){
        testab.addcost(Cost1);
        List<Cost> costs = testab.getcosts();
        assertEquals(1,costs.size());
        checkCost("2022-10-01",200.00,"buy clothes",Cost1);
    }
































}














