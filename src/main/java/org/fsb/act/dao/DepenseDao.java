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
		// TODO Auto-generated method stub
		return 0;
	}

	public static long addDepenseInDb(Depense depense) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static long updateDepenseInDb(Depense depense) {
		// TODO Auto-generated method stub
		return 0;
	}

}
