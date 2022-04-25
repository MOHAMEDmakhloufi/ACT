package org.fsb.act.services;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.fsb.act.dao.DashBoardDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class DashBoardService {

	public static String getArgentTresor() {
		
		return DashBoardDao.getArgentTresor();
	}

	public static Map<String, String> getRevenueDepenseDifferent(String annee, String mois, String jour) {
		
		return DashBoardDao.getRevenueDepenseDifferent(annee, mois, jour);
	}

	public static ObservableList<Data<Number, Number>> getDepenseAnnee(String annee) {
		ObservableList<Data<Number, Number>> list= FXCollections.observableArrayList();
		for(int i=1; i<=12; i++) {
			if(i<10)
				list.add(new Data<Number, Number>(i, DashBoardDao.getDepenseAnneeMois(annee, "0"+i)));
			else
				list.add(new Data<Number, Number>(i, DashBoardDao.getDepenseAnneeMois(annee, ""+i)));
		}
		return list;
	}

	public static ObservableList<Data<Number, Number>> getRevenueAnnee(String annee) {
		ObservableList<Data<Number, Number>> list= FXCollections.observableArrayList();
		for(int i=1; i<=12; i++) {
			if(i<10)
				list.add(new Data<Number, Number>(i, DashBoardDao.getRevenueAnneeMois(annee, "0"+i)));
			else
				list.add(new Data<Number, Number>(i, DashBoardDao.getRevenueAnneeMois(annee, ""+i)));
		}
		return list;
	}

	public static ObservableList<PieChart.Data> getTypeRevenue(String annee) {
		ObservableList<PieChart.Data> list= FXCollections.observableArrayList();
		 
	     list.add(new PieChart.Data("Don", DashBoardDao.getTypeRevenue(annee, "don")));
	     list.add(new PieChart.Data("Cotisation", DashBoardDao.getTypeRevenue(annee, "cotisation")));
		return list;
	}

	public static ObservableList<XYChart.Data<Number, String>> getRevenueOuvertureACeJour() {
		ObservableList<XYChart.Data<Number, String>> list= FXCollections.observableArrayList();
		
		int anneeCreation= Integer.parseInt(ModifierAssociationService.getAnneeCreation());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		
		int nowAnnee= Integer.parseInt(LocalDate.now().format(formatter));
		for(int i=anneeCreation; i<= nowAnnee; i++) {
			list.add(new XYChart.Data<Number, String>(DashBoardDao.getRevenueOuvertureACeJour(""+i), ""+i));
		}
		return list;
	}

	public static ObservableList<Data<Number, String>> getDepenseOuvertureACeJour() {
		ObservableList<XYChart.Data<Number, String>> list= FXCollections.observableArrayList();
		
		int anneeCreation= Integer.parseInt(ModifierAssociationService.getAnneeCreation());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		
		int nowAnnee= Integer.parseInt(LocalDate.now().format(formatter));
		for(int i=anneeCreation; i<= nowAnnee; i++) {
			list.add(new XYChart.Data<Number, String>(DashBoardDao.getDepenseOuvertureACeJour(""+i), ""+i));
		}
		return list;
	}

	public static ObservableList<Data<Number, String>> getDifferenceOuvertureACeJour() {
		ObservableList<XYChart.Data<Number, String>> list= FXCollections.observableArrayList();
		
		int anneeCreation= Integer.parseInt(ModifierAssociationService.getAnneeCreation());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		
		int nowAnnee= Integer.parseInt(LocalDate.now().format(formatter));
		for(int i=anneeCreation; i<= nowAnnee; i++) {
			list.add(new XYChart.Data<Number, String>(DashBoardDao.getDifferenceOuvertureACeJour(""+i), ""+i));
		}
		return list;
	}

}
