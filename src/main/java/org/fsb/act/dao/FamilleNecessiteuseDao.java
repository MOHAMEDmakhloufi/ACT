package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.fsb.act.entities.FamilleNecessiteuse;
import org.fsb.act.entities.PersonneNecessiteuse;

public class FamilleNecessiteuseDao {
	private static Connection connection= ConnxDB.getInstance();
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	public static List<FamilleNecessiteuse> getAll(){
		List<FamilleNecessiteuse> list =new ArrayList<>();
		String requete="SELECT * FROM FAMILLENECESSITEUSE";
		try {
			pst= connection.prepareStatement(requete);
			rs=pst.executeQuery();
			while(rs.next()) {
				FamilleNecessiteuse fn= new FamilleNecessiteuse();
				fn.setId(rs.getLong(1));
				fn.setPere(new PersonneNecessiteuse(rs.getLong(2)));
				fn.setMere(new PersonneNecessiteuse(rs.getLong(3)));
				//fils
				requete="SELECT FILS FROM FILSFAMILLE WHERE FAMILLE=?";
				pst= connection.prepareStatement(requete);
				pst.setLong(1, fn.getId());
				ResultSet rsFils= pst.executeQuery();
				while(rsFils.next()) {
					fn.getListFils().add(new PersonneNecessiteuse(rsFils.getLong(1)));
				}
				list.add(fn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int deleteFamilleFromDB(long id) {
		String requete="DELETE FROM FILSFAMILLE WHERE FAMILLE=?";
		try {
			pst= connection.prepareStatement(requete);
			pst.setLong(1, id);
			
			return pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static long addFamilleInDb(FamilleNecessiteuse fn) {
		boolean test= true;
		long idFamille=0;
		String requete= "INSERT INTO FAMILLENECESSITEUSE VALUES(SEQFAMILLENECESSITEUSE.nextval, ?, ?)";
		try {
			
			pst= connection.prepareStatement(requete);
			pst.setLong(1, fn.getPere().getId());
			pst.setLong(2, fn.getMere().getId());
			int i=pst.executeUpdate();
			if(i==1) {
				pst=connection.prepareStatement("select SEQFAMILLENECESSITEUSE.currval from dual");
            	rs=pst.executeQuery();
            	if(rs.next()) {
                    
        			idFamille=  rs.getLong(1);
        			requete="INSERT INTO FILSFAMILLE VALUES(?, ?)";
        			
        			for(PersonneNecessiteuse fils: fn.getListFils()) {
        				pst=connection.prepareStatement(requete);
            			pst.setLong(1, idFamille);
            			pst.setLong(2, fils.getId());
            			int j= pst.executeUpdate();
            			if(j!=1) {
            				test= false;
            			}
        			}
        			
            	}
			}
			if(test) {
				
				return idFamille;
			}
				
            
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return 0;
		}
		
		return 0;
	}

	public static int updateFamilleInDb(FamilleNecessiteuse fn) {
		boolean test= true;
		String requete="UPDATE FAMILLENECESSITEUSE SET pere=?, mere=? WHERE ID=?";
		try {
			pst=connection.prepareStatement(requete);
			pst.setLong(1, fn.getPere().getId());
			pst.setLong(2, fn.getMere().getId());
			pst.setLong(3, fn.getId());
			int i= pst.executeUpdate();
			if(i==1) {
				
				requete="INSERT INTO FILSFAMILLE VALUES(?, ?)";
    			
    			for(PersonneNecessiteuse fils: fn.getListFils()) {
    				pst=connection.prepareStatement(requete);
        			pst.setLong(1, fn.getId());
        			pst.setLong(2, fils.getId());
        			int j= pst.executeUpdate();
        			if(j!=1) {
        				test= false;
        			}
    			}
			}
			if(test) {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public static FamilleNecessiteuse getOneById(long id) {
		
		FamilleNecessiteuse fn = new FamilleNecessiteuse();
		String requete="SELECT * FROM FAMILLENECESSITEUSE WHERE id=?";
		try {
			pst= connection.prepareStatement(requete);
			pst.setLong(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				fn.setId(rs.getLong(1));
				fn.setPere(new PersonneNecessiteuse(rs.getLong(2)));
				fn.setMere(new PersonneNecessiteuse(rs.getLong(3)));
				//fils
				requete="SELECT FILS FROM FILSFAMILLE WHERE FAMILLE=?";
				pst= connection.prepareStatement(requete);
				pst.setLong(1, fn.getId());
				ResultSet rsFils= pst.executeQuery();
				while(rsFils.next()) {
					fn.getListFils().add(new PersonneNecessiteuse(rsFils.getLong(1)));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fn;
	}
}
