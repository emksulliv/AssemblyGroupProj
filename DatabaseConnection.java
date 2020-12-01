import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    static boolean query(String sql) throws Exception {
        //using Microsoft JDBC DRIVER 8.4 for SQL Server
        //default port 1433 & Integrated Security configured
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=UMBC Classes;integratedSecurity=true;";

        ResultSet result = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl); 
            Statement statement = connection.createStatement();){

            //Query Execution
             if(sql.contains("SELECT")){
                result = statement.executeQuery(sql);
                while (result.next()) {
                    System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+
                    result.getString(4)+" "+result.getString(5)+" "+result.getString(6));
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
    //Sample Records in Database:
    //CMSC313 2345 Online 1
    //CMSC304 3675 Online 2
    // ...
}
