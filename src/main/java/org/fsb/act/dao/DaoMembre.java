package org.fsb.act.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.fsb.act.entities.Membre;

public class DaoMembre {
	
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static List<Membre> getAll() {
		List<Membre> membres = new ArrayList<>();
		
		String requete= "SELECT * FROM MEMBRE";
		try {
			pst= connection.prepareStatement(requete);
			rs= pst.executeQuery();
			
			while(rs.next()) {
				long id= rs.getLong(1);
				String prenom= rs.getString(2);
				String nom= rs.getString(3);
				String dateNaissance= ""+rs.getDate(4);
				String profession= rs.getString(5);
				String email= rs.getString(6);
				String telephone= rs.getString(7);
				long numeroIdentite= rs.getLong(8);
				String typePiece= rs.getString(9);
				String dirigeant= rs.getString(12);
				Blob b1= rs.getBlob(10);
				Blob b2= rs.getBlob(11);
				byte[] image = null;
				byte[] copiePiece= null;
				if(b2 !=null)
					image=b2.getBytes(1, (int)b2.length());
				if(b1!=null)
					copiePiece= b1.getBytes(1, (int)b1.length());
				
				membres.add(new Membre(id, prenom, nom, dateNaissance, profession, email, telephone, typePiece, dirigeant, numeroIdentite, image, copiePiece));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		membres.sort(new Comparator<Membre>() {

			@Override
			public int compare(Membre o1, Membre o2) {
				
				return (int)(o2.getId() - o1.getId());
			}
		});
		return membres;
	}

	public static long addMembreInDb(Membre membre) {
		String requete="INSERT INTO MEMBRE VALUES(SEQMEMBRE.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			connection.setAutoCommit(false);
			pst= connection.prepareStatement(requete);
			pst.setString(1, membre.getPrenom());
			pst.setString(2, membre.getNom());
			pst.setDate(3, java.sql.Date.valueOf(membre.getDateNaissance()));
			pst.setString(4, membre.getProfession());
			pst.setString(5, membre.getEmail());
			pst.setString(6, membre.getTelephone());
			pst.setLong(7, membre.getNumeroIdentite());
			pst.setString(8, membre.getTypePiece());
			pst.setBytes(9, membre.getCopiePiece());
			pst.setBytes(10, membre.getImage());
			pst.setString(11, membre.getDirigeant());
			
			int i=pst.executeUpdate();
			
            if(i==1) {
            	pst=connection.prepareStatement("select SEQMEMBRE.currval from dual");
            	rs=pst.executeQuery();
            	if(rs.next()) {

            		
                    connection.commit();
                    connection.setAutoCommit(true);
        			return  rs.getLong(1);
            	}
            }
            
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
		
		return 0;
	}

	public static int deleteMembreFromDb(long id) {
		
		String requete= "DELETE FROM MEMBRE WHERE id=?";
		try {
			connection.setAutoCommit(true);
			pst= connection.prepareStatement(requete);
			pst.setLong(1, id);
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	public static int modifierMembreFromDb(Membre membre) {
		String requete= "UPDATE MEMBRE SET prenom=?, nom=?, datedenaissance=?, profession=?, email=?, telephone=?,"
				+ " typepiece=?, dirigeant=?, numeropieceidentite=?, image=?, copiepiece=?"
				+ " WHERE id=?";
		try {
			connection.setAutoCommit(true);
			pst= connection.prepareStatement(requete);
			pst.setString(1, membre.getPrenom());
			pst.setString(2, membre.getNom());
			pst.setDate(3, java.sql.Date.valueOf(membre.getDateNaissance()));
			pst.setString(4, membre.getProfession());
			pst.setString(5, membre.getEmail());
			pst.setString(6, membre.getTelephone());
			pst.setString(7, membre.getTypePiece());
			pst.setString(8, membre.getDirigeant());
			pst.setLong(9, membre.getNumeroIdentite());
			pst.setBytes(10, membre.getImage());
			pst.setBytes(11, membre.getCopiePiece());
			pst.setLong(12, membre.getId());
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

}
