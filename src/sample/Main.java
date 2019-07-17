package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class Main extends Application implements EventHandler<ActionEvent> {
    Stage test = new Stage();
    Button loginButton;
    TextField fieldUserName;
    PasswordField fieldPassword;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Login.fxml"));


        Scene scene = new Scene(root, 400,400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event){
        //Stage test = new Stage();
        GridPane root = new GridPane();
        GridPane admin = new GridPane();

        Button parcours = new Button("Parcours");
        Button BoiteIdee = new Button("Boite à idée");
        Button Chat = new Button("Chat assos");

        root.add(parcours, 0, 0, 1, 1);
        root.add(BoiteIdee, 1, 0, 1, 1);
        root.add(Chat, 2, 0, 1, 1);

        if (fieldUserName.getText().toString().equals("admin") && fieldPassword.getText().toString().equals("admin")) {



            //Appel API affichage des associations
            String url = "https://thawing-fjord-12780.herokuapp.com/associations";
            try {
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


                String nameAssos[] = response.toString().split(":");
                String name[] = nameAssos[3].split(",");
                System.out.println(name[0]);

                ObservableList<String> nameList = FXCollections.<String>observableArrayList(name[0]);
                ListView<String> LV = new ListView<>(nameList);
                admin.add(LV, 0, 0, 1, 1);

                Scene scene = new Scene(admin , 300 , 300);
                test.setTitle("Admin");
                test.setScene(scene);
                test.show();

            } catch (Exception e ) {

            }



        } else {
            Scene scene = new Scene(root , 300, 300);
            test.setTitle("AssoEcolo");
            test.setScene(scene);
            test.show();

            parcours.setOnAction(event1 -> {
                try {
                    handleParcours(event1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


    }

    public void handleParcours(ActionEvent event) throws Exception {
        GridPane root = new GridPane();

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
//ListView Parcours a définir ??
        ObservableList<String> nameList = FXCollections.<String>observableArrayList("");
        ListView<String> LV = new ListView<>(nameList);
        root.add(LV, 0, 0, 1, 1);

        Scene scene = new Scene(root , 300, 300);
        test.setTitle("Parcours");
        test.setScene(scene);
        test.show();

        //Faire un scroll view pour tous les parcours
    }
}
