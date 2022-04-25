package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.fsb.act.App;
import org.fsb.act.controllers.EvenementController;
import org.fsb.act.controllers.NotificationEventController;
import org.fsb.act.controllers.NotificationValidationController;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.dcn.DatabaseChangeRegistration;

public class ConnxDB {
    private static Connection connexion;
    public static OracleConnection oracleConnecxion;
    public static DatabaseChangeRegistration dcr;
    
    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "ACT1";
    private final String PASS = "act";

    private ConnxDB() throws SQLException{

        try{
               Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        connexion= DriverManager.getConnection(DB_URL, USER, PASS);

    }
    private ConnxDB(String surcharge) throws SQLException{

        try{
               Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        OracleDriver dr = new OracleDriver();
	    Properties prop = new Properties();
	    prop.setProperty("user",USER);
	    prop.setProperty("password",PASS);
	    oracleConnecxion = (OracleConnection)dr.connect(DB_URL,prop);
	    
	    Properties prop2 = new Properties();
	    
	    prop2.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS,"true");
	 
	    //Set the DCN_QUERY_CHANGE_NOTIFICATION option for query registration with finer granularity.
	    prop2.setProperty(OracleConnection.DCN_QUERY_CHANGE_NOTIFICATION,"true");
	    
	    dcr = oracleConnecxion.registerDatabaseChangeNotification(prop2);
	    // add the listenerr:
	    DCNDemoListener list = new DCNDemoListener();
	    dcr.addListener(list);
	    
	    Statement stmt = oracleConnecxion.createStatement();
	    // associate the statement with the registration:
	    ((OracleStatement)stmt).setDatabaseChangeRegistration(dcr);
	    ResultSet rs = stmt.executeQuery("select id from notificationEvenments ");
	    while (rs.next())
	    {}
	    rs= stmt.executeQuery("select id from notificationvalidation ");
	    while (rs.next())
	    {}
	    String[] tableNames = dcr.getTables();
	    for(int i=0;i<tableNames.length;i++)
	    	System.out.println(tableNames[i]+" is part of the registration.");
	    rs.close();
	    stmt.close();
    }

    public static Connection getInstance(){
        if (connexion == null)
            try {
                new ConnxDB();
            }catch(Exception e){
                System.out.println("--"+e.getMessage());
            }
        return connexion;
    }
    public static Connection getInstanceOracle(){
        if (oracleConnecxion == null)
            try {
                new ConnxDB("surchage");
            }catch(Exception e){
                System.out.println("--"+e.getMessage());
            }
        return oracleConnecxion;
    }
    
    private	 class DCNDemoListener implements DatabaseChangeListener{
  	  
  	  public void onDatabaseChangeNotification(DatabaseChangeEvent e)
  	  {
  		System.out.println("==databasechange==");
  		int test=-1;
  		String[] description= e.toString().split("\n");
  		for(String d: description)
  			if(d.matches(".*operation=\\[INSERT\\].*tableName=ACT\\.NOTIFICATIONEVENMENTS.*"))
  				test=1;
  			else if(d.matches(".*operation=\\[INSERT\\].*tableName=ACT\\.NOTIFICATIONVALIDATION.*"))
  				test=0;
  		System.out.println("==fin==");
  		
  	    System.out.println("DCNDemoListener: got an event running on thread ");
  	    System.out.println(e.toString());
  	    if(test==1)
  	    	javafx.application.Platform.runLater(()-> {
  	    		NotificationEventController.listControllersNotif.forEach(nec->{ if(nec !=null) nec.getAll();});
  	    		
  	    	});
  	    else if(test==0)
  	    	javafx.application.Platform.runLater(()-> {
  	    		NotificationValidationController.listControllersNotif.forEach(nvc->{ if(nvc !=null) nvc.getAll();});
  	    		
  	    	});
  	  }
  }
}
