package org.fsb.act.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.entities.Membre;
import org.fsb.act.entities.PersonneNecessiteuse;
import org.fsb.act.services.ServiceMembre;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SelectionnerMembreController implements Initializable{
	@FXML
    private TableColumn<?, ?> colProfession;

    @FXML
    private TableColumn<?, ?> colIdentite;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colTypePiece;

    @FXML
    private TableColumn<?, ?> colDirigeant;

    @FXML
    private TableView<Membre> tableMembres;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colD_N;

    @FXML
    private TableColumn<?, ?> colTelephone;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private AnchorPane anchorPane;
    
    private ObservableList<Membre>  data;
    
    private Stage stage;
	public Membre membreSelectionner = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCellTable();
		getAll();
		
	}
	/**
     * this function calls serviceMembre to receive all members
     */
	private void getAll() {
		data=FXCollections.observableArrayList(ServiceMembre.getAll());
		tableMembres.setItems(data);
	}
	/**
     * if you click on a table row
     * then display the row in these fields
     */
	@FXML
    void displayMembre(MouseEvent event) {
		if (event.getClickCount() > 1) {
			stage=(Stage)anchorPane.getScene().getWindow();
	        // check the table's selected item and get selected item
	        if (tableMembres.getSelectionModel().getSelectedItem() != null) {
	        	membreSelectionner= tableMembres.getSelectionModel().getSelectedItem();
	            stage.close();       
	        }
        }
	}
	/**
     * If you write in the search field  
     * then we find for matches members
     */
    @FXML
    void searchMembres() {
    	String text= searchField.getText();
    
    	if(!InputValidation.isFieldNotEmpty(searchField)) {
    		
    		tableMembres.setItems(data);
    	}else {

    		tableMembres.setItems(ServiceMembre.searchMembres(data, text));
    		
    	}
    	
    }
    @FXML
    void handleVider(ActionEvent event) {
    	stage=(Stage)anchorPane.getScene().getWindow();
    	membreSelectionner= new Membre(-1);
    	stage.close(); 
    }
	/**
     * correspondence between a table column and a attribute
     */
    private void setCellTable() {
    	colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    	colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	colD_N.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
    	colProfession.setCellValueFactory(new PropertyValueFactory<>("profession"));
    	colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    	colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    	colIdentite.setCellValueFactory(new PropertyValueFactory<>("numeroIdentite"));
    	colTypePiece.setCellValueFactory(new PropertyValueFactory<>("typePiece"));
    	colDirigeant.setCellValueFactory(new PropertyValueFactory<>("dirigeant"));
    }
    
}
