package org.fsb.act.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.fsb.act.entities.Compte;
import org.fsb.act.services.CompteAssociationService;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CompteAssociationController implements Initializable{
	
	@FXML
    private TableView<Compte> tableComptes= new TableView<Compte>();
    
    @FXML
    private TableColumn<Compte, String> colUsername;
    @FXML
    private TableColumn<Compte, String> colPassword;  
    @FXML
    private TableColumn<Compte, Integer> colActivation;
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private PasswordField pass2;
    
    @FXML
    private ToggleButton desactiver;
    
    @FXML
    private Button reset;
    
    @FXML
    private Button update;
    
    @FXML
    private Button creer;
    
    @FXML
    private TextField search;
    
    private Compte compte = new Compte();
    List<Compte> comptes;
    
    
    
    public void onEdit() {
        // check the table's selected item and get selected item
        if (tableComptes.getSelectionModel().getSelectedItem() != null) {
            Compte selectedCompte = tableComptes.getSelectionModel().getSelectedItem();
            username.setText(selectedCompte.getUsername());
            pass.setText(selectedCompte.getPassword());
            pass2.setText(selectedCompte.getPassword());
        }
    }
  	 @Override
 	public void initialize(URL location, ResourceBundle resources) {
     	setCellTable();
     	loadDataCompteAssociation();
     	tableComptes.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
 	}
  	
  	 
  	
  	
    public boolean validationChamps() {
    	boolean test = true;
    	username.setStyle("-fx-text-fill:#3F2B63");
    	pass.setStyle("-fx-text-fill:#3F2B63");
    	pass2.setStyle("-fx-text-fill:#3F2B63");
    	
    	
    	if(!InputValidation.isFieldNotEmpty(username) ) {
    		username.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(pass)) {
    		pass.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(pass2)) {
    		pass2.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!pass.getText().equals(pass2.getText())) {
    		pass2.setStyle("-fx-text-fill:red");
    		InputValidation.showAlertInfoWithoutHeaderText("Le mot de passe n'est pas la meme!");
    		test=false;
    	}
    	return test;
    } 	
  	/**
     * If you click btn reset,
     * empty all fields
     * @param event
     */
    @FXML
    void clear(ActionEvent event) {
    	username.clear();
    	pass.clear();
    	pass2.clear();
    }
    
    /**
     * If you click btn Update
     * we start to update the member
     * @param event
     */
    @FXML
    void updateCompte(ActionEvent event) {
    	if(validationChamps()) {   		
    		compte.setUsername(username.getText());
    		compte.setPassword(pass.getText());
    		compte.setActive(1);

    		int i= CompteAssociationService.updateCompteAssocition(compte);
    		if(i==1) {
    			InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
    			loadDataCompteAssociation();
    		}else
    			InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
    	}
    }
        
    /**
     * If you click btn Create
     * we create an account
     * @param event
     */
    @FXML
    void creerCompte(ActionEvent event) {
    	if(validationChamps()) {   		
    		compte.setUsername(username.getText());
    		compte.setPassword(pass.getText());
    		compte.setActive(1);

    		int i= CompteAssociationService.addCompteAssocition(compte);
    		if(i==1) {
    			InputValidation.showAlertInfoWithoutHeaderText("Record created successfully!");
    			loadDataCompteAssociation();
    		}else
    			InputValidation.showAlertErrorWithoutHeaderText("Record create Failure!");
    	}
    }
    
    /**
     * If you click btn Desactiver
     * we deactivate the account
     * @param event
     */
    @FXML
    void desactiverCompte(ActionEvent event) {
    	if(validationChamps()) {   		
    		compte.setUsername(username.getText());
    		compte.setPassword(pass.getText());
    		compte.setActive(0);

    		int i= CompteAssociationService.updateCompteAssocition(compte);
    		if(i==1) {
    			InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
    			loadDataCompteAssociation();
    		}else {
    			InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
    		}
    	}
    }

    /**
     * If you click btn Search
     * we search for an account
     * by username
     * @param event
     */
    @FXML
    void searchCompte(ActionEvent event) {
    		String key;
    		key=search.getText();
    		comptes=CompteAssociationService.searchDataCompteAssociation(key);
    		ObservableList<Compte> comp = FXCollections.observableArrayList(comptes);
    		//Adding data to the table
    	    tableComptes.setItems(comp);    	 
    }
    /**
     * correspondence between a table column and a attribute
     */
    private void setCellTable() {
    	colUsername.setCellValueFactory(new PropertyValueFactory<Compte,String>("username"));
    	colPassword.setCellValueFactory(new PropertyValueFactory<Compte,String>("password"));
    	colActivation.setCellValueFactory(new PropertyValueFactory<Compte,Integer>("active"));
    }
    
    /**
     * initialement load data from service
     */
	public void loadDataCompteAssociation() {
		comptes=CompteAssociationService.getDataCompteAssociation();
		ObservableList<Compte> comp = FXCollections.observableArrayList(comptes);
		tableComptes.setEditable(true);
		tableComptes.setItems(comp);		
	}
	//ObservableList<String> names = FXCollections.observableArrayList();
    
}
