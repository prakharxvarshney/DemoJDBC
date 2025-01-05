//First Step - import required package//

import java.sql.*;
import java.util.Scanner;

public class Main {
    //Third Step - Build connection;
    private static final String url = "jdbc:mysql://127.0.0.1:3306/xyz";
    private static final String userName = "root";
    private static final String Password = "Prakhar@112";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        try {
            //Second step - Driver Load
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,userName,Password);

            // Fourth Step - Create Statement
            //Statement statement = connection.createStatement();
            /*
            We should use a 'Prepared Statement' instead of a 'Statement' because, when the program is executed repeatedly,
            the query in a 'Statement' is compiled every time before execution. In contrast, with a 'Prepared Statement,'
            the query is compiled once and can be executed multiple times.
            Now, I am using Prepared Statement.
            */

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?)");

            // I want user data entry count for better efficieny
            System.out.print("How many student do you want to insert - ");
            int entryCount = scan.nextInt();

            /* Ab mere pass count aa chuka hai to ab mai ek loop chala dunga jisse user input tab tak leta rahe jab tak
               loop terminate na ho jaaye jisse kya hoga user ko baar baar student enter krne ke liye program execute na krna pade
             */

            for(int i=1; i<=entryCount; i++) {
                System.out.println("Please enter the number "+i+" student details");
                System.out.print("Enter the roll number - ");
                int roll_number = scan.nextInt();
                System.out.print("Enter the student name - ");
                String student_name = scan.next();
                System.out.print("Enter the student age - ");
                String student_age = scan.next();
                System.out.println("////////////////////////////////////////////////////////");
                preparedStatement.setInt(1,roll_number);
                preparedStatement.setString(2,student_name);
                preparedStatement.setString(3,student_age);

                //Fifth Step - Execute Queries
                preparedStatement.executeUpdate();
            }

            //Sixth Step - Close connection because of security reasons
            connection.close();

            // Niche diye huye print ke through hum check kr rahe hai kii agar query execute ho gayi hai to likha aa jaaye
            System.out.print("Data inserted successfully");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}