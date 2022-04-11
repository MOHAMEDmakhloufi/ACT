package org.fsb.act.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.services.AccesFinanceService;
import org.fsb.act.validation.InputValidation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AccesFinanceController implements Initializable{
	
	 @FXML
	 private TextField textFieldAcces;

	 private String acces;  
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadDataFinance();
		
	}
	/**
	 * load data acces fiance from service 
	 */
	public void loadDataFinance() {
		acces = AccesFinanceService.getAccesFinance();
		textFieldAcces.setText(acces);
	}
	/**
	 * if changed the textfield then update acces finance
	 */
	 @FXML
    void updateAcces() {
		 
		 acces= textFieldAcces.getText();
		 int i=AccesFinanceService.updateAccesFinance(acces);
		 if(i==1) {
 			InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
 		}else
 			InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
    }
}
