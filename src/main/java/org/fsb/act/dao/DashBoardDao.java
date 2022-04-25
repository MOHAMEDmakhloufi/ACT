package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DashBoardDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static String getArgentTresor() {
		String requete= "SELECT argentresor() as argent FROM DUAL ";
		try {
			pst= connection.prepareStatement(requete);
			rs= pst.executeQuery();
			if(rs.next()) {
				return ""+rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static Map<String, String> getRevenueDepenseDifferent(String annee, String mois, String jour) {
		String requete= "select calRevenue(?,?,?) as revenue, calTotalDepense (?,?,?) as depense, calDifference (?,?,?) as difference from dual";
		Map<String, String> map= new HashMap<>();
		try {
			pst= connection.prepareStatement(requete);
			pst.setString(1, annee);
			pst.setString(2, mois);
			pst.setString(3, jour);
			
			pst.setString(4, annee);
			pst.setString(5, mois);
			pst.setString(6, jour);
			
			pst.setString(7, annee);
			pst.setString(8, mois);
			pst.setString(9, jour);
			
			rs= pst.executeQuery();
			if(rs.next()) {
				map.put("revenue", rs.getString(1));
				map.put("depense", rs.getString(2));
				map.put("difference", rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static Number getDepenseAnneeMois(String annee, String mois) {
		String requete= "select calTotalDepense (?,?, NULL) as depense from dual";
		try {
			pst=connection.prepareStatement(requete);
			pst.setString(1, annee);
			pst.setString(2, mois);
			rs= pst.executeQuery();
			if(rs.next())
				return rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public static Number getRevenueAnneeMois(String annee, String mois) {
		String requete= "select calRevenue (?,?, NULL) as depense from dual";
		try {
			pst=connection.prepareStatement(requete);
			pst.setString(1, annee);
			pst.setString(2, mois);
			rs= pst.executeQuery();
			if(rs.next())
				return rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public static double getTypeRevenue(String annee, String type) {
		String requete= "select calRevenueByType(?, ?) as depense from dual";
		try {
			pst=connection.prepareStatement(requete);
			pst.setString(1, annee);
			pst.setString(2, type);
			rs= pst.executeQuery();
			if(rs.next())
				return rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0.0;
	}

	public static Number getRevenueOuvertureACeJour(String annee) {
		String requete= "select calRevenue (?,NULL, NULL) as revenue from dual";
		try {
			pst=connection.prepareStatement(requete);
			pst.setString(1, annee);
			rs= pst.executeQuery();
			if(rs.next())
				return rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public static Number getDepenseOuvertureACeJour(String annee) {
		String requete= "select calTotalDepense (?,NULL, NULL) as depense from dual";
		try {
			pst=connection.prepareStatement(requete);
			pst.setString(1, annee);
			rs= pst.executeQuery();
			if(rs.next())
				return rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public static Number getDifferenceOuvertureACeJour(String annee) {
		String requete= "select calDifference (?,NULL, NULL) as diff from dual";
		try {
			pst=connection.prepareStatement(requete);
			pst.setString(1, annee);
			rs= pst.executeQuery();
			if(rs.next())
				return rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
