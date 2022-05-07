package org.fsb.act.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.entities.FamilleNecessiteuse;
import org.fsb.act.entities.PersonneNecessiteuse;
import org.fsb.act.services.FamilleNecessiteuseService;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SelectionnerFamilleController implements Initializable{
	@FXML
    private TableColumn<?, ?> colPere;

    @FXML
    private TableColumn<?, ?> colMere;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<?, ?> colNbFils;

    @FXML
    private TableView<FamilleNecessiteuse> tableView;

    @FXML
    private TableColumn<?, ?> colAdresse;
    
    @FXML
    private AnchorPane anchorPane;
    
    private ObservableList<FamilleNecessiteuse>  data;
    
	private Stage stage;
	public FamilleNecessiteuse familleNecessiteuse = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		data= FXCollections.observableArrayList();
		
		setCellTable();
		getAll();
		
	}
	/**
	 * 
	 * if select row then display this famille
	 * @param event
	 */
	@FXML
    void displayFamille(MouseEvent event) {
		stage=(Stage)anchorPane.getScene().getWindow();
		if (event.getClickCount() > 1) {
			if (tableView.getSelectionModel().getSelectedItem() != null) {
				
				familleNecessiteuse= tableView.getSelectionModel().getSelectedItem();
				stage.close();
				
				}
        }
    }
	@FXML
    void handleVider(ActionEvent event) {
    	stage=(Stage)anchorPane.getScene().getWindow();
    	familleNecessiteuse = new FamilleNecessiteuse(-1);
    	stage.close(); 
    }
	/**
     *  If you write in the search field  
     * then we find for matches familles
     * @param event
     */
    @FXML
    void searchFamilles() {
    	String text= searchField.getText();
        
    	if(!InputValidation.isFieldNotEmpty(searchField)) {
    		
    		tableView.setItems(data);
    	}else {

    		tableView.setItems(FamilleNecessiteuseService.searchFamilles(data, text));
    		
    	}
    }
	/**
     * this function calls serviceMembre to receive all members
     */
	private void getAll() {
		
		data.clear();
		data.addAll(FamilleNecessiteuseService.getAll());
		tableView.setItems(data);
	}
	/**
     * correspondence between a table column and a attribute
     */
    private void setCellTable() {
    	colPere.setCellValueFactory(new PropertyValueFactory<>("pere"));
    	colMere.setCellValueFactory(new PropertyValueFactory<>("mere"));
    	colNbFils.setCellValueFactory(new PropertyValueFactory<>("nbFils"));
    	colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    	
    }
}
