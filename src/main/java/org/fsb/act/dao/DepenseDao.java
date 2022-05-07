package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.fsb.act.entities.Depense;
import org.fsb.act.entities.FamilleNecessiteuse;

public class DepenseDao {
	
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static List<Depense> getAllFromDb() {
		List<Depense> list= new ArrayList<>();
		String requete ="SELECT * FROM DEPENSE";
		try {
			pst=connection.prepareStatement(requete);
			rs= pst.executeQuery();
			while(rs.next()) {
				Depense depense= new Depense();
				depense.setId(rs.getLong(1));
				depense.setFamille(rs.getLong(2));
				depense.setPersonneNecessiteuse(rs.getLong(3));
				depense.setDescription(rs.getString(4));
				depense.setMontant(rs.getDouble(5));
				depense.setDerniereMiseJour(rs.getDate(6)+"");
				depense.setDirigeant(rs.getString(7));
				
				list.add(depense);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static int deleteDepenseFromDb(long id) {
		String requete="DELETE FROM depense WHERE id=?";
		try {
			pst= connection.prepareStatement(requete);
			pst.setLong(1, id);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public static int addDepenseInDb(Depense depense) {
		String requete="INSERT INTO depense VALUES(seqdepense.nextval, ?, ?, ?, ?, TO_DATE(sysdate, 'YYYY-MM-DD'), ?)";
		try {
			pst=connection.prepareStatement(requete);
			
			pst.setNString(1, (depense.getFamille()==0)?null:""+depense.getFamille());
			pst.setNString(2, (depense.getPersonneNecessiteuse()==0)?null:""+depense.getPersonneNecessiteuse());
			pst.setString(3, depense.getDescription());
			pst.setDouble(4, depense.getMontant());
			//pst.setDate(5, java.sql.Date.valueOf(depense.getDerniereMiseJour()));
			pst.setString(5, depense.getDirigeant());
			return pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static long updateDepenseInDb(Depense depense) {
		String requete="UPDATE depense SET famille=?, personnenecessiteuse=?, description=?, montant=?, ledernieremisejour=TO_DATE(sysdate, 'YYYY-MM-DD'), dirigeant=? WHERE id=?";
		try {
			pst= connection.prepareStatement(requete);
			pst.setNString(1, (depense.getFamille()==0)?null:""+depense.getFamille());
			pst.setNString(2, (depense.getPersonneNecessiteuse()==0)?null:""+depense.getPersonneNecessiteuse());
			pst.setString(3, depense.getDescription());
			pst.setDouble(4, depense.getMontant());
			//pst.setDate(5, java.sql.Date.valueOf(depense.getDerniereMiseJour()));
			pst.setString(5, depense.getDirigeant());
			pst.setLong(6, depense.getId());
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
