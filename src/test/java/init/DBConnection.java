package init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static init.RomProperty.*;


public class DBConnection {

    private static Connection conn = null;

    public static Connection getConn() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String driver = getPropertyValue("driver");
        String url = getPropertyValue("url");
        String username = getPropertyValue("username");
        String password = getPropertyValue("password");

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) {
                conn.close();
            }
            throw new SQLException(e);
        }
        return conn;
    }

}
