
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
                    System.out.println("You selected 1");
                    // TODO: Sam
                    // TODO: listing out sql file (all classes) looking at tutorial
                    try {
                        DatabaseConnection.query("SELECT * FROM ClassSchedule");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    System.out.println("You selected 2");
                    // TODO: Emily
                    // PUsh class to sql file
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
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    break;
                case "3":
                    System.out.println("You selected 3");
                    // TODO Full logic of
                    // assigning point values to keywords
                    // and filling in schedule of what is online and
                    // what is in person
                    // TODO editing the database to fill in "online/inperson colun"
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
                        //
                        // TODO making priotity points
                        //
                        classes.get(i).setPriority(priority);
                        String str = "UPDATE ClassSchedule SET Priority = '" + Integer.toString(priority) +
                                        "', Type = '"+ type +
                                        "' WHERE ClassCode = '"+ classes.get(i).getClassCode() +"')";
                        try {
                            DatabaseConnection.query(str);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case "4":
                    System.out.println("You selected 4");
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
