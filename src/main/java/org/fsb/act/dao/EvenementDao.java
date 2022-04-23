package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

	public static int supprimerEventFromDB(long id) {
		
		String requete = "DELETE FROM EVENEMENT WHERE ID = ?";
		try {
			pst = connection.prepareStatement(requete);
			pst.setLong(1, id);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public static List<Evenement> getAll() {
		
		 List<Evenement> liste = new ArrayList<>();
		 String requete = "select * from evenementComplete  ";
		 try {
			pst = connection.prepareStatement(requete);
			rs = pst.executeQuery();
			while (rs.next()) {
				liste.add(new Evenement(rs.getLong(1), rs.getString(2), rs.getString(3), ""+rs.getDate(4),""+ rs.getDate(5), rs.getString(6), rs.getString(7), rs.getDouble(8),rs.getString(9),rs.getString(10)));
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return liste;
	}

	public static long ajouterEvenementInDB(Evenement evenement) {
		String requete = "INSERT INTO EVENEMENT VALUES(SEQEVENEMENT.nextval ,?,?,TO_DATE(?,'YYYY/MM/DD HH24:MI'),TO_DATE(?,'YYYY/MM/DD HH24:MI'),?,?,?)";
		try {
			pst = connection.prepareStatement(requete);
			pst.setString(1, evenement.getTitre());
			pst.setString(2, evenement.getLieu());
			pst.setString(3, evenement.getDateDebut()+" "+evenement.getTimeDebut());
			pst.setString(4, evenement.getDateFin()+" "+evenement.getTimeFin());
			pst.setDouble(5, evenement.getBudget());
			pst.setString(6, evenement.getDirigeant());
			pst.setString(7, evenement.getValidation());
			int i = pst.executeUpdate();
			if (i==1) {
				pst = connection.prepareStatement("Select SEQEVENEMENT.currval FROM DUAL ");
				rs = pst.executeQuery();
				if (rs.next())
					return rs.getLong(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public static int modifierEventInDB(Evenement evenement) {
		String requete = "UPDATE EVENEMENT SET titre =? , Lieu =? ,dateDebut =TO_DATE(?,'YYYY/MM/DD HH24:MI'),dateFin=TO_DATE(?,'YYYY/MM/DD HH24:MI'),budget=?,dirigeant=?,validation=? where id =?";
		try {
			pst = connection.prepareStatement(requete);
			pst.setString(1, evenement.getTitre());
			pst.setString(2, evenement.getLieu());
			pst.setString(3, evenement.getDateDebut()+" "+evenement.getTimeDebut());
			pst.setString(4, evenement.getDateFin()+" "+evenement.getTimeFin());
			pst.setDouble(5, evenement.getBudget());
			pst.setString(6, evenement.getDirigeant());
			pst.setString(7, evenement.getValidation());
			pst.setLong(8, evenement.getId());
			return pst.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		return 0;

	}

}
