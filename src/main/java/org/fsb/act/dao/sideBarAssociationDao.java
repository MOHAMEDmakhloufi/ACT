package org.fsb.act.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.image.Image;
import models.LogoTitre;

public class sideBarAssociationDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	/**
	 * get logo association et titre asscoiation from data base
	 * @return
	 */
	public static LogoTitre getLogoTitre() {
		LogoTitre logoTitre= new LogoTitre();
		
		String requet ="SELECT logo, titre FROM association ";
		try {
			pst= connection.prepareStatement(requet);
			rs=pst.executeQuery();
			if(rs.next()) {
				Blob b =rs.getBlob(1);
				if(b != null)
					logoTitre.setLogo(b.getBytes(1, (int)b.length()));
				
				logoTitre.setTitre(rs.getString(2));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return logoTitre;
	}
}
