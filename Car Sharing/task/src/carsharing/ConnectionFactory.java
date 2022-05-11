package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static String DB_NAME = "car_sharing_db";
    private static String DB_URL = "jdbc:h2:./task/src/carsharing/db/" + DB_NAME;

    static final String USER = "sa";
    static final String PASS = "";

    private ConnectionFactory() {
    }


    public static void setDbName(String dbName) {
        DB_NAME = dbName;
        DB_URL = "jdbc:h2:../task/src/carsharing/db/" + DB_NAME;
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL);
            con.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }


}
