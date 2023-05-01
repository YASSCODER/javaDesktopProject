package com.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import com.app.entity.Users;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ConnectionController connection ;
    private static Users user;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("connection"), 1100, 900);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public  static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
       
        launch();
        connection = new ConnectionController();
        user = connection.connectionClicked();
        System.out.println(user.toString());
        
    }

}