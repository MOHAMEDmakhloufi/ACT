package org.fsb.act.services;

import java.util.ArrayList;
import java.util.List;

import org.fsb.act.dao.CotistationMembreDao;
import org.fsb.act.entities.CotisationMembre;
import org.fsb.act.entities.Evenement;
import org.fsb.act.entities.Membre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class CotisationMembreService {

	public static List<CotisationMembre> getAll() {
		List<CotisationMembre> list=CotistationMembreDao.getAllFromDb();
		list.forEach(c ->{
			Membre membre= ServiceMembre.getOneById(c.getMembre());
			c.setPrenom(membre.getPrenom());
			c.setNom(membre.getNom());
		});
		
		return list;
	}

	public static ObservableList<CotisationMembre> searchCotisations(ObservableList<CotisationMembre> data,
			String text) {
		ObservableList<CotisationMembre>  localData= FXCollections.observableArrayList();
		for(CotisationMembre cm: data) {
			if(cm.getPrenom().matches(text+".*") ||
		       cm.getNom().matches(text+".*") ||
		       (cm.getPrenom()+cm.getNom()).matches(text+".*") ||
		       cm.getDerniereMiseJour().matches(".*"+text+".*")||
		       cm.getDirigeant().matches(".*"+text+".*")||
		       (cm.getMontant()+"").matches(text+".*")) {
				
				localData.add(cm);
			}
		}
		return localData;
	}

	public static int ajouterCotMembre(CotisationMembre cotisationMembre) {

		return CotistationMembreDao.addDB(cotisationMembre);
	}

	public static int modifierCotisationMembre(CotisationMembre cotisationMembre) {
		// TODO Auto-generated method stub
		return CotistationMembreDao.updateDB(cotisationMembre);
	}

	public static int supprimerCotisation(long id) {
		// TODO Auto-generated method stub
		return CotistationMembreDao.deleteFromDB(id);
	}

}
