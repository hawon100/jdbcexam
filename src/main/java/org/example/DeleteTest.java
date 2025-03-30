package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
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

            ps = conn.prepareStatement("delete from role where role_id = ?");

            ps.setInt(1, 3);

            int updateCount = ps.executeUpdate();
            System.out.println("수정된 건수 : " + updateCount);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            try {
                if(ps != null) ps.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
    }
}
