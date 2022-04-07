package jdbcintroduction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {

    final static String[][] testData = {
        { "Max", "Mustermann", "55" },
        { "Erika", "Musterfrau", "44" }
    };

    static Connection connection = null;

    public static void main(String[] args) {
        // Driver class names for 3 different database systems
        final String MYSQL = "com.mysql.cj.jdbc.Driver";
        final String ORACLE = "oracle.jdbc.driver.OracleDriver";
        final String POSTGRESQL = "org.postgresql.Driver";

        try {
            Class.forName(MYSQL);
            Class.forName(ORACLE);
            Class.forName(POSTGRESQL);
        } catch (Exception ex) {
            System.err.println("Driver not found! " + ex);
            return;
        } 

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/wild", "kurzs", "mysql");
            
            // Example for a connection to a Oracle database:
            // connection = DriverManager.getConnection ("jdbc:oracle:thin:@myhost:1521:orcl", "scott", "tiger");
            // (see https://docs.oracle.com/cd/F49540_01/DOC/java.815/a64685/basic1.htm)

            // Example for a connection to a PostgreSQL database:
            // connection = DriverManager.getConnection ("jdbc:postgresql://localhost:5432/wild", "scott", "tiger");
            // (see https://www.postgresql.org/docs/7.4/jdbc-use.html)
        }
        catch (Exception ex) {
            System.err.println("Could not open connection! " + ex);
            return;
        }

        select();
        insert();
        select();
        update();
        select();
        delete();
        select();

        try {
            connection.close();
        }
        catch (Exception ex) {
            System.err.println("Could not close connection! " + ex);
        }

    }

    // Insert new records into the persons table
    public static void insert() {
        try {
            PreparedStatement insertStatement = connection.prepareStatement( "INSERT INTO persons ( firstname, lastname, age ) VALUES ( ?, ?, ? )");
            for (String[] row : testData) {
                insertStatement.setString(1, row[0] );
                insertStatement.setString(2, row[1] );
                insertStatement.setInt(3, Integer.parseInt(row[2]) );
                insertStatement.execute();
            }
            System.out.println(testData.length + " records inserted.");
            insertStatement.close();
        }
        catch (Exception ex) {
            System.err.println("Insert failed! " + ex);
        }
    }

    // update at least one record in the persons table
    public static void update() {
        try {
            Statement updateStatement = connection.createStatement();
            int count = updateStatement.executeUpdate("UPDATE persons SET lastname=\"Musterfrau\" WHERE lastname=\"Mustermann\" ");
            System.out.println(count + " records updated.");
            updateStatement.close();
        }
        catch (Exception ex) {
            System.err.println("Update failed! " + ex);
        }
    }

    // Delete at least two records in the persons table
    public static void delete() {
        try {
            Statement deleteStatement = connection.createStatement();
            int count = deleteStatement.executeUpdate("DELETE FROM persons WHERE lastname=\"Musterfrau\" ");
            System.out.println(count + " records deleted.");
            deleteStatement.close();
        }
        catch (Exception ex) {
            System.err.println("Delete failed! " + ex);
        }
    }

    // Read all rows from the persons database table
    public static void select() {
        Statement selectStatement = null;
        try {
            selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM persons");
            while(resultSet.next()) {
                System.out.print(resultSet.getString(1) + "\t"); // Firstname
                System.out.print(resultSet.getString(2) + "\t"); // Lastname
                System.out.println(resultSet.getInt(3)); // Age
            }
            resultSet.close();
            selectStatement.close();
            System.out.println();
        }
        catch (Exception ex) {
            System.err.println("Query failed! " + ex);
        }
    }
}
