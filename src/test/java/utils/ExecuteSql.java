package utils;

import init.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteSql {

    public static void runSql(String sql) {
        Statement stmt = null;
        try {
            System.out.println("start run sql : " + sql);
            final Connection conn = DBConnection.getConn();
            stmt = conn.createStatement();
            final boolean isResultSet = stmt.execute(sql);
            // execute insert or update sql will return null.
            if (isResultSet) {
                final ResultSet rslt = stmt.getResultSet();
                /*while (rslt.next()) {
                    rslt.getString("first_name");
                }*/
                rslt.close();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }
    }
}

