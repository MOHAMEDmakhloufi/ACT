package org.fsb.act.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Map;
import java.util.ResourceBundle;

import org.fsb.act.services.DashBoardService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashBoardController implements Initializable{
    @FXML
    private BarChart<?, ?> barChart1;

    @FXML
    private CategoryAxis categoryAxisBC1;
    
    @FXML
    private NumberAxis numberAxisBC1;
    
    @FXML
    private BarChart<?, ?> barChart2;

    @FXML
    private CategoryAxis categoryAxisBC2;
    
    @FXML
    private NumberAxis numberAxisBC2;
 
    @FXML
    private Label labelDepense;

    @FXML
    private Label labelRevenue;

    @FXML
    private Label labelDifference;
    
    @FXML
    private Label labelArgent;
    
    @FXML
    private TextField textFieldJour;

    @FXML
    private TextField textFieldMois;

    @FXML
    private TextField textFieldAnne;
    
    @FXML
    private VBox vBoxLeft;
    
    @FXML
    private VBox vBoxRight;
    
    private AreaChart<Number, Number> areaChart;
    private PieChart pieChart;
    private BarChart<Number, String> barChart;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	getArgentTresor();
    	revenueAndDepenseInitial();
    	vBoxLeft.getChildren().add(areaChart);
    	vBoxLeft.getChildren().add(pieChart);
    	barChar1();
    	vBoxRight.getChildren().add(0, barChart);
	}
    @FXML
    void refresh() {
    	getArgentTresor();
    	revenueAndDepenseInitial();
    	barChar1();
    }
    /**
     * the method get the last argent du tresor from data base
     */
    public void getArgentTresor() {
    	String argent= DashBoardService.getArgentTresor();
    	labelArgent.setText(argent+" DT");
    	
    }
    /**
     * initialement set the textField annee a now year
     */
    public void revenueAndDepenseInitial() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
    	textFieldAnne.setText(LocalDate.now().format(formatter));
    	changeDateAnnee();
    }
    /**
     * if On KeyTyped in text field annee then call this function 
     */
    @FXML
    void changeDateAnnee() {
    	if(textFieldAnne.getText().matches("^20[0-9][0-9]$")) {
        	changeDate();
        	areaChart();
        	pieChart();
    	}

    }
    @FXML
    void changeDateMois() {
    	if(textFieldMois.getText().matches("^(1[0-2])|(0[0-9])$")|| textFieldMois.getText().isEmpty() ) {
    		changeDate();
    	}

    }
    @FXML
    void changeDateJour() {
    	if(textFieldJour.getText().matches("^(3[0-1])|([0-2][0-9])$")|| textFieldJour.getText().isEmpty()) {
    		changeDate();
    	}

    }
    /**
     * if On KeyTyped in text field date(annee or mois or jour) then call this function 
     */
    @FXML
    void changeDate() {
    
    	String annee= textFieldAnne.getText();
    	String mois= textFieldMois.getText();
    	String jour= textFieldJour.getText();
    	Map<String, String> map= DashBoardService.getRevenueDepenseDifferent(annee, mois, jour);
    	
    	labelRevenue.setText("revenue :  "+map.get("revenue")+" DT");
    	labelDepense.setText("depense :  "+map.get("depense")+" DT");
    	labelDifference.setText("difference :  "+map.get("difference")+" DT");
    }
    /**
     * create an areaChart 
     */
    public void areaChart() {
    	
    	 //get Data Depense and revenue from Data base
    	 String annee= textFieldAnne.getText();
    	 ObservableList<Data<Number, Number>> listDepense = DashBoardService.getDepenseAnnee(annee);
    	 ObservableList<Data<Number, Number>> listRevenue = DashBoardService.getRevenueAnnee(annee);
    	 //fin
    	 if(areaChart==null) {
    		 final NumberAxis xAxis = new NumberAxis(1, 12, 1);
             final NumberAxis yAxis = new NumberAxis();
             areaChart = new AreaChart<Number, Number>(xAxis, yAxis);
             areaChart.setTitle("Revenue et Depense");
             
             areaChart.setLegendSide(Side.LEFT);
             areaChart.setPrefSize(374, 308);
             areaChart.setPrefWidth(374);
    	 }
    	 

         // Series data of depense
         XYChart.Series<Number, Number> seriesDepense = new XYChart.Series<Number, Number>();

         
         seriesDepense.setName("Depense");
         seriesDepense.getData().addAll(listDepense);

         // Series data of revenue
         XYChart.Series<Number, Number> seriesRevenue = new XYChart.Series<Number, Number>();
         seriesRevenue.setName("Revenue");
         seriesRevenue.getData().addAll(listRevenue);
         areaChart.getData().clear();
         areaChart.getData().addAll(seriesDepense, seriesRevenue);
         
         

    }
    public void pieChart() {
    	//get Data Revenue type and revenue from Data base
	   	String annee= textFieldAnne.getText();
	   	ObservableList<PieChart.Data> list = DashBoardService.getTypeRevenue(annee);
	   	//fin
	   	
        

        if(pieChart == null) {
        	pieChart = new PieChart();
        	pieChart.setPrefSize(374, 308);
        	pieChart.setPrefWidth(374); 
        	pieChart.setStyle("-fx-backgound-color:red");
        }
        pieChart.getData().clear();
        pieChart.getData().addAll(list);
        
        //pieChart.setLegendSide(Side.LEFT);
    }
    public void barChar1() {
    	
    	//get Data Depense and revenue from Data base
	   	ObservableList<XYChart.Data<Number, String>> list = DashBoardService.getRevenueOuvertureACeJour();
	   	ObservableList<XYChart.Data<Number, String>> list2 = DashBoardService.getDepenseOuvertureACeJour();
	   	ObservableList<XYChart.Data<Number, String>> list3 = DashBoardService.getDifferenceOuvertureACeJour();
	   	//fin
	   	
        // Create a BarChart
        if(barChart == null) {
        	CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("ANNEE");
            NumberAxis yAxis = new NumberAxis();
        	barChart = new BarChart<Number, String>(yAxis, xAxis);
        	barChart.setPrefWidth(400);
        }
        

        // Series 1 - Data of revenue
        XYChart.Series<Number, String> dataSeries1 = new XYChart.Series<Number, String>();
        dataSeries1.setName("Chiffre d'affaires de l'ouverture à ce jour en DT");
        dataSeries1.getData().addAll(list);
        
        // Series 1 - Data of revenue
        XYChart.Series<Number, String> dataSeries2 = new XYChart.Series<Number, String>();
        dataSeries2.setName("Depense de l'ouverture à ce jour en DT");
        dataSeries2.getData().addAll(list2);
        // Series 1 - Data of revenue
        XYChart.Series<Number, String> dataSeries3 = new XYChart.Series<Number, String>();
        dataSeries3.setName("Dettes de l'ouverture à ce jour en DT");
        dataSeries3.getData().addAll(list3);
        
        // Add Series to BarChart.
        barChart.getData().clear();
        barChart.getData().addAll(dataSeries1, dataSeries2, dataSeries3);

    }
}
