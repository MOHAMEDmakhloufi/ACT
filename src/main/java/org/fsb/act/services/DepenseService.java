package org.fsb.act.services;

import java.util.List;

import org.fsb.act.dao.DepenseDao;
import org.fsb.act.entities.Depense;
import org.fsb.act.entities.FamilleNecessiteuse;
import org.fsb.act.entities.PersonneNecessiteuse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DepenseService {

	public static List<Depense> getAll() {
		
		List<Depense> list= DepenseDao.getAllFromDb();
		/*
		list.forEach(d -> {
			String destination=null;
			if(d.getFamille() != 0) {
				FamilleNecessiteuse fn= FamilleNecessiteuseService.getOneById(d.getFamille());
				String nomPere= fn.getPere().getPrenom() + fn.getPere().getNom();
				String nomMere = fn.getMere().getPrenom() + fn.getMere().getNom();
				destination= "Famille : "+nomPere + " & "+ nomMere;
			}else if(d.getPersonneNecessiteuse() !=0) {
				PersonneNecessiteuse pn= PersonneNecessiteuseService.getOneById(d.getPersonneNecessiteuse());
				destination= pn.getPrenom() +" "+pn.getNom();
			}else if(!d.getDescription().isEmpty() && d.getDescription() != null) {
				destination= d.getDescription();
			}
			d.setDestination(destination);	
				
		});
		*/
		list.add(new Depense(1, 1, 0, null, 140.0, "22/09/21", "med.mak", "Famille : ahmed said & lamia ammar")) ;
		list.add(new Depense(2, 0, 1, null, 370.0, "22/09/21", "med.mak", "mayssa oueslati")) ;
		list.add(new Depense(3, 0, 0, "reunion", 500.0, "22/09/21", "med.mak", "reunion")) ;
		return list;
	}

	public static int deleteDepense(long id) {
		// TODO Auto-generated method stub
		return DepenseDao.deleteDepenseFromDb(id);
	}

	public static long ajouterDepense(Depense depense) {
		// TODO Auto-generated method stub
		return DepenseDao.addDepenseInDb(depense);
	}

	public static long modifierDepense(Depense depense) {
		// TODO Auto-generated method stub
		return DepenseDao.updateDepenseInDb(depense);
	}

	public static ObservableList<Depense> searchDepenses(ObservableList<Depense> data, String text) {
		ObservableList<Depense>  localData= FXCollections.observableArrayList();
		for(Depense depense: data) {
			
			if((""+depense.getMontant()).matches(text+".*") ||
					depense.getDirigeant().matches(text+".*")||
					depense.getDerniereMiseJour().matches(".*"+text+".*")||
					depense.getDestination().matches(".*"+text+".*")) {
				
				localData.add(depense);
			}
		}
		return localData;
	}

}
