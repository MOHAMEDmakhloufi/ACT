package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.fsb.act.entities.CotisationMembre;
import org.fsb.act.entities.Evenement;

public class CotistationMembreDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static List<CotisationMembre> getAllFromDb() {
		List<CotisationMembre> liste = new ArrayList<>();
		 String requete = "select * from cotisationMembre ";
		 try {
			pst = connection.prepareStatement(requete);
			rs = pst.executeQuery();
			while (rs.next()) {
				CotisationMembre cm= new CotisationMembre();
				cm.setId(rs.getLong(1));
				cm.setMembre(rs.getLong(2));
				cm.setMontant(rs.getDouble(3));
				cm.setDerniereMiseJour(""+rs.getDate(4));
				cm.setDirigeant(rs.getString(5));
				liste.add(cm);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return liste;
	}

}
