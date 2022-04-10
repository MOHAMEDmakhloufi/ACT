package org.fsb.act.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable{
	
	@FXML
	private ListView<String> listViewMembres;
	@FXML
	private ListView<String> listViewDonneurs; 

    @FXML
    private TabPane tabPane;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		generateSubList(listViewMembres, "gestion membres");
		generateSubList(listViewDonneurs, "gestion donneurs");
		
	}
	/**
	 * assign rows(str) to a list view
	 * @param listView
	 * @param str
	 */
	private void generateSubList(ListView<String> listView, String... str) {
		ObservableList<String> subList= FXCollections.observableArrayList();
		subList.addAll(str);
		listView.setItems(subList);
	}
	/**
	 * assign iconClose to a new Tab
	 * And if you are clicked an icon Close remove the tab and select the last tab
	 * @param tab
	 */
	private void iconClose(Tab tab) {
		AnchorPane a = new AnchorPane();
		a.setPrefWidth(12);
		a.setPrefHeight(12);
		
		ImageView imageView= new ImageView();
		imageView.setFitWidth(12);
		imageView.setFitHeight(12);
		
		imageView.setOnMouseClicked(event ->{
			
			int index= tabPane.getTabs().indexOf(tab);
			
	    	tabPane.getTabs().remove(index);
	    	
	    	tabPane.getSelectionModel().selectLast();
		});
		
		File file = new File(".\\src\\main\\resources\\org\\fsb\\act\\images\\close.png");
		Image image= new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), true, true);
		
		imageView.setImage(image);
		
		a.getChildren().add(imageView);
		tab.setGraphic(a);
	}
	
	/**
	 * create a new tab and load the FXML file 
	 */
	private void selectSubList() {
		Tab tab;
		int index = listViewMembres.getSelectionModel().getSelectedIndex();
		
		if(listViewMembres.getItems().get(index).equals("gestion membres")) {
			try {
				tab= new Tab("membres", App.loadFXML("gestionMembres"));
				
				tabPane.getTabs().add(tab);
				tabPane.getSelectionModel().select(tab);
				
				iconClose(tab);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * if you are clicked in the list then execute @selectSubList
	 * @param event
	 */
	@FXML
    void handleListMembres(MouseEvent event) {
		selectSubList();
    }
	
}
