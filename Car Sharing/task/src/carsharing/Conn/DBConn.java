package carsharing.Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
    private static Connection conn;
    private static Statement stmt;
    private static String JDBC_DRIVER = "org.h2.Driver";
    private static String DB_URL = "jdbc:h2:./src/carsharing/db/";

    public Statement openConnection(String fileName) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL+fileName);
        conn.setAutoCommit(true);
        stmt = conn.createStatement();
        return stmt;
    }

    public void closeConnection() throws SQLException {
            stmt.close();
            conn.close();
    }

}
