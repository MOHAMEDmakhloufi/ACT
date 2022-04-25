package org.fsb.act;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import org.fsb.act.controllers.NotificationEventController;
import org.fsb.act.dao.ConnxDB;


/**
 * JavaFX App .
 */
public class App extends Application {

    public static Scene scene;
    public static String dirigeant="med.mak";
    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD:src/main/java/org/fsb/act/App.java
        scene = new Scene(loadFXML("dons"));
=======
        scene = new Scene(loadFXML("PersonneNecessiteuse"));
>>>>>>> nexus:java/org/fsb/act/App.java
        stage.setScene(scene);
        stage.show();
        //stage.setResizable(false);
        stage.setOnCloseRequest(e -> {if(ConnxDB.dcr!= null)
			try {
				ConnxDB.oracleConnecxion.unregisterDatabaseChangeNotification(ConnxDB.dcr);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}});
    }

    static public void setRoot(String fxml, boolean... resizable) throws IOException {
    	Stage stage = (Stage)scene.getWindow();
        scene.setRoot(loadFXML(fxml));
        if(resizable.length == 0)
        	stage.setResizable(true);
        else
        	stage.setResizable(resizable[0]);
    }
    static public void setRoot(String fxml, double width, double height, boolean... resizable) throws IOException {
    	Stage stage = (Stage)scene.getWindow();
        scene.setRoot(loadFXML(fxml));
        
        double newX= stage.getX()+((stage.getWidth() - width)/2);
 
        stage.setWidth(width);
        stage.setX(newX);
    	stage.setHeight(height);
    	
        if(resizable.length == 0)
        	stage.setResizable(true);
        else
        	stage.setResizable(resizable[0]);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) { 	
        launch();
    }

}
