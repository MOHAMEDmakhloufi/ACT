package org.fsb.act.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.Association;
import org.fsb.act.entities.CotisationMembre;
import org.fsb.act.entities.Evenement;
import org.fsb.act.entities.FamilleNecessiteuse;
import org.fsb.act.entities.Membre;
import org.fsb.act.entities.PersonneNecessiteuse;
import org.fsb.act.models.Vide;
import org.fsb.act.services.CotisationMembreService;
import org.fsb.act.services.EvenementService;
import org.fsb.act.services.FamilleNecessiteuseService;
import org.fsb.act.services.ModifierAssociationService;
import org.fsb.act.services.PersonneNecessiteuseService;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class CotisationMembreController implements Initializable{
	 @FXML
    private TableColumn<?, ?> colMontant;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colDirigeant;

    @FXML
    private TableView<CotisationMembre> tableView;

    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colDerniereMiseJour;
    
    @FXML
	private TextField textFieldSearch;
    
    @FXML
	private TextField textFieldMontant;
    
	@FXML
	private TextField textFieldMembre;
	
	@FXML
	private Label labelMontant;
	
	@FXML
	private Label labelMembre;
	 
	 private ObservableList<CotisationMembre>  data;
	 //map pour stocker id membre selectiionner
	 private Map<String, Long> map= new HashMap<>();
	 //map pour stocker parametres de fichier recu
	private Map<String, Object> parametres= new HashMap<>();
	
	private CotisationMembre cotisationSelected;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Start get data association and add in map parametres for print recu
		//exemple data association
		Association association= ModifierAssociationService.getDataAssociation();
		parametres.put("titre", association.getTitre());
		parametres.put("email", association.getEmail() );
		parametres.put("telephone", association.getTelephone());
		parametres.put("siege", association.getSiege());
		//fin exemple
		parametres.put("nomDirigeant", App.dirigeant);
		File logo= new File(".//src//main//resources//org//fsb//act//images//logo.jpg");
		parametres.put("logo", logo.getPath());
		String date= LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		parametres.put("date", date);
		//End 
		setCellTable();
		getAll();
	}
	@FXML
 	 void refresh() {
 		getAll();
 	 }
	@FXML
	private void displayCotisation(MouseEvent event) {
		if (event.getClickCount() > 1) {
			if (tableView.getSelectionModel().getSelectedItem() != null) {
				
				clear(null);
				int index= tableView.getSelectionModel().getSelectedIndex();
				cotisationSelected= tableView.getItems().get(index);
				
				if(cotisationSelected.getMembre() != 0) {
					textFieldMembre.setText(cotisationSelected.getPrenom()+"."+cotisationSelected.getNom());
					map.put("Membre", cotisationSelected.getMembre());
				}
				textFieldMontant.setText(""+cotisationSelected.getMontant());

			}
        }
	}
	@FXML
    void clear(ActionEvent event) {
		initialColorLabel();
		textFieldMembre.clear();
		map.put("Membre", (long) -1);
		textFieldMontant.clear();
		cotisationSelected=null;
    }
	@FXML
    void handlePrintRecu(ActionEvent event) {
		if(cotisationSelected !=null) {
			parametres.put("prenom", cotisationSelected.getPrenom());
			parametres.put("nom", cotisationSelected.getNom());
			parametres.put("montant", cotisationSelected.getMontant()+"DT"); 
			 printRecu("recuCotisation", parametres);
		}
    }

    @FXML
    void ajouterCotisation(ActionEvent event) {
    	if(validationChamps()) {
			 
			CotisationMembre cotisationMembre= createCotisationFromIHM();
			
			//call service
			int i=CotisationMembreService.ajouterCotMembre(cotisationMembre);
			if(i!=0) {
				InputValidation.showAlertInfoWithoutHeaderText("Addition has been registered Successfully!");
				
				clear(null);
				getAll();
				parametres.put("prenom", cotisationMembre.getPrenom());
				parametres.put("nom", cotisationMembre.getNom());
				parametres.put("montant", cotisationMembre.getMontant()+"DT"); 
				printRecu("recuCotisation", parametres);
			}
			else
				InputValidation.showAlertErrorWithoutHeaderText("Registration Failed!");
		}
		 
		 
    }
    @FXML
    void updateCotisation(ActionEvent event) {
    	if(cotisationSelected != null && validationChamps()) {
    		
    		CotisationMembre cotisationMembre= createCotisationFromIHM();
			cotisationMembre.setId(cotisationSelected.getId());
			int i=CotisationMembreService.modifierCotisationMembre(cotisationMembre);
			if(i==1) {
				InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
				clear(null);
				getAll();
				parametres.put("prenom", cotisationMembre.getPrenom());
				parametres.put("nom", cotisationMembre.getNom());
				parametres.put("montant", cotisationMembre.getMontant()+"DT"); 
				printRecu("recuCotisation", parametres);
			}else
				InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
				
		
	}
		 
		 
    }
    @FXML
    void deleteCotisation(ActionEvent event) {
    	if(cotisationSelected !=null) {
    		if(InputValidation.showConfirmation("Are you sure to Delete this event?")
    				.get()== ButtonType.OK) {
    			int i= CotisationMembreService.supprimerCotisation(cotisationSelected.getId());
    			
    	    	if(i==1) {
	    			InputValidation.showAlertInfoWithoutHeaderText("Delete Successfully!");
	    			data.remove(cotisationSelected);
	    			clear(null);
    	    	}else
        			InputValidation.showAlertErrorWithoutHeaderText("Delete Failure :(");
    		}
    	}else 
    		InputValidation.showAlertErrorWithoutHeaderText("IMPOSSIBLE");
    }
    
    @FXML
    void search() {
    	String text= textFieldSearch.getText();
        
    	if(!InputValidation.isFieldNotEmpty(textFieldSearch)) {
    		
    		tableView.setItems(data);
    	}else {

    		tableView.setItems(CotisationMembreService.searchCotisations(data, text));
    		
    	}
    } 
    public CotisationMembre createCotisationFromIHM() {
    	CotisationMembre cotisationMembre= new CotisationMembre();
    	cotisationMembre.setMembre(map.get("Membre"));
    	cotisationMembre.setMontant(Double.parseDouble(textFieldMontant.getText()));
    	String date= LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    	cotisationMembre.setDerniereMiseJour(date);
    	cotisationMembre.setDirigeant(App.dirigeant);
    	
    	String[] prenomAndNom= textFieldMembre.getText().split("\\.");
    	
    	cotisationMembre.setPrenom(prenomAndNom[0]);
    	cotisationMembre.setNom(prenomAndNom[1]);
    	return cotisationMembre; 
    	
    }
    void printRecu(String fileName, Map<String, Object> parametres) {
		 
		
		 try {
			 String  sourceFile= App.class.getResource(fileName + ".jrxml").getPath();
			 JasperReport jr = JasperCompileManager.compileReport(sourceFile);
			 	
			 List<Vide> plist = new ArrayList<>();
			 plist.add(new Vide(""));
			 JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
			 JasperPrint jp = JasperFillManager.fillReport(jr, parametres, jcs);
			 
			 JasperViewer viewer= new JasperViewer(jp, false);
			 viewer.setVisible(true);
				
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
    /**
     * if clicked selectionner then open window selectionnerMembre.fxml
     * @param event
     */
    @FXML
    void openTableViewMembres(ActionEvent event) {
    	try {
			Stage stageSelectionnerMembre;
	    	SelectionnerMembreController selectionnerMembreController;
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SelectionnerMembre" + ".fxml"));
			Scene scene;
			scene = new Scene(fxmlLoader.load());
			stageSelectionnerMembre= new Stage();
			stageSelectionnerMembre.setScene(scene);

			selectionnerMembreController=(SelectionnerMembreController) fxmlLoader.getController();
			stageSelectionnerMembre.setOnCloseRequest(e-> selectionnerMembreController.membreSelectionner=null);
			
			stageSelectionnerMembre.showAndWait();
	    	
	    	//get Membre selectionner
	    	Membre membre= selectionnerMembreController.membreSelectionner;
	    	
			if((membre == null && !InputValidation.isFieldNotEmpty(textFieldMembre)  )||
					(membre != null && membre.getId()==-1 )) {
				textFieldMembre.clear();
				map.put("Membre", (long) -1);
			}else if(membre != null) {
				textFieldMembre.setText(membre.getPrenom() +"."+membre.getNom());
				map.put("Membre", membre.getId());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    /**
     * this function calls serviceCotisation to receive all cotisationss
     */
	private void getAll() {
		data=FXCollections.observableArrayList(CotisationMembreService.getAll());
		tableView.setItems(data);
	}
    private void setCellTable() {
    	colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    	colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	colDerniereMiseJour.setCellValueFactory(new PropertyValueFactory<>("derniereMiseJour"));
    	colMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
    	colDirigeant.setCellValueFactory(new PropertyValueFactory<>("dirigeant"));
    }
    
    /**
     * test the textFields One by one
     * call initialColorLabel()
     * @return
     */
    public boolean validationChamps() {
    	boolean test = true;
    	
    	initialColorLabel();
    	
    	if(!InputValidation.isFieldNotEmpty(textFieldMontant) ) {
    		labelMontant.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldMembre)) {
    		labelMembre.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	
    	return test;
    }
    /**
     * initial the color label 
     */
    public void initialColorLabel() {
    	labelMembre.setStyle("-fx-text-fill:#3F2B63");
    	labelMontant.setStyle("-fx-text-fill:#3F2B63");
    	
    	
    	
    }
    
}



/*Map<String, Object> parametres= new HashMap<>();
parametres.put("titre", "ACT");
parametres.put("email","act.act@gmail.com" );
parametres.put("telephone", "+21533665588");
parametres.put("siege", "bizerte");
parametres.put("nomDirigeant", "med.mak");
parametres.put("prenom", "ahmed");
parametres.put("nom", "saoudi");
File logo= new File(".//src//main//resources//org//fsb//act//images//logo.jpg");
parametres.put("logo", logo.getPath());
String date= LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
parametres.put("date", date);

printRecu("recuCotisation", parametres);
*/
