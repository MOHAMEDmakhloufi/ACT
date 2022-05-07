package org.fsb.act.controllers;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.Evenement;
import org.fsb.act.entities.Membre;
import org.fsb.act.services.EvenementService;

import org.fsb.act.services.ServiceMembre;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class EvenementController implements Initializable {
    @FXML
    private Label labelBudget;

    @FXML
    private Label labelLieu;
    
    @FXML
    private Label labelTitre;
    
    @FXML
    private Label labelDateDebut;

    @FXML
    private Label labelDateFin;

    @FXML
    private TableColumn<?, ?> colDateDebut;

    @FXML
    private TableColumn<?, ?> colLieu;

    @FXML
    private TableColumn<?, ?> colDateFin;

    @FXML
    private TableColumn<?, ?> colBudget;

    @FXML
    private TableColumn<?, ?> colTitre;
    
    @FXML
    private TableColumn<?, ?> colValidation;

    @FXML
    private TableColumn<?, ?> colDirigeant;
    
    @FXML
    private TextField timeDateFin;

    @FXML
    private TextField timeDateDebut;
    
    @FXML
    private TextField textFieldLieu;

    @FXML
    private TextField TextFieldTitre;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private TextField textFieldBudget;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private DatePicker dateFin;

    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<Evenement> tableEvenement;
    
    private ObservableList<Evenement>  data;
    private Evenement eventSelected;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//etape1
    	setCellTable();
    	//etape2
    	getAll();
	}
	
	@FXML
    void refresh() {
		//etape1
		resetEvent(null);
    	//etape2
    	getAll();
    }
	/**
     * correspondence between a table column and a attribute
     */
    private void setCellTable() {
    	colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    	colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
    	colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
    	colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
    	colBudget.setCellValueFactory(new PropertyValueFactory<>("budget"));
    	colValidation.setCellValueFactory(new PropertyValueFactory<>("validation"));
    	colDirigeant.setCellValueFactory(new PropertyValueFactory<>("dirigeant"));
    	
    }
    /**
     * this function calls evenementService to receive all events
     */
	private void getAll() {
		data=FXCollections.observableArrayList(EvenementService.getAll());
		tableEvenement.setItems(data);
	}
	@FXML
    void displayEvenement(MouseEvent event) {
		if (event.getClickCount()>1) {
			if (tableEvenement.getSelectionModel().getSelectedItem()!=null) {
				int index = tableEvenement.getSelectionModel().getSelectedIndex();
				eventSelected=tableEvenement.getItems().get(index);
				TextFieldTitre.setText(eventSelected.getTitre());
				textFieldLieu.setText(eventSelected.getLieu());
				textFieldBudget.setText(""+eventSelected.getBudget());
				dateDebut.setValue(LocalDate.parse(eventSelected.getDateDebut()));
				dateFin.setValue(LocalDate.parse(eventSelected.getDateFin()));
				timeDateDebut.setText(eventSelected.getTimeDebut());
				timeDateFin.setText(eventSelected.getTimeFin());
			}
		}
    }

    @FXML
    void resetEvent(ActionEvent event) {
    	initialColorLabel();
    	eventSelected = null ;
    	TextFieldTitre.setText(null);
    	textFieldLieu.setText(null);
    	textFieldBudget.setText(null);
    	dateDebut.setValue(null);
    	dateFin.setValue(null);
    	timeDateDebut.setText(null);
    	timeDateFin.setText(null);
    }

    @FXML
    void deleteEvent(ActionEvent event) {

    	if(eventSelected !=null) {
    		if(InputValidation.showConfirmation("Are you sure to Delete this event?")
    				.get()== ButtonType.OK) {
    			int i= EvenementService.supprimerEvent(eventSelected.getId());
    			
    	    	if(i==1) {
	    			InputValidation.showAlertInfoWithoutHeaderText("Delete Successfully!");
	    			data.remove(eventSelected);
	    			resetEvent(null);
    	    	}else
        			InputValidation.showAlertErrorWithoutHeaderText("Delete Failure :(");
    		}
    	}else 
    		InputValidation.showAlertErrorWithoutHeaderText("IMPOSSIBLE");
    }

    @FXML
    void updateEvent(ActionEvent event) {
    	if(eventSelected != null && validationChamps()) {
    		
				Evenement e= createEventFromIHM();
				if(e.getBudget() != eventSelected.getBudget())
					e.setValidation("en attente");
				else
					e.setValidation(eventSelected.getValidation());
				e.setId(eventSelected.getId());
				int i=EvenementService.modifierEvent(e);
				if(i==1) {
					InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
					resetEvent(null);
					data.set(data.indexOf(e), e);
				}else
					InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
					
			
    	}
    }

    @FXML
    void addEvent(ActionEvent event) {
    	
			if(validationChamps()) {
				 
				Evenement evenement= createEventFromIHM();
				evenement.setValidation("en attente");
				//call service
				long i=EvenementService.ajouterEvent(evenement);
				if(i!=0) {
					InputValidation.showAlertInfoWithoutHeaderText("Addition has been registered Successfully!");
					evenement.setId(i);
					resetEvent(null);
					data.add(0, evenement);
				}
				else
					InputValidation.showAlertErrorWithoutHeaderText("Registration Failed!");
			}
			
    	
    }
    public Evenement createEventFromIHM() {
    	Evenement e = new Evenement();
    	e.setTitre(TextFieldTitre.getText());
    	e.setLieu(textFieldLieu.getText());
    	e.setBudget(Double.parseDouble(textFieldBudget.getText()));
    	e.setDateDebut(""+dateDebut.getValue());
    	e.setDateFin(""+dateFin.getValue());
    	e.setTimeDebut(timeDateDebut.getText());
    	e.setTimeFin(timeDateFin.getText());
    	e.setDirigeant(App.dirigeant);
    	return e ; 
    	
    }
    
    @FXML
    void searchEvent() {
    	String text= textFieldSearch.getText();
        
    	if(!InputValidation.isFieldNotEmpty(textFieldSearch)) {
    		
    		tableEvenement.setItems(data);
    	}else {

    		tableEvenement.setItems(EvenementService.searchEvents(data, text));
    		
    	}
    } 
    
    /**
     * test the textFields One by one
     * call initialColorLabel()
     * @return
     */
    public boolean validationChamps() {
    	boolean test = true;
    	
    	initialColorLabel();
    	
    	if(!InputValidation.isFieldNotEmpty(TextFieldTitre) ) {
    		labelTitre.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldLieu)) {
    		labelLieu.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(timeDateDebut)|| !InputValidation.isFieldTime(timeDateDebut)||(dateDebut.getValue()==null)) {
    		labelDateDebut.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(timeDateFin) || !InputValidation.isFieldTime(timeDateFin) ||(dateFin.getValue()==null)) {
    		labelDateFin.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	
    	
    	if(!InputValidation.isFieldNotEmpty(textFieldBudget) || !InputValidation.isFieldNumber(textFieldBudget)) {
    		labelBudget.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	return test;
    }
    /**
     * initial the color label 
     */
    public void initialColorLabel() {
    	labelTitre.setStyle("-fx-text-fill:#3F2B63");
    	labelLieu.setStyle("-fx-text-fill:#3F2B63");
    	labelDateDebut.setStyle("-fx-text-fill:#3F2B63");
    	labelDateFin.setStyle("-fx-text-fill:#3F2B63");
    	labelBudget.setStyle("-fx-text-fill:#3F2B63");
    	
    	
    }
    
    
    
 }

	

	
	
	

