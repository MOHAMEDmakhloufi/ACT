package org.fsb.act.controllers;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.fsb.act.entities.Don;
import org.fsb.act.services.DonService;
import org.fsb.act.services.EvenementService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class DonController implements Initializable{
	  @FXML
	    private Label labelTypeDon;
	  @FXML
	    private Label labelMontant;
	  @FXML
	    private Label labelPrenom;
	  @FXML
	    private Label labelNom;
	  @FXML
	    private Label labelNationalite;
	  @FXML
	    private Label labelPieceIdentite;
	  
	  @FXML
	    private TextField textFieldMonatant;
	  @FXML
	    private TextField TextFieldPrenom;
	  @FXML
	    private TextField textFieldPieceIdentite;
	  @FXML
	    private TextField textFieldSearch;
	  @FXML
	    private TextField textFieldNom;
	  
	   @FXML
	    private ComboBox<?> comboBoxNationalite;
	    @FXML
	    private ComboBox<?> comboBoxtypeDon;
	   

	   

	    @FXML
	    private TableColumn<?, ?> colDerniereMiseAJour;

	   @FXML
	    private TableColumn<?, ?> colNationalite;

	   @FXML
	    private TableColumn<?, ?> colDonneur;

	    @FXML
	    private TableColumn<?, ?> colNom;

	    @FXML
	    private TableView<?> tableDon;

	    @FXML
	    private TableColumn<?, ?> colNum;

	    @FXML
	    private TableColumn<?, ?> colTypeDon;

	   @FXML
	    private TableColumn<?, ?> colObjet;
	   @FXML
	    private TableColumn<?, ?> colPrenom;
	    @FXML
	    private TableColumn<?, ?> colDirigeant;
	    
	   

	   
	    
	    // ***** Mes Attributs 
	    
	    private ObservableList<Don>  data;
	    
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	//etape1
	    	initialisercomboBoxNationalite();
	    	initialisercomboBoxtypeDon();
	    	//etape2
	    	setCellTable();
	    	//etape3
	    	getAll();
			
		}
	

	    private void getAll() {
	    	data=FXCollections.observableArrayList(DonService.getAll());
			tableDon.setItems( data);
			
		}


		private void setCellTable() {
			// TODO Auto-generated method stub
			
		}


		private void initialisercomboBoxtypeDon() {
	    	ObservableList<String>  list= FXCollections.observableArrayList(Arrays.asList("Argent liquide", "Objet Materiel"));
	    	comboBoxtypeDon.setItems(list);
	    	comboBoxtypeDon.getSelectionModel().select(0);
			
		}


		private void initialisercomboBoxNationalite() {
			ObservableList<String>  list= FXCollections.observableArrayList(Arrays.asList("Tunisienne", "française"));
	    	comboBoxNationalite.setItems(list);
	    	comboBoxNationalite.getSelectionModel().select(0);
			
		}


		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
