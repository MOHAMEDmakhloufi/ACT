package org.fsb.act.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.models.TabModel;
import org.fsb.act.services.SideBarAssociationService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	@FXML
	private ListView<String> listViewMembres;

    @FXML
    private ListView<String> listViewFinances;

    @FXML
    private ListView<String> listViewDashBoard;

    @FXML
    private ListView<String> listViewNecessiteux;

    @FXML
    private ListView<String> listViewEvenements;

    @FXML
    private ListView<String> listViewMonCompte;

    @FXML
    private TabPane tabPane;
    
    @FXML
    private ImageView imageLogo;

    @FXML
    private Label labelLitre;
    
    @FXML
    private AnchorPane anchorPane2;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		generateSubList(listViewMembres, "gestion membres");
		generateSubList(listViewNecessiteux, "gestion personnes necessiteuse", "gestion familles necessiteuse");
		generateSubList(listViewEvenements, "gestion evenments", "notification");
		generateSubList(listViewDashBoard, "dashBoard");
		generateSubList(listViewFinances, "cotisation des membres", "dons", "depenses", "notification");
		generateSubList(listViewMonCompte, "parametre de compte", "log out");
	}
	/**
	 * assign rows(str) to a list view
	 * @param listView
	 * @param str
	 */
	private void generateSubList(ListView<String> listView, String... str) {
		DisplayLogoAndTitre();
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
		a.setPrefWidth(13);
		a.setPrefHeight(13);
		
		ImageView imageView= new ImageView();
		imageView.setFitWidth(13);
		imageView.setFitHeight(13);
		
		imageView.setOnMouseClicked(event ->{
			
			int index= tabPane.getTabs().indexOf(tab);
			
	    	tabPane.getTabs().remove(index);
	    	
	    	tabPane.getSelectionModel().selectLast();
	    	
	    	if(tabPane.getTabs().size() ==0) {
				
		    	tabPane.getStyleClass().add("tabPanes");
		    	anchorPane2.getStyleClass().add("backgroundImage");
			}
		});
		
		File file = new File(".\\src\\main\\resources\\org\\fsb\\act\\images\\close.png");
		Image image= new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), true, true);
		
		imageView.setImage(image);
		
		a.getChildren().add(imageView);
		tab.setGraphic(a);
	}
	
	/**
	 * create a new tab and load the FXML file 
	 * @param list 
	 * @param listView 
	 */
	private void selectSubList(ListView<String> listView, List<TabModel> list) {
		
		if (listView.getSelectionModel().getSelectedItem() != null) {
			int index = listView.getSelectionModel().getSelectedIndex();
			list.forEach(element ->{
				if(listView.getItems().get(index).equals(element.getElementList())) {
					try {
						Tab tab= new Tab(element.getTabTitle(), App.loadFXML(element.getTabContent()));
						
						tabPane.getTabs().add(tab);
						tabPane.getSelectionModel().select(tab);
						
						iconClose(tab);
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				if(listView.getItems().get(index).equals("log out")) {
	
					try {
						AlertFinanceController.permission= false;
						App.setRoot("Login", 600, 400, false);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			if(tabPane.getTabs().size() !=0) {
				
		    	tabPane.getStyleClass().remove("tabPanes");
		    	anchorPane2.getStyleClass().remove("backgroundImage");
			}
		}
	}
	/**
	 * if you are clicked in the list then execute @selectSubList
	 * @param event
	 */
	@FXML
    void handleListMembres(MouseEvent event) {
		if(event.getClickCount() >1) {
			List<TabModel> list = new ArrayList<>();
			list.add(new TabModel("gestion membres", "membres", "gestionMembres"));
			selectSubList(listViewMembres, list);
		}
    }
	
	@FXML
    void handleListNecessiteux(MouseEvent event) {
		if(event.getClickCount() >1) {
			List<TabModel> list = new ArrayList<>();
			list.add(new TabModel("gestion personnes necessiteuse", "personnes", "personneNecessiteuse"));
			list.add(new TabModel("gestion familles necessiteuse", "familles", "FamillesNecessiteuse"));
			selectSubList(listViewNecessiteux, list);
		}
    }

    @FXML
    void handleListEvenements(MouseEvent event) {
    	if(event.getClickCount() >1) {
	    	List<TabModel> list = new ArrayList<>();
			list.add(new TabModel("gestion evenments", "evenements", "evenements"));
			list.add(new TabModel("notification", "notificationV", "notificationValidation"));
			selectSubList(listViewEvenements, list);
    	}
    }

    @FXML
    void handleListDashBoard(MouseEvent event) {
    	if(event.getClickCount() >1) {
	    	List<TabModel> list = new ArrayList<>();
			list.add(new TabModel("dashBoard", "dashBoard", "dashBoard"));
			selectSubList(listViewDashBoard, list);
    	}
    }

    @FXML
    void handleListFinances(MouseEvent event) {
    	if(event.getClickCount() >1) {
	    	List<TabModel> list = new ArrayList<>();
			list.add(new TabModel("cotisation des membres", "cotisation", "cotisationMembre"));
			list.add(new TabModel("dons", "dons", "Dons"));
			list.add(new TabModel("depenses", "depenses", "depense"));
			list.add(new TabModel("notification", "notificationE", "notificationEvenement"));
			selectSubList(listViewFinances, list);
    	}
    }
    @FXML
    void testPermission(MouseEvent event){
    	Stage alert= new Stage();
    	alert.setResizable(false);
    	alert.setOnCloseRequest(even -> {
    	    AlertFinanceController.alreadyOpen= false;
    	    // Save file
    	});
    	try {
    		if(!AlertFinanceController.permission && !AlertFinanceController.alreadyOpen) {
    			listViewFinances.setDisable(true);
    			alert.setScene(new Scene(App.loadFXML("alertAcces")));
    			alert.showAndWait();
    			
    		}
    		if(AlertFinanceController.permission) {
    			listViewFinances.setDisable(false);
    		}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void handleListMonCompte(MouseEvent event) {
    	if(event.getClickCount() >1) {
	    	List<TabModel> list = new ArrayList<>();
			list.add(new TabModel("parametre de compte", "monCompte", "monCompte"));
			
			selectSubList(listViewMonCompte, list);
    	}
    }
    public void DisplayLogoAndTitre() {
    	SideBarAssociationService.getLogoTitre(imageLogo, labelLitre);
    }
    

}
