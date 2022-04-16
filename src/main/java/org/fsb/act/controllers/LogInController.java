package org.fsb.act.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.Association;
import org.fsb.act.entities.Compte;
import org.fsb.act.services.LogInService;
import org.fsb.act.validation.InputValidation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController implements Initializable{

    @FXML
    private CheckBox remember;

    @FXML
    private PasswordField password;

    @FXML
    private Label labelCreate;

    @FXML
    private Button btnlogin;

    @FXML
    private TextField username;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(associationExist()!= null)
			labelCreate.setVisible(false);
		
	}
	/**
	 * get association if exist from service
	 * @return
	 */
	Association associationExist() {
		return LogInService.associationExist();
	}
	/**
	 * if you are clicked log in then create association object and send to service
	 * @param event
	 */
	@FXML
    void handeLogIn(ActionEvent event) {
		
		Association association;
		if(validationChamps()) {
			Compte compte= LogInService.getCompte(username.getText());
			
			if(compte!= null) {
				if(compte.getActive()==0) {
					InputValidation.showAlertErrorWithoutHeaderText("votre compte est desactiver!");
				}else if(compte.getPassword().equals(password.getText()) ) {
					try {
						App.dirigeant= compte.getUsername();
						App.setRoot("Main", 1024, 600);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else
					InputValidation.showAlertErrorWithoutHeaderText("username or password is incorrect!");
			}else if((association= associationExist()) != null) {
				
				if(association.getUsername().equals(username.getText())&&association.getPassword().equals(password.getText())) {
					try {
						App.setRoot("sideBarAssociation", 800, 500);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else 
					InputValidation.showAlertErrorWithoutHeaderText("username or password is incorrect!");
			}else
				InputValidation.showAlertErrorWithoutHeaderText("username or password is incorrect!");
		}else
			InputValidation.showAlertErrorWithoutHeaderText("username or password is incorrect!");
		
		
	}
	/**
     * test the textFields One by one
     * @return
     */
    public boolean validationChamps() {
    	boolean test = true;
    	
    	if(!InputValidation.isFieldNotEmpty(username) ) {
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(password)) {
    		test=false;
    	}
    	
    	return test;
    }
    /**
     * open interface creer association
     */
    @FXML
    void openCreerAssociation() {
    	try {
			App.setRoot("CreerAssociation", 800, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
