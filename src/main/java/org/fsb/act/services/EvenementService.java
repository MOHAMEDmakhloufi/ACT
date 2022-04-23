package org.fsb.act.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fsb.act.dao.EvenementDao;
import org.fsb.act.entities.Evenement;
import org.fsb.act.entities.Membre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class EvenementService {

	public static List<Evenement> getAll() {
		return EvenementDao.getAll();
		
		
	}

	public static int supprimerEvent(long id) {
		// TODO Auto-generated method stub
		return EvenementDao.supprimerEventFromDB(id);
	}

	public static long ajouterEvent(Evenement evenement) {
		// TODO Auto-generated method stub
		return EvenementDao.ajouterEvenementInDB(evenement);
	}

	public static int modifierEvent(Evenement e) {
		// TODO Auto-generated method stub
		return EvenementDao.modifierEventInDB(e);
	}
	public static Evenement getOneById(long id) {
		return EvenementDao.getOneById(id);
	}
	public static ObservableList<Evenement> searchEvents(ObservableList<Evenement> data, String text) {
		ObservableList<Evenement>  localData= FXCollections.observableArrayList();
		for(Evenement e: data) {
			if(e.getTitre().matches(text+".*") ||
		       e.getLieu().matches(text+".*") ||
		       e.getDateDebut().matches(".*"+text+".*")||
		       e.getDateFin().matches(".*"+text+".*")||
		       e.getTimeDebut().matches(text+".*") ||
		       e.getTimeFin().matches(text+".*") ||
		       
		       e.getDirigeant().matches(text+".*") ||
		       (""+e.getBudget()).matches(text+".*") ||
		      e.getValidation().matches(text+".*")) {
				
				localData.add(e);
			}
		}
		return localData;
	}

}
