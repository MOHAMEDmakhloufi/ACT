package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.fsb.act.entities.Association;
import org.fsb.act.entities.Compte;

public class LogInDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static Compte getCompteFromDb(String text) {
		String requete="SELECT * FROM COMPTE WHERE username=?";
		Compte compte = new Compte();
		try {
			pst=connection.prepareStatement(requete);
			pst.setString(1, text);
			rs= pst.executeQuery();
			if(rs.next()) {
				compte.setUsername(rs.getString(1));
				compte.setPassword(rs.getString(2));
				compte.setActivation(rs.getString(3));
				return compte;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return null;
	}

	public static Association associationExist() {
		String requete= "SELECT username, password FROM ASSOCIATION";
		Association association;
		try {
			pst=connection.prepareStatement(requete);
			rs= pst.executeQuery();
			if(rs.next()) {
				association= new Association();
				association.setUsername(rs.getString(1));
				association.setPassword(rs.getString(2));
				return association;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
