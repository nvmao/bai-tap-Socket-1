package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {


    public User Login(String username,String password){
        Connection connection = Database.connect();

        try{
            String query = String.format("SELECT UserName,PassWord,MASV FROM SINHVIEN WHERE UserName='%s' AND PassWord='%s'",username,password);
            System.out.println("query: " +query);

            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);
            while (data.next())
            {
                return new User(data.getString("UserName"),data.getString("PassWord"),data.getString("MASV"));
            }
        }
        catch (Exception e){
            System.out.println("user login error: " +e);
        }

        return null;

    }

}
