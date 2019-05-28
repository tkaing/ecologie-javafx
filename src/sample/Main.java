package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application implements EventHandler<ActionEvent> {
    Stage test = new Stage();
    Button loginButton;
    TextField fieldUserName;
    PasswordField fieldPassword;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();

        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        Label labelTitle = new Label("Enter your user name and password!");

        // Put on cell (0,0), span 2 column, 1 row.
        root.add(labelTitle, 0, 0, 2, 1);

        Label labelUserName = new Label("User Name");
        fieldUserName = new TextField();

        Label labelPassword = new Label("Password");

        fieldPassword = new PasswordField();

        loginButton = new Button("Login");
        loginButton.setOnAction(this::handle);

        GridPane.setHalignment(labelUserName, HPos.RIGHT);

        // Put on cell (0,1)
        root.add(labelUserName, 0, 1);


        GridPane.setHalignment(labelPassword, HPos.RIGHT);
        root.add(labelPassword, 0, 2);

        // Horizontal alignment for User Name field.
        GridPane.setHalignment(fieldUserName, HPos.LEFT);
        root.add(fieldUserName, 1, 1);

        // Horizontal alignment for Password field.
        GridPane.setHalignment(fieldPassword, HPos.LEFT);
        root.add(fieldPassword, 1, 2);

        // Horizontal alignment for Login button.
        GridPane.setHalignment(loginButton, HPos.RIGHT);
        root.add(loginButton, 1, 3);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("AssoEcolo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
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
            Scene scene = new Scene(admin , 300 , 300);
            test.setTitle("Admin");
            test.setScene(scene);
            test.show();

            //Appel API affichage des parcours
        } else {
            Scene scene = new Scene(root , 300, 300);
            test.setTitle("AssoEcolo");
            test.setScene(scene);
            test.show();

            parcours.setOnAction(this::handleParcours);
        }


    }

    public void handleParcours(ActionEvent event) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root , 300, 300);
        test.setTitle("Parcours");
        test.setScene(scene);
        test.show();

        //Appel API affichage des parcours
    }
}
