package org.fsb.act.services;

import java.util.ArrayList;
import java.util.List;

import org.fsb.act.dao.CotistationMembreDao;
import org.fsb.act.entities.CotisationMembre;
import org.fsb.act.entities.Membre;



public class CotistationMembreService {

	public static List<CotisationMembre> getAll() {
		List<CotisationMembre> list=CotistationMembreDao.getAllFromDb();
		list.forEach(c ->{
			Membre membre= ServiceMembre.getOneById(c.getMembre());
			c.setPrenom(membre.getPrenom());
			c.setNom(membre.getNom());
		});
		
		return list;
	}

}
