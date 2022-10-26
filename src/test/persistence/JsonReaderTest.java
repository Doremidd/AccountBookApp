package persistence;

import model.AccountBook;
import model.Cost;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    public void testReaderNonExistentFile(){
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try{
            AccountBook ab = reader.read();
            fail("IOException expected");
        } catch(IOException e){
            //all good
        }
    }

    @Test
    public void testReaderEmptyAccountBook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccountBook.json");
        try{
            AccountBook ab = reader.read();
            assertEquals("Selina's accountbook",ab.getname());
            assertEquals(0,ab.size());
        } catch(IOException e){
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReadeGeneralAccountBook() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccountBook.json");
        try{
            AccountBook ab = reader.read();
            assertEquals("Selina's accountbook",ab.getname());
            List<Cost> costs = ab.getcosts();
            assertEquals(2,costs.size());
            checkCost("2022-02-15", 100.0,"go for fun",costs.get(0));
            checkCost("2022-06-20", 200.0,"birthday gift for Tony",costs.get(1));
        } catch(IOException e){
            fail("Couldn't read from file");
        }
    }


}
