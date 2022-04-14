package org.fsb.act.services;

import org.fsb.act.dao.LogInDao;
import org.fsb.act.entities.Association;
import org.fsb.act.entities.Compte;

public class LogInService {

	public static Compte getCompte(String text) {
		
		return LogInDao.getCompteFromDb(text);
	}

	public static Association associationExist() {
		
		return LogInDao.associationExist();
	}

}
