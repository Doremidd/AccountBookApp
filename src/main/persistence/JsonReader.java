package persistence;

import model.AccountBook;
import model.Cost;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads accoutbook from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS:reads accountbook from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountBook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccountBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder stringbuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringbuilder.append(s));
        }

        return stringbuilder.toString();
    }

    // EFFECTS: parses accountbook from JSON object and returns it
    private AccountBook parseAccountBook(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        AccountBook ab = new AccountBook(name);
        addCosts(ab, jsonObject);
        return ab;
    }

    // MODIFIES: ab
    // EFFECTS: parses costs from JSON object and adds them to accountbook
    private void addCosts(AccountBook ab, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("costs");
        for (Object json : jsonArray) {
            JSONObject nextCost = (JSONObject) json;
            addCost(ab, nextCost);
        }
    }

    // MODIFIES: ab
    // EFFECTS: parses cost from JSON object and adds it to accountbook
    private void addCost(AccountBook ab, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        Double amount = jsonObject.getDouble("amount");
        String usage = jsonObject.getString("usage");
        Cost cost = new Cost(date,amount,usage);
        ab.addcost(cost);
    }

}
