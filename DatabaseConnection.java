import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                    result.getString(4)+" "+result.getString(5));
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
    static ArrayList<Course> editQuery(String sql) throws Exception {
        //using Microsoft JDBC DRIVER 8.4 for SQL Server
        //default port 1433 & Integrated Security configured
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=UMBC Classes;integratedSecurity=true;";
        ArrayList<Course> classes = new ArrayList<Course>();
        ResultSet result = null;
        try(Connection connection = DriverManager.getConnection(connectionUrl); 
            Statement statement = connection.createStatement();){

            //Query Execution
            result = statement.executeQuery(sql);
            while (result.next()) {
                Course course = new Course(result.getString(1), result.getString(2), result.getInt(3),
                 result.getInt(4), result.getString(5));
                
                classes.add(course);
            }
        

            connection.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
    
}
