package sample;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Member {
    public ListView lvCourses;
    int c = 0;

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
            lvCourses.getItems().add(i + " :_ " + n[2]);
        }
    }

    public void handleMouseClick(MouseEvent mouseEvent) throws Exception {

        if(c == 0) {
            setLvCourses(lvCourses);
            c++;
        }

        System.out.println("clicked on " + lvCourses.getSelectionModel().getSelectedItem());
    }
}
