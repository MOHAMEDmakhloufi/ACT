package org.fsb.act;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App .
 */
public class App extends Application {

    public static Scene scene;
    public static String dirigeant;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.show();
        
    }

    static public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    static public void setRoot(String fxml, double width, double heigth) throws IOException {
    	Stage stage = (Stage)scene.getWindow();
    	
        scene.setRoot(loadFXML(fxml));
        stage.setWidth(width);
    	stage.setHeight(heigth);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
    	
        launch();
    }

}
