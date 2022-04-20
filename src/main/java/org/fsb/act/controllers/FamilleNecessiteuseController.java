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
import org.fsb.act.entities.FamilleNecessiteuse;
import org.fsb.act.entities.Membre;
import org.fsb.act.entities.PersonneNecessiteuse;
import org.fsb.act.services.FamilleNecessiteuseService;
import org.fsb.act.services.ServiceMembre;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
public class FamilleNecessiteuseController implements Initializable {
	
	@FXML
	private Label labelPere;
	 
	@FXML
	private Label labelMere;
	
	@FXML
    private VBox vBox;
	
	@FXML
    private TextField searchField;
	
	@FXML
    private TextField textFieldMere;
	
	@FXML
    private TextField textFieldPere;
	
	@FXML
    private TextField textFieldFils1;
	
	@FXML
    private TableColumn<?, ?> colPere;

    @FXML
    private TableColumn<?, ?> colMere;

    @FXML
    private TableColumn<?, ?> colNbFils;

    @FXML
    private TableColumn<?, ?> colAdresse;

    @FXML
    private TableView<FamilleNecessiteuse> tableView;

	private Map<GridPane, Long> map= new HashMap<>();
	private Stage stageSelectionnerPersonne;
	private SelectionnerPersonneController selectionnerPersonneController;
	private ObservableList<FamilleNecessiteuse>  data;
	private FamilleNecessiteuse familleNecessiteuse;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//load the file selectionnerPersonneN 
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SelectionnerPersonneN" + ".fxml"));
			Scene scene= new Scene(fxmlLoader.load());
			stageSelectionnerPersonne= new Stage();
			stageSelectionnerPersonne.setScene(scene);
			
			selectionnerPersonneController=(SelectionnerPersonneController) fxmlLoader.getController();
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//End load the file selectionnerPersonneN
		
