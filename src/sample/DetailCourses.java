package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailCourses {


    public BarChart idBarChart;
    public CategoryAxis idA;
    public NumberAxis idO;
    public String Curr;


    @FXML
    public void initialize() throws Exception {
        XYChart.Series set1 = new XYChart.Series<>();

        String url = "https://thawing-fjord-12780.herokuapp.com/courses";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("Response = " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());

        String nameAssos[] = response.toString().split("\"glassWaste\":");
        String glass[] = nameAssos[1].split(",");
        set1.getData().add(new XYChart.Data("Glass", Integer.valueOf(glass[0])));
        System.out.println(glass[0]);

        String nameAssos2[] = response.toString().split("\"plasticWaste\":");
        String glass2[] = nameAssos2[1].split(",");
        set1.getData().add(new XYChart.Data("Plastic", Integer.valueOf(glass2[0])));
        System.out.println(glass2[0]);

        String nameAssos3[] = response.toString().split("\"foodWaste\":");
        String glass3[] = nameAssos3[1].split(",");
        set1.getData().add(new XYChart.Data("food", Integer.valueOf(glass3[0])));
        System.out.println(glass[0]);

        String nameAssos4[] = response.toString().split("\"otherWaste\":");
        String glass4[] = nameAssos4[1].split(",");
        set1.getData().add(new XYChart.Data("Other", Integer.valueOf(glass4[0])));
        System.out.println(glass[0]);







        idBarChart.getData().addAll(set1);

    }
    @FXML
    LineChart<String, Double> lineChart;




}
