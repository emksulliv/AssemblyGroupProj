
/**
 * ASSEMBLY FINAL GROUP PROJECT
 * The problem we are solving: Maximizing the number of classes held on 
 * campus while remaining within safe university covid population guidelines.
 * 
 * we are using a point based system where we will utilize each class's description
 * section, and will assign point values based on keywords found within that section. 
 * the highest point values = the higher priority to be held on campus.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

class QuickStart {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);

        System.out.println("\n\033[1m\033[4m\n\nWelcome To The Semester Class Scheduler!\033[0m\033[0m\n\n");

        // print out the menu to the app. The user will print out a number to select an
        // option
        /**
         * TODO: put this in a while loop where menu options will print every time the
         * user prints anything other than '4' (to exit).
         **/

        // \033[1m STRING \033[0m = BOLD TEXT
        // \033[4m STRING \033[0m = UNDERLINED TEXT
        // \033[3m STRING \033[0m = ITALIC TEXT

        System.out.println("\033[4mPlease Pick an Option: \033[0m");
        System.out.println("1. List all classes available");
        System.out.println("2. Add a class to the database");
        System.out.println("3. Plan semester's classes");
        System.out.println("4. exit\n\n");

        
        String menu_selection;
        System.out.println("Enter an option to continue: ");
        menu_selection = user_input.next();
        while(!(menu_selection.equals("4"))){
            switch (menu_selection) {
                case "1":
                    System.out.println("You selected 1\n");
                    try {
                        DatabaseConnection.query("SELECT * FROM ClassSchedule");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    System.out.println("You selected 2\n");
                    String input;
                    String sqlStr = "INSERT INTO ClassSchedule VALUES(";
                    System.out.println("Class Code: ");
                    input = user_input.next();
                    sqlStr += "'" + input + "',";
                    System.out.println("Description: ");
                    user_input.nextLine();
                    input = user_input.nextLine();
                    sqlStr += "'" + input + "',";
                    System.out.println("Class Size: ");
                    input = user_input.next();
                    sqlStr += "'" + input + "','5','TBD')";

                    try {
                        DatabaseConnection.query(sqlStr);
                        System.out.println("Success!");
                    }
                    catch(Exception e){
                        System.out.println("Failed To Add Class");
                        e.printStackTrace();
                    }

                    break;
                case "3":
                    System.out.println("You selected 3\n");
                    // TODO Full logic of
                    // assigning point values to keywords
                    // and filling in schedule of what is online and
                    // what is in person
                    // TODO printing that
                    ArrayList<Course> classes = null; 
                    try {
                        classes = DatabaseConnection.editQuery("SELECT * FROM ClassSchedule");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    for(int i=0;i<classes.size();i++){
                        int priority = 0;
                        String type = "";
                        // ****************************
                        // TODO making priotity points
                        //*****************************
                        if(classes.get(i).getDescription().contains("freshman class") ||
                            classes.get(i).getDescription().contains("graduate class") ||
                            (classes.get(i).getClassSize() < 49))
                            {priority = 5;type = "In-Person";}
                        else if(classes.get(i).getDescription().contains("sophomore class") ||
                            classes.get(i).getDescription().contains("science class") ||
                            classes.get(i).getDescription().contains("lab") ||
                            ((classes.get(i).getClassSize() > 50)&&(classes.get(i).getClassSize() < 99)))
                            {priority = 4;type = "Hybrid";}
                        else if(classes.get(i).getDescription().contains("transfer class") ||
                            ((classes.get(i).getClassSize() > 100)&&(classes.get(i).getClassSize() < 199)))
                            {priority = 3;type = "Hybrid";}
                        else if(classes.get(i).getDescription().contains("junior class") ||
                            ((classes.get(i).getClassSize() > 200)&&(classes.get(i).getClassSize() < 299)))
                            {priority = 2;type = "Online";}
                        else if(classes.get(i).getDescription().contains("senior class") ||
                            (classes.get(i).getClassSize() > 300))
                            {priority = 1;type = "Online";}
                        else
                            {priority = 1;type = "Online";}

                        classes.get(i).setPriority(priority);
                        classes.get(i).setType(type);

                        String str = "UPDATE ClassSchedule SET Priority = '" + Integer.toString(classes.get(i).getPriority()) +
                                        "', Type = '"+ classes.get(i).getType() +
                                        "' WHERE ClassCode = '"+ classes.get(i).getClassCode() +"'";
                        try {
                            DatabaseConnection.query(str);
                            System.out.println(classes.get(i).toString());
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case "4":
                    System.out.println("You selected 4\n");
                    break;
                default:
                    System.out.println("Invalid Menu Input");
                    break;

            }
        System.out.println("\033[4mPlease Pick an Option: \033[0m");
        System.out.println("1. List all classes available");
        System.out.println("2. Add a class to the database");
        System.out.println("3. Plan semester's classes");
        System.out.println("4. exit\n\n");

        System.out.println("Enter an option to continue: ");
        menu_selection = user_input.next();
        }
        System.out.println("Exiting Program");
        user_input.close();
    }
}
