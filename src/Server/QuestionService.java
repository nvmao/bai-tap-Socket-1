package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public  List<Question> getQuestions(){
        Connection connection = Database.connect();

        List<Question> questions = new ArrayList<Question>();

        try{
            String query = String.format("SELECT TOP(10) NOIDUNG,A,B,C,D,DAP_AN FROM BODE ORDER BY NEWID()");
            System.out.println("query: " +query);

            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);
            while (data.next())
            {
                Question question = new Question(
                        data.getString("NOIDUNG"),
                        data.getString("A"),
                        data.getString("B"),
                        data.getString("C"),
                        data.getString("D"),
                        data.getString("DAP_AN")
                );

                questions.add(question);
            }
            return questions;
        }
        catch (Exception e){
            System.out.println("questions error: " +e);
        }

        return null;
    }

}
