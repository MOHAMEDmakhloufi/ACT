package org.fsb.act.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.fsb.act.entities.Association;

public class ModifierAssociationDao {
	
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static Association getDataAssociation() {
		Association association = new Association();
		String requete = "SELECT  * FROM ASSOCIATION ";
		try {
			pst= connection.prepareStatement(requete);
			rs= pst.executeQuery();
			if(rs.next()) {
				association.setTitre(rs.getString(2));
				association.setSiege(rs.getString(3)); 
				association.setEmail(rs.getString(4)); 
				association.setTelephone(rs.getString(5)); 
				association.setUsername(rs.getString(6)); 
				association.setPassword(rs.getString(7));
				Blob b =rs.getBlob(9);
				if(b != null)
					association.setLogo(b.getBytes(1, (int)b.length()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return association;
		
	}

	public static int update(Association association) {
		String requete = "UPDATE ASSOCIATION SET titre=?, siege=?, email=?, telephone=?, username=?, password=?, logo=?";
		
		try {
			pst= connection.prepareStatement(requete);
			pst.setString(1, association.getTitre());
			pst.setString(2, association.getSiege());
			pst.setString(3, association.getEmail());
			pst.setString(4, association.getTelephone());
			pst.setString(5, association.getUsername());
			pst.setString(6, association.getPassword());
			pst.setBytes(7, association.getLogo());
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
