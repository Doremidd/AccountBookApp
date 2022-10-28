package persistence;

import model.AccountBook;
import model.Cost;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    //Source:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    public void testWriterInvalidFile() {
        try{
            AccountBook ab = new AccountBook("Selina's accountbook");
            JsonWriter jsonWriter = new JsonWriter("/data/my\0illegal:fileName.json");
            jsonWriter.open();
            fail("FileNotFoundException was expected");
        } catch(FileNotFoundException e) {
            //all good
        }
    }

    @Test
    public void testWriterEmptyAccountBook(){
        try{
            AccountBook ab = new AccountBook("Selina's accountbook");
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyAccountBook.json");
            jsonWriter.open();
            jsonWriter.write(ab);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterEmptyAccountBook.json");
            ab = jsonReader.read();
            assertEquals("Selina's accountbook",ab.getname());
            assertEquals(0,ab.size());
        }catch(IOException e){
            fail("Exception should not have been thrown");
        }
    }


    @Test
    public void testWriterGeneralAccountBook(){
        try{
            AccountBook ab = new AccountBook("Selina's accountbook");
            ab.addcost(new Cost("2022-09-16", 100, "go to restaurants"));
            ab.addcost(new Cost("2022-06-20", 200, "birthday gift for Tony"));
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterGeneralAccountBook.json");
            jsonWriter.open();
            jsonWriter.write(ab);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterGeneralAccountBook.json");
            ab = jsonReader.read();
            List<Cost> costs = ab.getcosts();
            assertEquals(costs.size(),2);
            checkCost("2022-09-16", 100.0, "go to restaurants",costs.get(0));
            checkCost("2022-06-20", 200.0, "birthday gift for Tony",costs.get(1));
        }catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
