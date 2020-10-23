package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class PointService {

    public boolean savePoint(String userId,Float point){
        Connection connection = Database.connect();

        try{
            String query = String.format("INSERT INTO BANGDIEM(MASV,DIEM,LAN) values('%s',%f,1)",userId,point);
            System.out.println("query: " +query);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("insert point successful");

            return true;
        }
        catch (Exception e){
            System.out.println("save point error: " +e);
        }
        return false;
    }


}
