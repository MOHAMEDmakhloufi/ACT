package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.fsb.act.App;
import org.fsb.act.entities.Evenement;
import org.fsb.act.entities.NotificationEvenement;
import org.fsb.act.entities.NotificationValidation;

public class NotificationValidationDao {
	
	private static Connection connection= ConnxDB.getInstanceOracle();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static List<NotificationValidation> getAllFromDb() {
		List<NotificationValidation> list = new ArrayList<>();
		String requete= "SELECT nv.*, e.dirigeant FROM NOTIFICATIONVALIDATION nv INNER JOIN EVENEMENT e ON nv.evenement= e.id  WHERE dirigeant=? ORDER BY datemisejour DESC";
		try {
			pst= connection.prepareStatement(requete);
			pst.setString(1, App.dirigeant);
			rs= pst.executeQuery();
			while(rs.next()) {
				NotificationValidation nv= new NotificationValidation();
				nv.setId(rs.getLong(1));
				nv.setNotifEvent(new NotificationEvenement(rs.getLong(2)));
				nv.setEvenement(new Evenement(rs.getLong(3)));
				nv.setDate(""+rs.getDate(4));
				
				list.add(nv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
