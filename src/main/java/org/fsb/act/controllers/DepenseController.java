package org.fsb.act.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.Depense;
import org.fsb.act.entities.FamilleNecessiteuse;
import org.fsb.act.entities.Membre;
import org.fsb.act.entities.PersonneNecessiteuse;
import org.fsb.act.services.DepenseService;
import org.fsb.act.services.FamilleNecessiteuseService;
import org.fsb.act.services.PersonneNecessiteuseService;
import org.fsb.act.services.ServiceMembre;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DepenseController implements Initializable{
	@FXML
    private RadioButton radioFamille;

    @FXML
    private RadioButton radioPersonne;

    @FXML
    private RadioButton radioAutre;

    @FXML
    private GridPane gridPersonne;

    @FXML
    private GridPane gridFamille;

    @FXML
    private TableView<Depense> tableDepenses;

    @FXML
    private TableColumn<?, ?> colDirigeant;

    @FXML
    private TableColumn<?, ?> colMontant;
    
    @FXML
    private TableColumn<?, ?> colDerniereMiseJour;

    @FXML
    private TableColumn<?, ?> colDestination;

    @FXML
    private Label labelMontant;
    
    @FXML
    private Label labelNecessiteuse;

    @FXML
    private TextField textFieldMontant;
    
    @FXML
    private TextField textFieldFamille;
    
    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldPersonne;
    
    @FXML
    private TextField searchField;
    
    private Depense depenseSelected;
    private ObservableList<Depense>  data;
    private Map<String, Long> map= new HashMap<>();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//etape0
		handleRadioPersonne(null);
    	//etape1
    	setCellTable();
    	//etape2
    	getAll();
		
	}
	/**
     * this function calls serviceMembre to receive all depenses
     */
	private void getAll() {
		data=FXCollections.observableArrayList(DepenseService.getAll());
		tableDepenses.setItems(data);
	}
	@FXML
    void refresh() {
		//etape1
		clear(null);
    	//etape2
    	getAll();
    }
	/**
	 * 
	 * if select row then display this depense
	 * @param event
	 */
	@FXML
    void displayDepense(MouseEvent event) {
		
		if (event.getClickCount() > 1) {
			if (tableDepenses.getSelectionModel().getSelectedItem() != null) {
				
				clear(null);
				int index= tableDepenses.getSelectionModel().getSelectedIndex();
				depenseSelected= tableDepenses.getItems().get(index);
				
				if(depenseSelected.getFamille() != 0) {
					FamilleNecessiteuse fn= FamilleNecessiteuseService.getOneById(depenseSelected.getFamille());
					String nomPere= fn.getPere().getPrenom() + fn.getPere().getNom();
					String nomMere = fn.getMere().getPrenom() + fn.getMere().getNom();
					textFieldFamille.setText(nomPere + " & "+ nomMere);
					map.put("Famille", depenseSelected.getFamille());
					handleRadioFamille(null);
					
				}else if(depenseSelected.getPersonneNecessiteuse() !=0) {
					PersonneNecessiteuse pn= PersonneNecessiteuseService.getById(depenseSelected.getPersonneNecessiteuse());
			
					textFieldPersonne.setText(pn.getPrenom() +" "+pn.getNom());
					map.put("Personne", depenseSelected.getPersonneNecessiteuse());
					handleRadioPersonne(null);
				}else if(!depenseSelected.getDescription().isEmpty() && depenseSelected.getDescription() != null) {
					textFieldDescription.setText(depenseSelected.getDescription());
					handleRadioAutre(null);
				}
				textFieldMontant.setText(""+depenseSelected.getMontant());

			}
        }
    }
    /**
     * if you click the radio button then her grid pane personne is visible 
     * @param event
     */
    @FXML
    void handleRadioPersonne(ActionEvent event) {
    	//select radio
    	radioPersonne.setSelected(true);
    	radioFamille.setSelected(false);
    	radioAutre.setSelected(false);
    	//select correct node
    	gridPersonne.setVisible(true);
    	gridFamille.setVisible(false);
    	textFieldDescription.setVisible(false);
    	//clear
    	emptyGrid(gridFamille);
    	textFieldDescription.setText(null);
    	
    }
    
    /**
     * if you click the radio button then her grid pane famille is visible 
     * @param event
     */
    @FXML
    void handleRadioFamille(ActionEvent event) {
    	//select radio
    	radioPersonne.setSelected(false);
    	radioFamille.setSelected(true);
    	radioAutre.setSelected(false);
    	//select correct node
    	gridPersonne.setVisible(false);
    	gridFamille.setVisible(true);
    	textFieldDescription.setVisible(false);
    	//clear
    	emptyGrid(gridPersonne);
    	textFieldDescription.setText(null);
    }
    
    /**
     * if you click the radio button then her grid pane autre is visible 
     * @param event
     */
    @FXML
    void handleRadioAutre(ActionEvent event) {
    	//select radio
    	radioPersonne.setSelected(false);
    	radioFamille.setSelected(false);
    	radioAutre.setSelected(true);
    	//select correct node
    	gridPersonne.setVisible(false);
    	gridFamille.setVisible(false);
    	textFieldDescription.setVisible(true);
    	//clear
    	emptyGrid(gridPersonne);
    	emptyGrid(gridFamille);
    }
    
    /**
     * If you click btn reset,
     * empty all fields
     * call initialColorLabel()
     * @param event
     */
    @FXML
    void clear(ActionEvent event) {
    	
    	initialColorLabel();
    	depenseSelected= null;
    	emptyGrid(gridFamille);
    	emptyGrid(gridPersonne);
    	textFieldDescription.setText(null);
    	textFieldMontant.setText(null);
    	
    	map.put("Famille", (long) -1);
    	map.put("Personne", (long) -1);
    }
    /**
     * this method clear an gridpane 
     * @param grid
     */
    void emptyGrid(GridPane grid) {
    	TextField txf= (TextField) ((AnchorPane)(grid.getChildren().get(0))).getChildren().get(0);
    	txf.setText(null);
    }
    /**
     * If you click btn Supprimer
     * we start to delete the depense
     * @param event
     */
    @FXML
    void supprimerDepense(ActionEvent event) {
    	if(depenseSelected !=null) {
    		if(InputValidation.showConfirmation("Are you sure to Delete this depense?")
    				.get()== ButtonType.OK) {
    			int i= DepenseService.deleteDepense(depenseSelected.getId());
    			
    	    	if(i==1) {
	    			InputValidation.showAlertInfoWithoutHeaderText("Delete Successfully!");
	    			data.remove(depenseSelected);
	    			clear(null);
    	    	}else
        			InputValidation.showAlertErrorWithoutHeaderText("Delete Failure :(");
    		}
    	}else 
    		InputValidation.showAlertErrorWithoutHeaderText("IMPOSSIBLE");
    }
    /**
     * If you click btn Update
     * we start to update the depense
     * @param event
     */
    @FXML
    void updateDepense(ActionEvent event) {
    	if(depenseSelected != null && validationChamps()) {
		
    		Depense depense= createDepenseFromIhm();
    		depense.setId(depenseSelected.getId());
			//call service
			long i=DepenseService.modifierDepense(depense);
			if(i==1) {
				InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
				clear(null);
				getAll();
			}else
				InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
				
		
    	}else 
    		InputValidation.showAlertErrorWithoutHeaderText("IMPOSSIBLE");
    }
    /**
     * If you click btn Ajouter
     * we start to add the depense
     * @param event
     */
    @FXML
    void addDepense(ActionEvent event) {
    	if(validationChamps()) {
			 
			Depense depense= createDepenseFromIhm();
			//call service
			int i=DepenseService.ajouterDepense(depense);
			if(i==1) {
				InputValidation.showAlertInfoWithoutHeaderText("Addition has been registered Successfully!");
				
				clear(null);
				getAll();
			}
			else
				InputValidation.showAlertErrorWithoutHeaderText("Registration Failed!");
		}else 
    		InputValidation.showAlertErrorWithoutHeaderText("ERREUR");
    }
    /**
     * create instance depense from ihm
     */
    public Depense createDepenseFromIhm() {
    	Depense depense= new Depense();
    	if(radioPersonne.isSelected() ) {
    		depense.setPersonneNecessiteuse(map.get("Personne"));
    	}
    	if(radioFamille.isSelected() ) {
    		depense.setFamille(map.get("Famille"));
    	}
    	if(radioAutre.isSelected() ) {
    		depense.setDescription(textFieldDescription.getText());
    	}
    	depense.setMontant(Double.parseDouble(textFieldMontant.getText()));
    	depense.setDirigeant(App.dirigeant);
    	
    	return depense;
    }
    /**
     * If you write in the search field  
     * then we find for matches depenses
     * @param event
     */
    @FXML
    void searchDepenses() {
    	String text= searchField.getText();
        
    	if(!InputValidation.isFieldNotEmpty(searchField)) {
    		
    		tableDepenses.setItems(data);
    	}else {

    		tableDepenses.setItems(DepenseService.searchDepenses(data, text));
    		
    	}
    }
    @FXML
    void openTableViewPN(ActionEvent event) {
    	
		try {
			Stage stageSelectionnerPersonne;
	    	SelectionnerPersonneController selectionnerPersonneController;
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SelectionnerPersonneN" + ".fxml"));
			Scene scene;
			scene = new Scene(fxmlLoader.load());
			stageSelectionnerPersonne= new Stage();
			stageSelectionnerPersonne.setScene(scene);

			selectionnerPersonneController=(SelectionnerPersonneController) fxmlLoader.getController();
			stageSelectionnerPersonne.setOnCloseRequest(e-> selectionnerPersonneController.personneNecessiteuse=null);
			
			stageSelectionnerPersonne.showAndWait();
			stageSelectionnerPersonne.setResizable(false);
	    	//get buttion selectionner and gridPane and the textField
	    	Button bClicked= (Button) event.getSource();
	    	GridPane gridPane= (GridPane) bClicked.getParent().getParent();
	    	TextField textField = (TextField)((AnchorPane)(gridPane.getChildren().get(0))).getChildren().get(0);
	    	//get personne selectionner
	    	PersonneNecessiteuse pN= selectionnerPersonneController.personneNecessiteuse;
	    	
			if((pN == null && !InputValidation.isFieldNotEmpty(textField)  )||
					(pN != null && pN.getId()==-1 )) {
				textField.setText(null);
				map.put("Personne", (long) -1);
			}else if(pN != null) {
				textField.setText(pN.getPrenom() +" "+pN.getNom());
				map.put("Personne", pN.getId());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }

    @FXML
    void openTableViewFN(ActionEvent event) {
    	try {
			Stage stageSelectionnerFamille;
	    	SelectionnerFamilleController selectionnerFamilleController;
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SelectionnerFamille" + ".fxml"));
			Scene scene;
			scene = new Scene(fxmlLoader.load());
			stageSelectionnerFamille= new Stage();
			stageSelectionnerFamille.setScene(scene);

			selectionnerFamilleController=(SelectionnerFamilleController) fxmlLoader.getController();
			stageSelectionnerFamille.setOnCloseRequest(e-> selectionnerFamilleController.familleNecessiteuse=null);
			
			stageSelectionnerFamille.showAndWait();
	    	stageSelectionnerFamille.setResizable(false);
	    	//get famille selectionner
	    	FamilleNecessiteuse fN= selectionnerFamilleController.familleNecessiteuse;
	    	
			if((fN == null && !InputValidation.isFieldNotEmpty(textFieldFamille)  )||
					(fN != null && fN.getId()==-1 )) {
				textFieldFamille.setText(null);
				map.put("Famille", (long) -1);
			}else if(fN != null) {
				textFieldFamille.setText(fN.getPere().getPrenom() +" "+fN.getPere().getNom()+" et "+fN.getMere().getPrenom() +" "+fN.getMere().getNom());
				map.put("Famille", fN.getId());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
    	if(radioPersonne.isSelected() && !InputValidation.isFieldNotEmpty(textFieldPersonne)) {
    		labelNecessiteuse.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(radioFamille.isSelected() && !InputValidation.isFieldNotEmpty(textFieldFamille)) {
    		labelNecessiteuse.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(radioAutre.isSelected() && !InputValidation.isFieldNotEmpty(textFieldDescription)) {
    		labelNecessiteuse.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	if(!InputValidation.isFieldNotEmpty(textFieldMontant) || !InputValidation.isFieldNumber(textFieldMontant)) {
    		labelMontant.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	return test;
    }
    /**
     * initial the color label 
     */
    public void initialColorLabel() {
    	
    	labelNecessiteuse.setStyle("-fx-text-fill:#3F2B63");
    	labelMontant.setStyle("-fx-text-fill:#3F2B63");
    }
    /**
     * correspondence between a table column and a attribute
     */
    private void setCellTable() {
    	colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
    	colDerniereMiseJour.setCellValueFactory(new PropertyValueFactory<>("derniereMiseJour"));
    	colMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
    	colDirigeant.setCellValueFactory(new PropertyValueFactory<>("dirigeant"));
    }

}
