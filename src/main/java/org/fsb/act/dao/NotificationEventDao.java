package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.fsb.act.entities.Evenement;
import org.fsb.act.entities.NotificationEvenement;

public class NotificationEventDao {
	
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static List<NotificationEvenement> getAllFromDb() {
		List<NotificationEvenement> list = new ArrayList<>();
		String requete= "SELECT * FROM NOTIFICATIONEVENMENTS  ORDER BY datemisejour DESC";
		try {
			pst= connection.prepareStatement(requete);
			rs= pst.executeQuery();
			while(rs.next()) {
				
				list.add(new NotificationEvenement(rs.getLong(1), new Evenement(rs.getLong(2)), rs.getString(3), rs.getString(4), ""+rs.getDate(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static int updateValidationFromDb(NotificationEvenement ne) {
		String requete="UPDATE NOTIFICATIONEVENMENTS SET validation=?, dirigeant=? WHERE id=?";
		try {
			pst= connection.prepareStatement(requete);
			pst.setString(1, ne.getValidation());
			pst.setString(2, ne.getDirigeant());
			pst.setLong(3, ne.getId());
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
