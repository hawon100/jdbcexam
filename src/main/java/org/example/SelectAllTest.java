package org.example;

import java.sql.*;

public class SelectAllTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        try {
            conn =
                    DriverManager.getConnection(
                            "jdbc:mysql://127.0.0.1:3306/samplesdb?useUnicode=true&serverTimezone=Asia/Seoul",
                            "rootoj",
                            "1234");
            if(conn != null) {
                System.out.println("DBMS 연결 성공!!");
                System.out.println(conn.getClass().getName());
            }

            ps = conn.prepareStatement("select role_id, name from role");

            rs = ps.executeQuery();

            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                String name = rs.getString("name");
                System.out.println(roleId + ", " + name);
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            try {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
    }
}
