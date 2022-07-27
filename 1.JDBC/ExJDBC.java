import java.sql.*;
import java.util.Scanner;

public class ExJDBC {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", "root", "May2022!");
        Scanner scanner = new Scanner(System.in);
        Statement  statement = connection.createStatement();
        System.out.println("Select num");
        double salary = Double.parseDouble(scanner.nextLine());

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE salary > " + salary);

        while (resultSet.next()){
            String jobTitle = resultSet.getString(5);
            long id = resultSet.getLong(1);
            System.out.println(id);
            System.out.println(jobTitle);
        }
        connection.close();
    }
}
