package org.fsb.act.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.PersonneNecessiteuse;
import org.fsb.act.services.PersonneNecessiteuseService;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PersonneNecessiteuseController implements Initializable {
	@FXML
    private TableView<PersonneNecessiteuse> tablePersonnes= new TableView<PersonneNecessiteuse>();   
    @FXML
    private TableColumn<PersonneNecessiteuse, String> colPrenom;
    @FXML
    private TableColumn<PersonneNecessiteuse, String> colNom; 
    @FXML
    private TableColumn<PersonneNecessiteuse, String> colDate;
    @FXML
    private TableColumn<PersonneNecessiteuse, String> colTelephone;
    @FXML
    private TableColumn<PersonneNecessiteuse, String> colAdresse;
    @FXML
    private TableColumn<PersonneNecessiteuse, Integer> colCode;
    @FXML
    private TableColumn<PersonneNecessiteuse, String> colProfession;
    
    @FXML
    private VBox V1;
    @FXML
    private VBox V2;
    @FXML
    private VBox V3;
    
    
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenomPere;
    @FXML
    private TextField prenomGPere;
    @FXML
    private TextField prenomMere;
    @FXML
    private TextField nomMere;
    @FXML
    private Button next1;  
    @FXML
    private Button reset1;
    
    
    @FXML
    private TextField dateN;
    @FXML
    private TextField lieuN;
    @FXML
    private TextField nationalite;
    @FXML
    private TextField gouvernorat;
    @FXML
    private TextField profession;
    @FXML
    private ChoiceBox<String> etatCivil;
    @FXML
    private Button previous2;  
    @FXML
    private Button next2;    
    @FXML
    private Button reset2;
    
    
    @FXML
    private TextField identite;
    @FXML
    private ChoiceBox<String> typeIdentite;
    @FXML
    private TextField email;
    @FXML
    private TextField telephone;
    @FXML
    private TextField adresse;
    @FXML
    private TextField codePostal;
    @FXML
    private Button previous3;  
    @FXML
    private Button reset3;    
    @FXML
    private Button supprimer;
    @FXML
    private Button update;    
    @FXML
    private Button ajouter;
    
    
    
    @FXML
    private Label lprenom;
    @FXML
    private Label lnom;
    @FXML
    private Label lprenomPere;
    @FXML
    private Label lprenomGPere;
    @FXML
    private Label lprenomMere;
    @FXML
    private Label lnomMere;
    @FXML
    private Label ldateN;
    @FXML
    private Label llieuN;
    @FXML
    private Label lnationalite;
    @FXML
    private Label lgouvernorat;
    @FXML
    private Label lprofession;
    @FXML
    private Label letatCivil;
    @FXML
    private Label lidentite;
    @FXML
    private Label ltypeIdentite;
    @FXML
    private Label lemail;
    @FXML
    private Label ltelephone;
    @FXML
    private Label ladresse;
    @FXML
    private Label lcodePostal;
    
    
    @FXML
    private TextField search;
    @FXML
    private Button upload;    
    @FXML
    private Button download;  
    @FXML
    private AnchorPane page;
    
    List<PersonneNecessiteuse> personnes;
    private PersonneNecessiteuse selectedPersonne;
    private ObservableList<PersonneNecessiteuse>  data;
    
    
    private FileChooser fileChooserCsv;
    private File fileUpCsv;
    private File fileDownCsv;
    private Stage stage;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	V1.setVisible(true);
    	V2.setVisible(false);
    	V3.setVisible(false); 
     	setCellTable();
     	data=loadDataPersonneNecessiteuse();
     	etatCivil.setItems(FXCollections.observableArrayList(
     		    "First", "Second", "Third"));
     	typeIdentite.setItems(FXCollections.observableArrayList(
    "First", "Second", "Third"));
     	tablePersonnes.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
     	
     	fileChooserCsv = new FileChooser();
		fileChooserCsv.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Csv files", "*csv")
		);
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
	public ObservableList<PersonneNecessiteuse> loadDataPersonneNecessiteuse() {
		personnes=PersonneNecessiteuseService.getData();
		ObservableList<PersonneNecessiteuse> comp = FXCollections.observableArrayList(personnes);
		tablePersonnes.setEditable(true);
		tablePersonnes.setItems(comp);	
		return comp;
	}
    public void onEdit() {
        // check the table's selected item and get selected item
        if (tablePersonnes.getSelectionModel().getSelectedItem() != null) {
            selectedPersonne= tablePersonnes.getSelectionModel().getSelectedItem();
            prenom.setText(selectedPersonne.getPrenom());
            nom.setText(selectedPersonne.getNom());
            prenomPere.setText(selectedPersonne.getPrenomPere());
            prenomGPere.setText(selectedPersonne.getPrenomGPere());
            nomMere.setText(selectedPersonne.getNomMere());
            prenomMere.setText(selectedPersonne.getPrenomMere());
            prenom.setText(selectedPersonne.getPrenom());
            dateN.setText(selectedPersonne.getDateN());
            lieuN.setText(selectedPersonne.getLieuN());
            nationalite.setText(selectedPersonne.getNationalite());
            gouvernorat.setText(selectedPersonne.getGouvernorat());
            profession.setText(selectedPersonne.getProfession());
            etatCivil.setValue(selectedPersonne.getEtatCivil());
            identite.setText(""+selectedPersonne.getIdentite());
            typeIdentite.setValue(selectedPersonne.getTypeIdentite());
            email.setText(selectedPersonne.getEmail());
            telephone.setText(selectedPersonne.getTelephone());
            adresse.setText(selectedPersonne.getAdresse());
            codePostal.setText(""+selectedPersonne.getCodePostal());         
        }
    }
    
    PersonneNecessiteuse IHMtoPersonneN() {
    	PersonneNecessiteuse p=new PersonneNecessiteuse();
    	p.setPrenom(prenom.getText());
		p.setNom(nom.getText());
		if(!(prenomPere.getText()==null))
			p.setPrenomPere(prenomPere.getText());
		if(!(prenomGPere.getText()==null))
			p.setPrenomGPere(prenomGPere.getText());
		if(!(prenomMere.getText()==null))
			p.setPrenomMere(prenomMere.getText());
		if(!(nomMere.getText()==null))
			p.setNomMere(nomMere.getText());
		if(!(dateN.getText()==""))
			p.setDateN(dateN.getText());;
		if(!(lieuN.getText()==null))
			p.setLieuN(lieuN.getText());
		if(!(nationalite.getText()==null))
			p.setNationalite(nationalite.getText());
		if(!(gouvernorat.getText()==null))
			p.setGouvernorat(gouvernorat.getText());
		if(!(profession.getText()==null))
			p.setProfession(profession.getText());
		if(!(etatCivil.getValue()==null))
			p.setEtatCivil(etatCivil.getValue()); //////////
		if(!(identite.getText()==null))
			p.setIdentite(Integer.valueOf(identite.getText()));
		if(!(typeIdentite.getValue()==null))
			p.setTypeIdentite(typeIdentite.getValue()); //////////
		if(!(email.getText()==null))
			p.setEmail(email.getText());
		if(!(telephone.getText()==null))
			p.setTelephone(telephone.getText());
		if(!(adresse.getText()==null))
			p.setAdresse(adresse.getText());
		if(!(codePostal.getText()==null))
			p.setCodePostal(Integer.valueOf(codePostal.getText()));
		p.setDirigeant(App.dirigeant);
		return p;   	
    }
    
    
    
    
    
    
    @FXML
    void creerPersonneN(ActionEvent event) {
    	if(validationChamps()) {   		    		
    		int i= PersonneNecessiteuseService.ajouterPersonneN(IHMtoPersonneN());
    		if(i==1) {
    			InputValidation.showAlertInfoWithoutHeaderText("Record created successfully!");
    			loadDataPersonneNecessiteuse();
    		}else
    			InputValidation.showAlertErrorWithoutHeaderText("Record create Failure!");
    	}
    }   
    @FXML
    void supprimerPersonneN(ActionEvent event) {
    	if(selectedPersonne !=null) {
    		if(InputValidation.showConfirmation("Are you sure to Delete this person "+selectedPersonne.getPrenom()+"?")
    				.get()== ButtonType.OK) {
    			int i= PersonneNecessiteuseService.supprimerPersonneN(selectedPersonne.getId());
    			
    	    	if(i==1) {
	    			InputValidation.showAlertInfoWithoutHeaderText("Delete Successfully!");
	    			data.remove(selectedPersonne);
	    			clear();
    	    	}else
        			InputValidation.showAlertErrorWithoutHeaderText("Delete Failure :(");
    		}
    	}else 
    		InputValidation.showAlertErrorWithoutHeaderText("IMPOSSIBLE");
    }
    
    
    @FXML
    void updatePersonneN(ActionEvent event) {
    	if(selectedPersonne != null && validationChamps()) {
    		
    			PersonneNecessiteuse p= IHMtoPersonneN();
				p.setId(selectedPersonne.getId());
				int i=PersonneNecessiteuseService.modifierPersonneN(p);
				if(i==1) {
					InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
					clear();
					loadDataPersonneNecessiteuse();
				}else
					InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
					
		
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
    
    
    
    
    
    
    public void clear() {
    	 clear1();
    	 clear2();
    	 clear3();
    }
    public void clear1() {
    	prenom.clear();
        nom.clear();
        prenomPere.clear();
        prenomGPere.clear();
        nomMere.clear();
        prenomMere.clear();
        prenom.clear();
        }
    public void clear2() {
    	 etatCivil.setValue(null);
    	 dateN.clear();
         lieuN.clear();
         nationalite.clear();
         gouvernorat.clear();
         profession.clear();
        }
    public void clear3() {
    	identite.clear();
    	typeIdentite.setValue(null);
    	 email.clear();
         telephone.clear();
         adresse.clear();
         codePostal.clear();  
        }
    public void next1() {
    	V1.setVisible(false);
    	V2.setVisible(true);
    	V3.setVisible(false);    	
    }
    public void next2() {
    	V1.setVisible(false);
    	V2.setVisible(false);
    	V3.setVisible(true);    	
    }
    public void previous2() {
    	V1.setVisible(true);
    	V2.setVisible(false);
    	V3.setVisible(false);    	
    }
    public void previous3() {
    	V1.setVisible(false);
    	V2.setVisible(true);
    	V3.setVisible(false);    	
    }
    
  
    
    
    
    /**
     * if you click btn uploadCsvFile
     * then open choiseBrowser and select file
     * @param event
     */
    @FXML
    void uploadCsvFile(ActionEvent event) {
    	stage= (Stage)page.getScene().getWindow();
    	fileUpCsv = fileChooserCsv.showOpenDialog(stage);
    	if(fileUpCsv != null) {
    		int nbErreur= PersonneNecessiteuseService.mappingCsvToPersonneN(fileUpCsv.getAbsolutePath());
    		InputValidation.showAlertInfoWithoutHeaderText("Members merged with "+nbErreur+" error");
    		loadDataPersonneNecessiteuse();
    	}
    }
    /**
     * if you click btn downloadCsvFile
     * then open choiseBrowser and save file
     * @param event
     */
    @FXML
    void downloadCsvFile(ActionEvent event) {
    	stage= (Stage)page.getScene().getWindow();
    	fileDownCsv=fileChooserCsv.showSaveDialog(stage);
    	if(fileDownCsv != null) {
    		boolean reponse= PersonneNecessiteuseService.mappingPersonneNToCsv(fileDownCsv.getAbsoluteFile(), new ArrayList<PersonneNecessiteuse>(tablePersonnes.getItems() ));
    		if(reponse)
    			InputValidation.showAlertInfoWithoutHeaderText("Successfully Saved!");
    		else
    			InputValidation.showAlertErrorWithoutHeaderText("Failed Saved");
    	}
    }
    
    
    
    
    
    
    public boolean validationChamps() {
    	boolean test = true;
        
    	if(!InputValidation.isFieldNotEmpty(nom)) {
    		lnom.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(prenom)) {
    		lprenom.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	return test;
    }
}
