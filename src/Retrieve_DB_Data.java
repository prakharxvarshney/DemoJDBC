// Iss class me hum jaanne wale hai DB me se data kaise retrieve kare.

/*
Database ko access karna hai to SQL use karna padega aur uske liye humko
SQL ke package ko import karna hoga.
*/
import java.sql.*;

public class Retrieve_DB_Data {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/xyz";
    private static final String userName = "root";
    private static final String password = "Prakhar@112";

    public static void main(String[] args) {
        try {
            /* Second step hai - Ab driver class initialize krni hai jisse MySQL se connect kar sake*/
            Class.forName("com.mysql.cj.jdbc.Driver");

            /*Connection Class ka object create krenge taaki database ke sath connection built kar sake*/
            Connection connection = DriverManager.getConnection(url,userName,password);

            // Fourth Step - Create Statement
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student");

            /* Ab jo humne query likhi hai data retrieve karne ke liye, usse jo data aayega usko store krne ke liye
            java ke paas ek class hai "Result Set" ke naam se ab hum uska use kr rahe hai */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int roll_number = resultSet.getInt("roll_number");
                String student_name = resultSet.getString("student_name");
                String student_age = resultSet.getString("class_name");
                System.out.println(roll_number + "               " + student_name + "               " + student_age);
            }

            // ab sara kaam ho chuka hai to connection close kar dete hai
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
