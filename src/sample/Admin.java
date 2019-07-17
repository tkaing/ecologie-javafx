package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Admin {
    public ListView lvAssos;
    int c = 0;
    public ListView lvCourses;

    @FXML
    public void initialize() throws Exception{
        setLvCourses(lvCourses);
        setLvAssos(lvAssos);
    }


    public void setLvAssos(ListView lvAssos) throws Exception{
        this.lvAssos = lvAssos;
        String url = "https://thawing-fjord-12780.herokuapp.com/associations";
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

        System.out.println("assos" + response.toString());

        String nameAssos[] = response.toString().split("},");
        for(int i = 0; i < nameAssos.length; i++) {
            String n[] = nameAssos[i].split(":");
            String n2[] = n[3].split(",");
            lvAssos.getItems().add(i + " : " + n2[0]);
        }
    }

    public void setLvCourses(ListView lvCourses) throws Exception{
        this.lvCourses = lvCourses;
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

        String nameAssos[] = response.toString().split("},");
        for(int i = 0; i < nameAssos.length; i++) {
            String n[] = nameAssos[i].split(":");
            String n2[] = n[2].split(",");
            lvCourses.getItems().add(i + " : " + n2[0]);

            System.out.println(n[10]);
        }
    }

    public void handleMouseClick(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
        System.out.println("clicked on " + lvCourses.getSelectionModel().getSelectedItem());

        Parent root = FXMLLoader.load(getClass().getResource("/sample/detailCourses.fxml"));
        Scene scene = new Scene(root, 600,400);
        Stage test = new Stage();
        test.setScene(scene);
        test.show();
    };
}
