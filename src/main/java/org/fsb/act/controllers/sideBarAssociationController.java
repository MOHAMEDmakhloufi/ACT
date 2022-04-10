package org.fsb.act.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.fsb.act.App;
import org.fsb.act.services.sideBarAssociationService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class sideBarAssociationController implements Initializable{
	
	@FXML
    private BorderPane borderPane;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label labelLitre;

    @FXML
    private Button btnCompte;
    
    @FXML
    private Button btnAssociation;

    @FXML
    private Button btnAcces;

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DisplayLogoAndTitre();
		try {
			borderPane.setCenter(App.loadFXML("CompteAssociation"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	/**
	 * if you are clicked go to compteAssociation
	 * @param event
	 */
	@FXML
    void toGererLesComptes(ActionEvent event) {
		try {
			borderPane.setCenter(App.loadFXML("CompteAssociation"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * if you are clicked go to modifierAssociation
	 * @param event
	 */
    @FXML
    void toModifierAssociation(ActionEvent event) {
    	try {
			borderPane.setCenter(App.loadFXML("modifierAssociation"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
	 * if you are clicked go to accesFinance
	 * @param event
	 */
    @FXML
    void toAccesFinance(ActionEvent event) {
    	try {
			borderPane.setCenter(App.loadFXML("AccesFinance"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * Display logo association et titre asscoiation from service
     */
    public void DisplayLogoAndTitre() {
    	sideBarAssociationService.getLogoTitre(imageLogo, labelLitre);
    }

}
