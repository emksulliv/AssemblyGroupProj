import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    static boolean query(String sql) throws Exception {
        //using Microsoft JDBC DRIVER 8.4 for SQL Server
        //default port 1433 & Integrated Security configured
        //Test
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=UMBC Classes;integratedSecurity=true;";

        ResultSet result = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl); 
            Statement statement = connection.createStatement();){

            //Query Execution
             if(sql.contains("SELECT")){
                result = statement.executeQuery(sql);
                while (result.next()) {
                    System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+
                    result.getString(4));
                }
            }
            else{
                statement.execute(sql);
            }
        

            connection.close();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
        /*//using Microsoft JDBC DRIVER 8.4 for SQL Server
        //default port 1433 & Integrated Security configured
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=UMBC Classes;integratedSecurity=true;";

        ResultSet result = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl); 
            Statement selectStatement = connection.createStatement(); Statement insertStatement = connection.createStatement();) {

            if(connection.isValid(10))
            System.out.println("Connection Successful");
            else
            System.out.println("Connection Timed Out");

            //Query Actions
            String sql = "SELECT * FROM Classes";
            String insertSql = "INSERT INTO Classes VALUES('CMSC341','2783','Hybrid','3');";

            insertStatement.execute(insertSql);
            result = selectStatement.executeQuery(sql);

            // Print results from select statement
            while (result.next()) {
                System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+
                result.getString(4));
            }
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Connection Not Successful");
            e.printStackTrace();
        }*/

        DatabaseConnection.query("SELECT * FROM Classes");
    }
}
