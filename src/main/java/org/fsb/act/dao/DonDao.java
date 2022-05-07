package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.fsb.act.entities.Don;
import org.fsb.act.entities.Donneur;

public class DonDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static Donneur getDonneurById(long id) {
		String requete= "SELECT * FROM DONNEUR WHERE id=?";
		Donneur donneur= new Donneur();
		try {
			pst= connection.prepareStatement(requete);
			pst.setLong(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				donneur.setId(id);
				donneur.setPrenom(rs.getString(2));
				donneur.setNom(rs.getString(3));
				donneur.setPieceIdentite(rs.getLong(4));
				donneur.setNationalite(rs.getString(5));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donneur;
	}

	public static List<Don> getAllFromDb() {
		String requete= "SELECT * FROM DON ";
		List<Don> list= new ArrayList<>();
		try {
			pst=connection.prepareStatement(requete);
			rs=pst.executeQuery();
			while(rs.next()) {
				Don don= new Don();
				don.setId(rs.getLong(1));
				don.setDonneur(new Donneur(rs.getLong(2)));
				don.setTypeDon(rs.getString(3));
				don.setMontant(rs.getDouble(4));
				don.setObjetMateriel(rs.getString(5));
				don.setLeDerniermisejour(rs.getString(6));
				don.setDirigeant(rs.getString(7));
				list.add(don);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public static long ajouterDonneur(Donneur donneur) throws SQLException {
		String requete="INSERT INTO DONNEUR VALUES(SEQDONNEUR.nextval, ?, ?, ?, ?)";
		
			pst=connection.prepareStatement(requete);
			pst.setString(1, donneur.getPrenom());
			pst.setString(2, donneur.getNom());
			pst.setLong(3, donneur.getPieceIdentite());
			pst.setString(4, donneur.getNationalite());
			int i= pst.executeUpdate();
			if(i==1) {
				pst=connection.prepareStatement("select SEQDONNEUR.currval from dual");
            	rs=pst.executeQuery();
            	if(rs.next())
            		return rs.getLong(1);
			}
		return 0;
	}

	public static long ajouterDon(Don don) throws SQLException {
		String requete="INSERT INTO DON VALUES(SEQDON.nextval, ?, ?, ?, ?, TO_DATE(sysdate, 'YYYY-MM-DD '), ?)";
		
			pst= connection.prepareStatement(requete);
			pst.setLong(1, don.getDonneur().getId());
			pst.setString(2, don.getTypeDon());
			pst.setDouble(3, don.getMontant());
			pst.setString(4, don.getObjetMateriel());
			
			pst.setString(5, don.getDirigeant());
			int i= pst.executeUpdate();
			if(i==1) {
				pst=connection.prepareStatement("select SEQDON.currval from dual");
            	rs=pst.executeQuery();
            	if(rs.next())
            		return rs.getLong(1);
			}
		return 0;
	}

	public static int updateDonneur(Donneur donneur) throws SQLException {
		String requete="UPDATE DONNEUR SET prenom=?, nom=?, NUMEROPIECEIDENTITE=?, NATIONALITE=? WHERE id=?";
		
		pst=connection.prepareStatement(requete);
		pst.setString(1, donneur.getPrenom());
		pst.setString(2, donneur.getNom());
		pst.setLong(3, donneur.getPieceIdentite());
		pst.setString(4, donneur.getNationalite());
		pst.setLong(5, donneur.getId());
		return pst.executeUpdate();
		
	}

	public static int updateDon(Don don) throws SQLException {
		String requete="UPDATE DON SET typedon=?, montant=?, OBJETMATERIELLE=?, LEDERNIEREMISEJOUR=TO_DATE(sysdate, 'YYYY-MM-DD '), DIRIGEANT=? WHERE ID=?";
		
		pst=connection.prepareStatement(requete);
		pst.setString(1, don.getTypeDon());
		pst.setDouble(2, don.getMontant());
		pst.setString(3, don.getObjetMateriel());
		pst.setString(4, don.getDirigeant());
		pst.setLong(5, don.getId());
		return pst.executeUpdate();
	}

	public static int supprimerDon(long id) throws SQLException {
		String requete="DELETE FROM DON WHERE id=?";
		pst= connection.prepareStatement(requete);
		pst.setLong(1, id);
		
		return pst.executeUpdate();
	}

	public static int supprimerDonneur(long id) throws SQLException {
		String requete="DELETE FROM DONNEUR WHERE id=?";
		pst= connection.prepareStatement(requete);
		pst.setLong(1, id);
		
		return pst.executeUpdate();
	}
}
