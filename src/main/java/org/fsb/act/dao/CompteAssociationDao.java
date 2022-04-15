package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.fsb.act.entities.Compte;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompteAssociationDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;

	
	public static List<Compte> getDataAssociation() {
		String requete = "SELECT  * FROM COMPTE";
		List<Compte> comptes = new ArrayList<Compte>();
		try {
			pst= connection.prepareStatement(requete);
			rs= pst.executeQuery();
			while(rs.next()) {
				comptes.add(new Compte(rs.getString(1),rs.getString(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return comptes;		
	}
	
	

	public static List<Compte> search(String key) {
		String requete = "SELECT  * FROM COMPTE WHERE USERNAME LIKE ?";
		List<Compte> comptes = new ArrayList<Compte>();
		try {
			pst= connection.prepareStatement(requete);
			pst.setString(1, key+"%");
			rs= pst.executeQuery();
			while(rs.next()) {
				comptes.add(new Compte(rs.getString(1),rs.getString(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return comptes;		
	}
	
	
	
	public static int update(Compte compte) {
		String requete = "UPDATE COMPTE SET password=? , activation=? WHERE username=?";
		
		try {
			pst= connection.prepareStatement(requete);		
			pst.setString(1, compte.getPassword());
			pst.setInt(2, compte.getActive());
			pst.setString(3, compte.getUsername());			
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	public static int add(Compte compte) {
		String requete = "INSERT INTO COMPTE(username,password,activation) VALUES (?,?,?)";
		
		try {
			pst= connection.prepareStatement(requete);	
			pst.setString(1, compte.getUsername());	
			pst.setString(2, compte.getPassword());
			pst.setInt(3, compte.getActive());					
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
