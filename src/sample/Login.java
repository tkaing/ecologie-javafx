package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Login {
    public TextField username;
    public TextField password;

    public void login(ActionEvent actionEvent) throws Exception{



        if ((username.getText().toString().equals("admin")) && (password.getText().toString().equals("admin"))) {
            System.out.println(username.getText() + " | " + password.getText());
            Parent root = FXMLLoader.load(getClass().getResource("/sample/admin.fxml"));

            Scene scene = new Scene(root, 600,400);
            Stage test = new Stage();
            test.setScene(scene);
            test.show();


        } else {
            //LOGIN
            String url = "https://selfsolve.apple.com/wcResults.do";
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "\"email\": \"member@live.fr\", \"password\": \"43706e41796b484c\"";

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            //while ((inputLine = in.readLine()) != null) {
                //response.append(inputLine);
            //}
            in.close();

            //print result
            System.out.println(response.toString());
        }


    }
}
