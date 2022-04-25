package org.fsb.act.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.NotificationEvenement;
import org.fsb.act.entities.NotificationValidation;
import org.fsb.act.services.NotificationValidationService;
import org.fsb.act.validation.InputValidation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class NotificationValidationController implements Initializable{
	
	@FXML
	 private VBox vBox;
	 
	 private Map<AnchorPane, NotificationValidation> map= new HashMap<>();
	 
	 public static List<NotificationValidationController> listControllersNotif= new ArrayList<>();
	 /*
	  * the destructor
	  */
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listControllersNotif.add(this); 
		
		getAll();
		//vBox.getChildren().add(createNotification(new NotificationValidation(1, null,null, null), true));
	}
	 /**
    * this function calls serviceMembre to receive all notifications
    */
	 public  void getAll() {
		 map.clear();
		 vBox.getChildren().clear();
		List<NotificationValidation> notifications= NotificationValidationService.getAllFromService();
		
		notifications.forEach(nv -> {
			AnchorPane ap;
			if(nv.getNotifEvent().getValidation().equals("accepter"))
				ap= createNotification(nv, true);
			else 
				ap= createNotification(nv, false);
				
			map.put(ap, nv);
			
			vBox.getChildren().add(ap);
			
		});
	}
	 
	
	 /**
	  * this methode create new anchorPane complet
	  * @return
	  */
	 public AnchorPane createNotification(NotificationValidation nv, Boolean validation) {
		 AnchorPane anchorPane= new AnchorPane();
		 anchorPane.getStyleClass().add("anchor-pane");
		//create object gridPane
		GridPane gridPane= new GridPane();
		AnchorPane.setTopAnchor(gridPane, 0.0);
	    AnchorPane.setLeftAnchor(gridPane, 0.0);
	    AnchorPane.setRightAnchor(gridPane, 0.0);
	    AnchorPane.setBottomAnchor(gridPane, 0.0);
		//create three column with Percentage Sizing
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(62);
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(38);
	    //add this columns a gridPane
	    gridPane.getColumnConstraints().addAll(column1, column2);
	    RowConstraints row1 = new RowConstraints();
	    row1.setPercentHeight(25);
	    RowConstraints row2 = new RowConstraints();
	    row2.setPercentHeight(25);
	    RowConstraints row3 = new RowConstraints();
	    row3.setPercentHeight(25);
	    RowConstraints row4 = new RowConstraints();
	    row4.setPercentHeight(25);
	    gridPane.getRowConstraints().addAll(row1, row2, row3, row4);
	    //add the grid in the anchorpane
	    anchorPane.getChildren().add(gridPane);
	    
	    //label1
	    Label label1= new Label("LE Demande d'approbation d'un montant  est :");
	    //label2
	    Label label2= new Label("dirigeant qui demande : "+nv.getEvenement().getDirigeant());
	    label2.getStyleClass().add("sousLabel");
	    //label3
	    Label label3= new Label("Budget en DT  : " +nv.getEvenement().getBudget()+" DT");
	    label3.getStyleClass().add("sousLabel");
	    //label4
	    Label label4= new Label("Raison : "+nv.getEvenement().getTitre() +", Le "+nv.getEvenement().getDateDebut());
	    label4.getStyleClass().add("sousLabel");
	    
	    gridPane.add(label1, 0, 0);
	    gridPane.add(label2, 0, 1);
	    gridPane.add(label3, 0, 2);
	    gridPane.add(label4, 0, 3);
	    
	    //anchorpane buttons
	    AnchorPane anchorPaneBtns= new AnchorPane();
	    
	    gridPane.add(anchorPaneBtns, 1, 0);
	    //button 
	    Button btn= new Button("REFUSER");
	    btn.setStyle("-fx-cursor: none");
	    AnchorPane.setTopAnchor(btn, 5.0);
	    AnchorPane.setLeftAnchor(btn, 15.0);
	    if(validation) {
	    	btn.setText("ACCEPTER");
	    	btn.getStyleClass().add("accepter");
	    }else {
		    btn.getStyleClass().add("refuser");
		    
	    }
	    //dirigeant
	    Label labelDirigeant= new Label("par "+ nv.getNotifEvent().getDirigeant());
	    AnchorPane.setTopAnchor(labelDirigeant, 0.0);
	    AnchorPane.setRightAnchor(labelDirigeant, 25.0);
	    AnchorPane.setLeftAnchor(labelDirigeant, 130.0);
	    anchorPaneBtns.getChildren().addAll(btn, labelDirigeant);
	    
	    //anchorPane date notif
	    AnchorPane anchorPaneDate= new AnchorPane();
	    gridPane.add(anchorPaneDate, 1, 3);
	    //label1
	    Label labelDate= new Label(nv.getDate());
	    anchorPaneDate.getChildren().add(labelDate);
	    AnchorPane.setRightAnchor(labelDate, 25.0);
		 return anchorPane;
	 }
}
