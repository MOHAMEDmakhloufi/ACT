package org.fsb.act.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnxDB {
    private static Connection connexion;

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

    public static Connection getInstance(){
        if (connexion == null)
            try {
                new ConnxDB();
            }catch(Exception e){
                System.out.println("--"+e.getMessage());
            }
        return connexion;
    }
}
