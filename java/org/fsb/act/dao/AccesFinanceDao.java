package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesFinanceDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static String getAccesFinanceFromDB() {
		String requete = "SELECT accesfinance FROM ASSOCIATION";
		try {
			pst= connection.prepareStatement(requete);
			rs= pst.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static int updateAccesFinance(String acces) {
		String requete="UPDATE ASSOCIATION SET accesfinance=?";
		try {
			pst= connection.prepareStatement(requete);
			pst.setString(1, acces);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
