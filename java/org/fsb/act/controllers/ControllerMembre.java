package org.fsb.act.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.Membre;
import org.fsb.act.services.ServiceMembre;
import org.fsb.act.validation.InputValidation;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerMembre implements Initializable{
	
    @FXML
    private TableView<Membre> tableMembres;
    
    @FXML
    private TableColumn<?, ?> colProfession;

    @FXML
    private TableColumn<?, ?> colIdentite;
    
    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colTelephone;
    
    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colD_N;
    
    @FXML
    private TableColumn<?, ?> colDirigeant;
    
    @FXML
    private TableColumn<?, ?> colTypePiece;
    
    @FXML
    private Button btnChoisirCopie;
    
    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnUpImage;
    
    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSupprimer;
    
    @FXML
    private Button btnPrintMembres;
    
    @FXML
    private Label labelNom;
    
    @FXML
    private Label labelPrenom;

    @FXML
    private Label labelEmail;
    
    @FXML
    private Label LabelDate;

    @FXML
    private Label labelProfession;
    
    @FXML
    private Label labelNumeroPiece;
    
    @FXML
    private Label labelTelephone;
    
    @FXML
    private TextField searchField;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;
    
    @FXML
    private TextField profession;

    @FXML
    private TextField pieceIdentiteNum;

    @FXML
    private TextField telephone;
    
    @FXML
    private DatePicker dateNaissance;

    @FXML
    private ComboBox<String> typePiece;

    @FXML
    private ImageView image;
    
    @FXML
    private ScrollPane scrollPane;
    
    //Start my own attributes
    private FileChooser fileChooserCsv;
    private FileChooser fileChooserImage;
    private File imageUser;
    private File copiePiece;
    
    private File fileUpCsv;
    private File fileDownCsv;
    private Stage stage;
    private Membre membreSelected;
	private ObservableList<Membre>  data;
    //End my own attributes

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img= new Image("file:.//src//main//resources//org//fsb//act//images//userDefault.jpg", image.getFitWidth(), image.getFitHeight(), true, true);
    	image.setImage(img);
    	//etape1
    	initialiserComBoxTypePiece();
    	//etape2
    	setCellTable();
    	//etape3
    	getAll();
    	//autre etapes
    	fileChooserCsv = new FileChooser();
		fileChooserCsv.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Csv files", "*csv")
		);
		fileChooserImage = new FileChooser();
		fileChooserImage.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All files", "* *"),
				new FileChooser.ExtensionFilter("images", "*png", "*jpg", "*gif")
		);
		
	}
    
    /**
     * If you click btn upload image, 
     * we open the browser to upload the image member
     * @param event
     */
    @FXML
    void uploadImage(ActionEvent event) {
    	stage= (Stage) scrollPane.getScene().getWindow();
    	imageUser= fileChooserImage.showOpenDialog(stage);
    	if(imageUser !=null) {
    		Image img= new Image(imageUser.getAbsoluteFile().toURI().toString(), image.getFitWidth(), image.getFitHeight(), true, true);
    		image.setImage(img);
    	}
    }
    
    /**
     * If you click btn ChoisirCopie ,
     *  we open the browser to upload the copy
     * @param event
     */
    @FXML
    void choisirCopie(ActionEvent event) {
    	stage= (Stage) scrollPane.getScene().getWindow();
    	copiePiece= fileChooserImage.showOpenDialog(stage);
    	if(copiePiece != null) {
    		openImage(copiePiece);
            
    	}
    }
    /**
     * open image in new stage
     * @param file
     */
    public void openImage(File file){
    	Image img= new Image(file.getAbsoluteFile().toURI().toString(), 0, 0, true, true);
		ImageView imageView = new ImageView(img);
		
		FlowPane root = new FlowPane();
        root.setPadding(new Insets(20));
        root.getChildren().add(imageView);
        
        Scene feneteCopiePiece= new Scene(root);
        Stage stageCopiePiece= new Stage();
        stageCopiePiece.setScene(feneteCopiePiece);
        stageCopiePiece.showAndWait();
    }
    /**
     * show copie piece with call openImage
     */
    @FXML
    void showCopiePiece() {
    	
    	
    	if(copiePiece != null)
    		openImage(copiePiece);
    }
    /**
     * If you click btn reset,
     * empty all fields
     * call initialColorLabel()
     * @param event
     */
    @FXML
    void clear() {
    	initialColorLabel();
    	membreSelected=null;
    	prenom.setText(null);
    	nom.setText(null);
    	dateNaissance.setValue(null);
    	email.setText(null);
    	profession.setText(null);
    	pieceIdentiteNum.setText(null);
    	telephone.setText(null);
    	typePiece.getSelectionModel().select(0);
    	Image img= new Image("file:.//src//main//resources//org//fsb//act//images//userDefault.jpg", image.getFitWidth(), image.getFitHeight(), true, true);
    	image.setImage(img);
    	imageUser= null;
    	copiePiece= null;
    	
    }
    
    /**
     * If you click btn Supprimer
     * we start to delete the member
     * @param event
     */
    @FXML
    void supprimerMembre(ActionEvent event) {
    	if(membreSelected !=null) {
    		if(InputValidation.showConfirmation("Are you sure to Delete this membre "+membreSelected.getPrenom()+"?")
    				.get()== ButtonType.OK) {
    			int i= ServiceMembre.supprimerMembre(membreSelected.getId());
    			
    	    	if(i==1) {
	    			InputValidation.showAlertInfoWithoutHeaderText("Delete Successfully!");
	    			data.remove(membreSelected);
	    			clear();
    	    	}else
        			InputValidation.showAlertErrorWithoutHeaderText("Delete Failure :(");
    		}
    	}else 
    		InputValidation.showAlertErrorWithoutHeaderText("IMPOSSIBLE");
    }
    
    /**
     * If you click btn Update
     * we start to update the member
     * @param event
     */
    @FXML
    void updateMembre(ActionEvent event) {
    	if(membreSelected != null && validationChamps()) {
    		try {
				Membre membre= createMembreFromIHM();
				membre.setId(membreSelected.getId());
				int i=ServiceMembre.modifierMembre(membre);
				if(i==1) {
					InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
					clear();
					data.set(data.indexOf(membre), membre);
				}else
					InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
			}
    	}
    }
    /**
     * If you click btn Ajouter
     * we start to add the member
     * @param event
     */
    @FXML
    void ajouterMembre(ActionEvent event) {
    	
    	
		try {
			if(validationChamps()) {
				 
				Membre membre= createMembreFromIHM();
				//call service
				long i=ServiceMembre.ajouterMembre(membre);
				if(i!=0) {
					InputValidation.showAlertInfoWithoutHeaderText("Addition has been registered Successfully!");
					membre.setId(i);
					clear();
					data.add(0, membre);
				}
				else
					InputValidation.showAlertErrorWithoutHeaderText("Registration Failed!");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			InputValidation.showAlertErrorWithoutHeaderText("Registration Failed!");
		}
    	
    }
    public Membre createMembreFromIHM() throws IOException {
    	Membre membre= new Membre();
    	FileInputStream fis= new FileInputStream(image.getImage().getUrl().split(":")[1]);
		membre.setImage(fis.readAllBytes());
		
		if(copiePiece !=null) {
			fis= new FileInputStream(copiePiece);
			membre.setCopiePiece(fis.readAllBytes());
		}
		membre.setPrenom(prenom.getText());
		membre.setNom(nom.getText());
		membre.setDateNaissance(dateNaissance.getValue()+"");
		membre.setProfession(profession.getText());
		membre.setEmail(email.getText());
		membre.setTelephone(telephone.getText());
		membre.setNumeroIdentite(Long.parseLong(pieceIdentiteNum.getText()));
		int indexType= typePiece.getSelectionModel().getSelectedIndex();
		membre.setTypePiece(typePiece.getItems().get(indexType));
		membre.setDirigeant(App.dirigeant);
		return membre;
    }
    /**
     * test the textFields One by one
     * call initialColorLabel()
     * @return
     */
    public boolean validationChamps() {
    	boolean test = true;
    	
    	initialColorLabel();
    	
    	if(!InputValidation.isFieldNotEmpty(prenom) ) {
    		labelPrenom.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(nom)) {
    		labelNom.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(profession)) {
    		labelProfession.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	if(dateNaissance.getValue() == null) {
    		
    		LabelDate.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	if(!InputValidation.isFieldNotEmpty(email) || !InputValidation.isFieldEmail(email)) {
    		labelEmail.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(telephone)) {
    		labelTelephone.setStyle("-fx-text-fill:red");
    		test=false;
    		
    	}
    	if(!InputValidation.isFieldNotEmpty(pieceIdentiteNum) || !InputValidation.isFieldNumber(pieceIdentiteNum)) {
    		labelNumeroPiece.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	return test;
    }
    /**
     * initial the color label 
     */
    public void initialColorLabel() {
    	labelPrenom.setStyle("-fx-text-fill:#3F2B63");
    	LabelDate.setStyle("-fx-text-fill:#3F2B63");
    	labelNom.setStyle("-fx-text-fill:#3F2B63");
    	labelProfession.setStyle("-fx-text-fill:#3F2B63");
    	labelEmail.setStyle("-fx-text-fill:#3F2B63");
    	labelTelephone.setStyle("-fx-text-fill:#3F2B63");
    	labelNumeroPiece.setStyle("-fx-text-fill:#3F2B63");
    	
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
    
    /**
     * if you click on a table row
     * then display the row in these fields
     * call initialColorLabel()
     */
    @FXML
    void displayMembre() {
    	
    	initialColorLabel();
    	clear();
    	int index= tableMembres.getSelectionModel().getSelectedIndex();
    	membreSelected= tableMembres.getItems().get(index);
    	
    	prenom.setText(membreSelected.getPrenom());
    	nom.setText(membreSelected.getNom());
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
    	dateNaissance.setValue(LocalDate.parse(membreSelected.getDateNaissance(), formatter));
    	profession.setText(membreSelected.getProfession());
    	email.setText(membreSelected.getEmail());
    	telephone.setText(membreSelected.getTelephone());
    	pieceIdentiteNum.setText(""+membreSelected.getNumeroIdentite());
    	int indexComboBox= typePiece.getItems().indexOf(membreSelected.getTypePiece());
    	typePiece.getSelectionModel().select(indexComboBox);
    	try {
    		Image img;
    		if(membreSelected.getImage() != null) {
    	
		    	FileOutputStream fos = new FileOutputStream(".//src//main//resources//org//fsb//act//images//userImage.jpg");
	    		fos.write(membreSelected.getImage());
	    		img = new Image("file:.//src//main//resources//org//fsb//act//images//userImage.jpg", image.getFitWidth(), image.getFitHeight(), true, true);
	    	}else 
	    		img = new Image("file:.//src//main//resources//org//fsb//act//images//userDefault.jpg", image.getFitWidth(), image.getFitHeight(), true, true);
    		imageUser= null;
			image.setImage(img);
			
			FileOutputStream fos2 = new FileOutputStream(".//src//main//resources//org//fsb//act//images//copiePiece.jpg");
			if(membreSelected.getCopiePiece() != null) {
				fos2.write(membreSelected.getCopiePiece());
				copiePiece= new File(".//src//main//resources//org//fsb//act//images//copiePiece.jpg");
			}
				
			
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * if you click btn uploadCsvFile
     * then open choiseBrowser and select file
     * @param event
     */
    @FXML
    void uploadCsvFile(ActionEvent event) {
    	stage= (Stage)scrollPane.getScene().getWindow();
    	fileUpCsv = fileChooserCsv.showOpenDialog(stage);
    	if(fileUpCsv != null) {
    		int nbErreur= ServiceMembre.mappingCsvToMembre(fileUpCsv.getAbsolutePath());
    		InputValidation.showAlertInfoWithoutHeaderText("Members merged with "+nbErreur+" error");
    		getAll();
    	}
    }
    /**
     * if you click btn downloadCsvFile
     * then open choiseBrowser and save file
     * @param event
     */
    @FXML
    void downloadCsvFile(ActionEvent event) {
    	stage= (Stage)scrollPane.getScene().getWindow();
    	fileDownCsv=fileChooserCsv.showSaveDialog(stage);
    	if(fileDownCsv != null) {
    		boolean reponse= ServiceMembre.mappingMembreToCsv(fileDownCsv.getAbsoluteFile(), new ArrayList<Membre>(tableMembres.getItems() ));
    		if(reponse)
    			InputValidation.showAlertInfoWithoutHeaderText("Successfully Saved!");
    		else
    			InputValidation.showAlertErrorWithoutHeaderText("Failed Saved");
    	}
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
    /**
     * 
     */
    private void initialiserComBoxTypePiece(){
    	ObservableList<String>  list= FXCollections.observableArrayList(Arrays.asList("C.I.N", "carte de sejour"));
    	typePiece.setItems(list);
    	typePiece.getSelectionModel().select(0);
    }
    /**
     * this function calls serviceMembre to receive all members
     */
	private void getAll() {
		data=FXCollections.observableArrayList(ServiceMembre.getAll());
		tableMembres.setItems(data);
	}
}
