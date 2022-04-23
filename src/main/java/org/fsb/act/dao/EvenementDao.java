package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.fsb.act.entities.Evenement;

public class EvenementDao {
	
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static Evenement getOneById(long id) {
		Evenement e= new Evenement();
		String requete= "SELECT * FROM EVENEMENTCOMPLETE WHERE id=?";
		
		try {
			pst= connection.prepareStatement(requete);
			pst.setLong(1, id);
			rs= pst.executeQuery();
			if(rs.next()) {
				e.setId(rs.getLong(1));
				e.setTitre(rs.getString(2));
				e.setLieu(rs.getString(3));
				e.setDateDebut(""+rs.getDate(4));
				e.setDateFin(""+rs.getDate(5));
				e.setTimeDebut(rs.getString(6));
				e.setTimeFin(rs.getString(7));
				e.setBudget(rs.getDouble(8));
				e.setDirigeant(rs.getString(9));
				e.setValidation(rs.getString(10));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}

}
