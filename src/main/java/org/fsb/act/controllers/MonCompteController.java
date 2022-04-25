package org.fsb.act.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.Compte;
import org.fsb.act.entities.Evenement;
import org.fsb.act.services.CompteAssociationService;
import org.fsb.act.services.EvenementService;
import org.fsb.act.validation.InputValidation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MonCompteController implements Initializable{
    @FXML
    private Label labelActuelPassword;

    @FXML
    private PasswordField passwordFieldConfirmer;

    @FXML
    private Label labelUsername;

    @FXML
    private PasswordField passwordFieldNouveau;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private Label labelConfirmer;

    @FXML
    private PasswordField passwordFieldActuel;

    @FXML
    private Label labelNouveau;

    private Compte monCompte;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		monCompte= CompteAssociationService.getOneById(App.dirigeant);
		textFieldUsername.setText(monCompte.getUsername());
		
	}
    
    
    /**
     * if clicked enregistrer then save the new update in data base
     * @param event
     */
    @FXML
    void enregistrerLesModification(ActionEvent event) {
    	if(validationChamps() && monCompte!=null) {
    		if(monCompte.getPassword().equals(passwordFieldActuel.getText())) {
				monCompte.setPassword(passwordFieldNouveau.getText());
				int i=CompteAssociationService.updateCompteAssocition(monCompte);
				if(i==1) {
					InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
					clear();
				}else
					InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
    		}else
    			InputValidation.showAlertErrorWithoutHeaderText("ACTUEL PASSWORD INCORRECT ! ");
    	}else
    		InputValidation.showAlertErrorWithoutHeaderText("ERREUR");
    }
    public void clear() {
    	initialColorLabel();
    	passwordFieldActuel.clear();
    	passwordFieldNouveau.clear();
    	passwordFieldConfirmer.clear();
    }
    /**
     * test the textFields One by one
     * call initialColorLabel()
     * @return
     */
    public boolean validationChamps() {
    	boolean test = true;
    	
    	initialColorLabel();
    	
    	if(!InputValidation.isFieldNotEmpty(textFieldUsername) ) {
    		labelUsername.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(passwordFieldActuel)) {
    		labelActuelPassword.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(passwordFieldNouveau)) {
    		labelNouveau.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(passwordFieldConfirmer) ) {
    		labelConfirmer.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.areFieldsIdendical(passwordFieldNouveau, passwordFieldConfirmer)) {
    		labelNouveau.setStyle("-fx-text-fill:red");
    		labelConfirmer.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	
    	return test;
    }
    /**
     * initial the color label 
     */
    public void initialColorLabel() {
    	labelUsername.setStyle("-fx-text-fill:#3F2B63");
    	labelActuelPassword.setStyle("-fx-text-fill:#3F2B63");
    	labelNouveau.setStyle("-fx-text-fill:#3F2B63");
    	labelConfirmer.setStyle("-fx-text-fill:#3F2B63");
    	
    	
    }
	
}
