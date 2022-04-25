package org.fsb.act.services;

import org.fsb.act.dao.AccesFinanceDao;

public class AccesFinanceService {

	public static String getAccesFinance() {
		
		return AccesFinanceDao.getAccesFinanceFromDB();
	}

	public static int updateAccesFinance(String acces) {
		return AccesFinanceDao.updateAccesFinance(acces);
		
	}

}
