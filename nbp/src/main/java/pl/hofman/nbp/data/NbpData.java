package pl.hofman.nbp.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.hofman.nbp.model.Nbp;
import pl.hofman.nbp.repository.NbpRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Component
public class NbpData implements CommandLineRunner {

    @Autowired
    private NbpRepository nbpRepository;

    @Override
    public void run(String... args) throws Exception {

        Nbp wal1 = new Nbp();
        Nbp wal2 = new Nbp();
        Nbp wal3 = new Nbp();
        Nbp wal4 = new Nbp();
        Nbp wal5 = new Nbp();
        Nbp wal6 = new Nbp();
        Nbp wal7 = new Nbp();
        Nbp wal8 = new Nbp();
        Nbp wal9 = new Nbp();
        Nbp wal10 = new Nbp();
        ArrayList<Nbp> waluty = new ArrayList<>();
        waluty.add(wal1);
        waluty.add(wal2);
        waluty.add(wal3);
        waluty.add(wal4);
        waluty.add(wal5);
        waluty.add(wal6);
        waluty.add(wal7);
        waluty.add(wal8);
        waluty.add(wal9);
        waluty.add(wal10);

        try {
            URL link = new URL("http://api.nbp.pl/api/exchangerates/tables/A/?format=json");
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            System.out.println(connection.getResponseCode());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(bufferedReader);
            JSONObject all = (JSONObject) jsonArray.get(0);
            JSONArray rates = (JSONArray) all.get("rates");



            for (int i = 0; i < 10; i++) {
                Nbp pomoc = waluty.get(i);
                JSONObject object = (JSONObject) rates.get(i);
                pomoc.setCode((String) object.get("code"));
                pomoc.setRate((Double) object.get("mid"));
                pomoc.setCurrency((String) object.get("currency"));
                nbpRepository.save(pomoc);
            }

        } catch (Exception e) {
            System.out.println("Exception found");
        }
    }
}
