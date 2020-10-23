package Server;

import Util.Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connect(){
        try{
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databasename=KIEMTRALTM;user=MAO;password=1";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                Util.log_success("database connected successful");
                return conn;
            }
        }catch (Exception e){
            Util.log_error("sql error: "+e);
        }
        return null;
    }

}