		//etape1
		setCellTable();
		//etape2
		getAll();
	}
	/**
     * this function calls serviceMembre to receive all members
     */
	private void getAll() {
		data=FXCollections.observableArrayList(FamilleNecessiteuseService.getAll());
		tableView.setItems(data);
	}
	/**
	 * 
	 * if select row then display this famille
	 * @param event
	 */
	@FXML
    void displayFamille(MouseEvent event) {
		if (event.getClickCount() > 1) {
			if (tableView.getSelectionModel().getSelectedItem() != null) {
				if(familleNecessiteuse != null)
					clear(null);
				int index= tableView.getSelectionModel().getSelectedIndex();
				familleNecessiteuse= tableView.getItems().get(index);
				
				//pere
				GridPane pere=(GridPane)vBox.getChildren().get(1);
				map.put(pere, familleNecessiteuse.getPere().getId());
				textFieldPere.setText(familleNecessiteuse.getPere().getPrenom() +" "+ familleNecessiteuse.getPere().getNom());
				//mere
				GridPane mere=(GridPane)vBox.getChildren().get(3);
				map.put(mere, familleNecessiteuse.getMere().getId());
				textFieldMere.setText(familleNecessiteuse.getMere().getPrenom() +" "+ familleNecessiteuse.getMere().getNom());
				//Fils
				if(familleNecessiteuse.getNbFils()> 0) {
					//1er fils
					GridPane fils1=(GridPane)vBox.getChildren().get(5);
					map.put(fils1, familleNecessiteuse.getListFils().get(0).getId());
					textFieldFils1.setText(familleNecessiteuse.getListFils().get(0).getPrenom() +" "+ familleNecessiteuse.getListFils().get(0).getNom());
					//autre Fils
					
					for(int i=1; i<familleNecessiteuse.getNbFils(); i++) {
						
						GridPane newGridPane= createNewFils();
						map.put(newGridPane, familleNecessiteuse.getListFils().get(i).getId());
						TextField textField = (TextField)((AnchorPane)(newGridPane.getChildren().get(0))).getChildren().get(0);
						textField.setText(familleNecessiteuse.getListFils().get(i).getPrenom() +" "+ familleNecessiteuse.getListFils().get(i).getNom());
						vBox.getChildren().add(vBox.getChildren().size() - 1,newGridPane);
				    	vBox.setMargin(newGridPane,  new Insets( 20, 0, 0, 0 ));
					}
				}
				//((TextField)((AnchorPane)pere.getChildren().get(0)).getChildren().get(0)).setText(familleNecessiteuse.getPere().getPrenom());
			}
        }
    }
	/**
	 * If you click btn creer  then create new Famille
	 * @param event
	 */
	@FXML
    void addFamille(ActionEvent event) {
		if(validationChamps()) {
			 
			FamilleNecessiteuse fn= createFamilleFromIHM();
			//call service
			long i=FamilleNecessiteuseService.ajouterFamille(fn);
			if(i!=0) {
				InputValidation.showAlertInfoWithoutHeaderText("Addition has been registered Successfully!");
				
				clear(null);
				getAll();
			}
			else
				InputValidation.showAlertErrorWithoutHeaderText("Registration Failed!");
		}
    }
	/**
	 * if you click btn reset then empty the fields
	 * @param event
	 */
    @FXML
    void clear(ActionEvent event) {
    	initialColorLabel();
    	
    	//pere
		GridPane pere=(GridPane)vBox.getChildren().get(1);
		map.put(pere, (long) -1);
		textFieldPere.setText(null);
		//mere
		GridPane mere=(GridPane)vBox.getChildren().get(3);
		map.put(mere, (long) -1);
		textFieldMere.setText(null);
		//Fils
		if(familleNecessiteuse.getNbFils()> 0) {
			//1er fils
			GridPane fils1=(GridPane)vBox.getChildren().get(5);
			map.put(fils1, (long) -1);
			textFieldFils1.setText(null);
			//autre Fils
			List<GridPane> list= new ArrayList<>();
			for(int i=6; i<vBox.getChildren().size()-1; i++) {
				
				list.add((GridPane) vBox.getChildren().get(i));
				map.remove(vBox.getChildren().get(i));
				
			}
			vBox.getChildren().removeAll(list);
		}
		familleNecessiteuse=null;
    }
    /**
     * if you click btn update then update the famille selected
     * @param event
     */
    @FXML
    void updateFamille(ActionEvent event) {
    	if(familleNecessiteuse != null && validationChamps()) {
    		
				FamilleNecessiteuse fn= createFamilleFromIHM();
				fn.setId(familleNecessiteuse.getId());
				int i=FamilleNecessiteuseService.modifierFamille(fn);
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
     * create object famille
     * @return
     */
    public FamilleNecessiteuse createFamilleFromIHM(){
    	FamilleNecessiteuse fn= new FamilleNecessiteuse();
    	fn.setId(familleNecessiteuse.getId());
    	//pere
    	long pereId= map.get(vBox.getChildren().get(1));
    	fn.setPere(new PersonneNecessiteuse(pereId));
    	//mere
    	long mereId = map.get(vBox.getChildren().get(3));
    	fn.setMere(new PersonneNecessiteuse(mereId));
    	//1er fils
    	if(InputValidation.isFieldNotEmpty(textFieldFils1)) {
    		
    		long fils1= map.get(vBox.getChildren().get(5));
    		System.out.println("hello "+fils1);
    		fn.getListFils().add(new PersonneNecessiteuse(fils1));
    	}
    	// other fils
    	for(int i=6; i<vBox.getChildren().size()-1; i++) {
    		long fils= map.get(vBox.getChildren().get(i));
    		fn.getListFils().add(new PersonneNecessiteuse(fils));
    	}
    	
    	return fn;
    }
    /**
     * if you click btn delete then delete the famille selected
     * @param event
     */
    @FXML
    void deleteFamille(ActionEvent event) {
    	if(familleNecessiteuse != null) {
    		if(InputValidation.showConfirmation("Are you sure to Delete this Famille ?")
    				.get()== ButtonType.OK) {
    			int i= FamilleNecessiteuseService.supprimerFamille(familleNecessiteuse.getId());
    			
    	    	if(i==1) {
	    			InputValidation.showAlertInfoWithoutHeaderText("Delete Successfully!");
	    			data.remove(familleNecessiteuse);
	    			clear(null);
    	    	}else
        			InputValidation.showAlertErrorWithoutHeaderText("Delete Failure :(");
    		}
    	}else 
    		InputValidation.showAlertErrorWithoutHeaderText("IMPOSSIBLE");
    }
    /**
     * test the textFields One by one
     * call initialColorLabel()
     * @return
     */
    public boolean validationChamps() {
    	boolean test = true;
    	
    	initialColorLabel();
    	
    	if(!InputValidation.isFieldNotEmpty(textFieldPere) ) {
    		labelPere.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldMere)) {
    		labelMere.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	return test;
    }
    /**
     * initial the color label 
     */
    public void initialColorLabel() {
    	labelPere.setStyle("-fx-text-fill:#3F2B63");
    	labelMere.setStyle("-fx-text-fill:#3F2B63");
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
	 * if you are clicked + then create new gridPane fils
	 * @param event
	 */
    @FXML
    public void addNewFils(ActionEvent event) {
    	Button bClicked= (Button) event.getSource();
    	GridPane gridPane= (GridPane) bClicked.getParent().getParent();
    	int index =vBox.getChildren().indexOf(gridPane);
    	
    	GridPane newGridPane= createNewFils();
    	
    	
    	vBox.getChildren().add(index + 1, newGridPane);
    	vBox.setMargin(newGridPane,  new Insets( 20, 0, 0, 0 ));
    	
    	Button btnSelectionner=(Button) ((AnchorPane)(newGridPane.getChildren().get(2))).getChildren().get(0);
    	ActionEvent e= new ActionEvent(btnSelectionner, null);
    	
    	openTableViewPN(e);
    	
    }
    
    @FXML
    void openTableViewPN(ActionEvent event) {
    	
    	stageSelectionnerPersonne.showAndWait();
    	//get buttion selectionner and gridPane and the textField
    	Button bClicked= (Button) event.getSource();
    	GridPane gridPane= (GridPane) bClicked.getParent().getParent();
    	TextField textField = (TextField)((AnchorPane)(gridPane.getChildren().get(0))).getChildren().get(0);
    	//get personne selectionner
    	PersonneNecessiteuse pN= selectionnerPersonneController.personneNecessiteuse;
    	List<Integer> list= new ArrayList<>(Arrays.asList(1, 3, 5));
		if((pN == null && !InputValidation.isFieldNotEmpty(textField) && !list.contains(vBox.getChildren().indexOf(gridPane)) )||
				(pN != null && pN.getId()==-1 &&  !list.contains(vBox.getChildren().indexOf(gridPane)))) {
			vBox.getChildren().remove(gridPane);
			map.remove(gridPane);
		}else if(pN != null && pN.getId()==-1 && list.contains(vBox.getChildren().indexOf(gridPane))) {
			textField.setText(null);
			map.put(gridPane, (long)-1);
		}else if(pN != null) {
			textField.setText(pN.getPrenom() +" "+pN.getNom());
			map.put(gridPane, pN.getId());
		}
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
    /**
     * this methode create new gridPane complet
     * @return
     */
    public GridPane createNewFils() {
		
		//create object gridPane
		GridPane newGridPane= new GridPane();
		//create three column with Percentage Sizing
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(60);
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(10);
	    ColumnConstraints column3 = new ColumnConstraints();
	    column3.setPercentWidth(30);
	    //add this columns a gridPane
	    newGridPane.getColumnConstraints().addAll(column1, column2, column3);
	    //set the Hgab
	    newGridPane.setHgap(2);
	    //create three anchorPane
		    //ap1
		    AnchorPane ap1= new AnchorPane();
		    TextField textField= new TextField();
		    textField.setPromptText("vide");
		    textField.setDisable(true);
		    AnchorPane.setTopAnchor(textField, 0.0);
		    AnchorPane.setLeftAnchor(textField, 0.0);
		    AnchorPane.setRightAnchor(textField, 0.0);
		    AnchorPane.setBottomAnchor(textField, 0.0);
		    ap1.getChildren().add(textField);
		    //ap2
		    AnchorPane ap2= new AnchorPane();
		    Button plus= new Button();
		    plus.setText("+");
		    plus.setOnAction(e -> addNewFils(e));
		    plus.setStyle("-fx-font-size: 13.5px;");
		    //plus.setOnAction(FamilleNecessiteuseController::addNewFils);
		    AnchorPane.setTopAnchor(plus, 0.0);
		    AnchorPane.setLeftAnchor(plus, 0.0);
		    AnchorPane.setRightAnchor(plus, 0.0);
		    AnchorPane.setBottomAnchor(plus, 0.0);
		    ap2.getChildren().add(plus);
		    //ap3
		    AnchorPane ap3= new AnchorPane();
		    Button selectionner= new Button();
		    selectionner.setOnAction(e -> openTableViewPN(e));
		    selectionner.setText("Selectionner");
		    AnchorPane.setTopAnchor(selectionner, 0.0);
		    AnchorPane.setLeftAnchor(selectionner, 0.0);
		    AnchorPane.setRightAnchor(selectionner, 0.0);
		    AnchorPane.setBottomAnchor(selectionner, 0.0);
		    ap3.getChildren().add(selectionner);
	    
	    //Places the anchorPane at  row and  column
	    
	    newGridPane.add(ap1, 0,0);
	    newGridPane.add(ap2, 1,0);
	    newGridPane.add(ap3, 2,0);
	    
		return newGridPane;
	}
}
