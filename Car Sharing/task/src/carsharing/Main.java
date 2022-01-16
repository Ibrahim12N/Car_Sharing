package carsharing;

import carsharing.Conn.DBConn;
import carsharing.menu.Menu;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String fileName = "default";
        if (args.length > 1) {
            if (args[0].equals("-databaseFileName")) {
                fileName = args[1];
            }
        }

        DBConn dbConn = new DBConn();
        Statement stmt = dbConn.openConnection(fileName);

        Menu menu = new Menu(stmt);
        menu.menu();

        dbConn.closeConnection();

    }

}



