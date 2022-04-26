package org.fsb.act.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.entities.PersonneNecessiteuse;
import org.fsb.act.services.PersonneNecessiteuseService;
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

public class SelectionnerPersonneController implements Initializable {

    @FXML
    private TableColumn<PersonneNecessiteuse, String> colProfession;

    @FXML
    private TableView<PersonneNecessiteuse> tablePersonnes;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<PersonneNecessiteuse, String> colNom;

    @FXML
    private TableColumn<PersonneNecessiteuse, Integer> colCode;

    @FXML
    private TableColumn<PersonneNecessiteuse, String> colDate;

    @FXML
    private TableColumn<PersonneNecessiteuse, String> colPrenom;

    @FXML
    private TableColumn<PersonneNecessiteuse, String> colTelephone;

    @FXML
    private TableColumn<PersonneNecessiteuse, String> colAdresse;
    
    @FXML
    private AnchorPane anchorPane;
    
    private ObservableList<PersonneNecessiteuse>  data;
    
    private Stage stage;
	public PersonneNecessiteuse personneNecessiteuse = null;
	//public PersonneNecessiteuse personneNecessiteuse = new PersonneNecessiteuse(-1);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tablePersonnes.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
		
		setCellTable();
		getAll();
		//stage=(Stage)anchorPane.getScene().getWindow();
		//stage.setOnCloseRequest(e-> System.out.println("I'am closed man"));
	}
	public void onEdit() {
		stage=(Stage)anchorPane.getScene().getWindow();
        // check the table's selected item and get selected item
        if (tablePersonnes.getSelectionModel().getSelectedItem() != null) {
        	personneNecessiteuse= tablePersonnes.getSelectionModel().getSelectedItem();
            stage.close();       
        }
    }
	@FXML
    void search() {
		String text= search.getText();  
    	if(!InputValidation.isFieldNotEmpty(search)) {  		
    		tablePersonnes.setItems(data);
    	}else {
    		tablePersonnes.setItems(PersonneNecessiteuseService.searchPersonneN(data, text));   		
    	} 
    }

    @FXML
    void handleVider(ActionEvent event) {
    	stage=(Stage)anchorPane.getScene().getWindow();
    	personneNecessiteuse = new PersonneNecessiteuse(-1);
    	stage.close(); 
    }
    public void getAll() {
    	data= FXCollections.observableArrayList(PersonneNecessiteuseService.getData());
    	tablePersonnes.setItems(data);
    }
    private void setCellTable() {
    	colNom.setCellValueFactory(new PropertyValueFactory<PersonneNecessiteuse,String>("nom"));
    	colPrenom.setCellValueFactory(new PropertyValueFactory<PersonneNecessiteuse,String>("prenom"));
    	colDate.setCellValueFactory(new PropertyValueFactory<PersonneNecessiteuse,String>("dateN"));
    	colTelephone.setCellValueFactory(new PropertyValueFactory<PersonneNecessiteuse,String>("telephone"));
    	colAdresse.setCellValueFactory(new PropertyValueFactory<PersonneNecessiteuse,String>("adresse"));
    	colCode.setCellValueFactory(new PropertyValueFactory<PersonneNecessiteuse,Integer>("codePostal"));
    	colProfession.setCellValueFactory(new PropertyValueFactory<PersonneNecessiteuse,String>("profession"));
    }
}
