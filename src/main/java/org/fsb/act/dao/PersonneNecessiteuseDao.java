package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.fsb.act.entities.PersonneNecessiteuse;

public class PersonneNecessiteuseDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	
	
	
	
	public static int add(PersonneNecessiteuse personne) {
		String requete = "INSERT INTO PERSONNENECESSITEUSE(id,prenom,nom,prenompere,prenomgrandpere,"
				+ "prenommere,nommere,datenaissance,lieunaissance,nationalite,"
				+ "gouvernorat,etatcivil,profession,numeropieceidentite,typeidentite,"
				+ "email,telephone,adresse,codepostal,dirigeant) "
				+ " values (SEQPERSONNENECESSITEUSE.nextval,?,?,?,?,"
				+ "?,?,?,?,?,"
				+ "?,?,?,?,?,"
				+ "?,?,?,?,?)";
		
		try {
			pst= connection.prepareStatement(requete);
			pst.setString(1,personne.getPrenom());	
			pst.setString(2,personne.getNom());
			pst.setString(3,personne.getPrenomPere());	
			pst.setString(4,personne.getPrenomGPere());
			pst.setString(5,personne.getPrenomMere());	
			pst.setString(6,personne.getNomMere());
			pst.setDate(7,java.sql.Date.valueOf(personne.getDateN()));
			pst.setString(8,personne.getLieuN());	
			pst.setString(9,personne.getNationalite());
			pst.setString(10,personne.getGouvernorat());	
			pst.setString(11,personne.getEtatCivil());
			pst.setString(12,personne.getProfession());
			pst.setInt(13,personne.getIdentite());	
			pst.setString(14,personne.getTypeIdentite());
			pst.setString(15,personne.getEmail());	
			pst.setString(16,personne.getTelephone());
			pst.setString(17,personne.getAdresse());
			pst.setInt(18,personne.getCodePostal());	
			pst.setString(19,personne.getDirigeant());
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	
	public static int update(PersonneNecessiteuse personne) {
		String requete = "UPDATE PERSONNENECESSITEUSE SET prenom=? , nom=? , prenompere=? , prenomgrandpere=? ,"
				+ " prenommere=? , nommere=? , datenaissance=? , lieunaissance=? , nationalite=? ,gouvernorat=? ,"
				+ "etatcivil=? , profession=? , numeropieceidentite=? , typeidentite=? ,"
				+ "email=? , telephone=? , adresse=? , codepostal=? , dirigeant=? WHERE id=?";
		
		try {
			pst= connection.prepareStatement(requete);					
			pst.setString(1,personne.getPrenom());	
			pst.setString(2,personne.getNom());
			pst.setString(3,personne.getPrenomPere());	
			pst.setString(4,personne.getPrenomGPere());
			pst.setString(5,personne.getPrenomMere());	
			pst.setString(6,personne.getNomMere());
		    pst.setDate(7,java.sql.Date.valueOf(personne.getDateN()));
			pst.setString(8,personne.getLieuN());	
			pst.setString(9,personne.getNationalite());
			pst.setString(10,personne.getGouvernorat());	
			pst.setString(11,personne.getEtatCivil());
			pst.setString(12,personne.getProfession());
			pst.setInt(13,personne.getIdentite());	
			pst.setString(14,personne.getTypeIdentite());
			pst.setString(15,personne.getEmail());	
			pst.setString(16,personne.getTelephone());
			pst.setString(17,personne.getAdresse());
			pst.setInt(18,personne.getCodePostal());	
			pst.setString(19,personne.getDirigeant());
			pst.setLong(20, personne.getId());
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	public static List<PersonneNecessiteuse> getData() {
		String requete = "SELECT  * FROM PERSONNENECESSITEUSE";
		List<PersonneNecessiteuse> personnes = new ArrayList<PersonneNecessiteuse>();
		try {
			pst= connection.prepareStatement(requete);
			rs= pst.executeQuery();
			while(rs.next()) {
				personnes.add(new PersonneNecessiteuse(rs.getLong(1), rs.getString(2),
						rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7),""+rs.getDate(8), rs.getString(9), rs.getString(10), rs.getString(11), 
						rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15),
						rs.getString(16), rs.getString(17), rs.getString(18), rs.getInt(19),rs.getString(20)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		personnes.sort(new Comparator<PersonneNecessiteuse>() {

			@Override
			public int compare(PersonneNecessiteuse p1, PersonneNecessiteuse p2) {
				
				return (int)(p2.getId() - p1.getId());
			}
		});
		return personnes;		
	}
	
	
	
	public static PersonneNecessiteuse getById(long id) {
		String requete = "SELECT  * FROM PERSONNENECESSITEUSE WHERE id=?";
		try {
			pst= connection.prepareStatement(requete);
			pst.setLong(1, id);
			rs= pst.executeQuery();
			if(rs.next()) {
				PersonneNecessiteuse personne = new PersonneNecessiteuse(rs.getLong(1), rs.getString(2),
						rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7),""+rs.getDate(8), rs.getString(9), rs.getString(10), rs.getString(11), 
						rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15),
						rs.getString(16), rs.getString(17), rs.getString(18), rs.getInt(19),rs.getString(20));
				return personne;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	
	
	public static int delete(long id) {
		
		String requete= "DELETE FROM PERSONNENECESSITEUSE WHERE id=?";
		try {
			pst= connection.prepareStatement(requete);
			pst.setLong(1, id);			
			return pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}
}