package vc.database;

/**
 * <p>Copyright: 2005</p>
 
 */
import java.sql.*;
import vc.database.DBPool;

public class BaseDAO {
    //==========================================================================
    public void releaseConnection(Connection conn, PreparedStatement preStmt) {
        try {
            if (preStmt != null) preStmt.close();
        } catch (SQLException e) {}
        DBPool.putConnection(conn);
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
        releaseConnection(conn, preStmt);
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {}
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
        releaseConnection(conn, preStmt, rs);
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {}
    }

}
