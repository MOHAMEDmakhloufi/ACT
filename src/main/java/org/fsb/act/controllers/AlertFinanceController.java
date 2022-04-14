package org.fsb.act.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.services.AccesFinanceService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AlertFinanceController implements Initializable{
	

	
	@FXML
    private PasswordField accesFinance;
	
	@FXML
    private AnchorPane anchorPane;
	
	private String acces;
	public static boolean permission= false;
	public static boolean alreadyOpen= false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		acces= AccesFinanceService.getAccesFinance();
		alreadyOpen=true;
	}
	
    @FXML
    void permission(ActionEvent event) {
    	if(acces == null)
    		acces="";
    	if(accesFinance.getText().equals(acces)) {
    		permission= true;
    		Stage stage= (Stage) anchorPane.getScene().getWindow();
    		alreadyOpen= false;
    		stage.close();
    	}
    	else
    		permission= false;
    }

}
