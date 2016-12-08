import java.sql.*;

class DbGacha {
    private Connection conn;
    DbGacha() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gachadb",
                "root",
                ""
        );
    }
   // public boolean isConnected() {return (conn!=null);}

    void insertUsr(
            String usr,
            String pwd
    )throws Exception{
        String query = "INSERT INTO user(user_name, password) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, usr);
        stmt.setString(2, pwd);
        stmt.execute();
        stmt.close();
    }

    boolean chkUsr(
            String usr
    )throws Exception{
        String query = "SELECT COUNT(*) as flg FROM user where user_name=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, usr);
        ResultSet rs = stmt.executeQuery();
        int flg = 0;
        boolean f = false;
        while(rs.next()) {
            flg = rs.getInt("flg");
        }
        stmt.close();
        if (flg == 1) f = true;
        return f;
    }
    void updMny(
            String usr,
            int mny
    )throws Exception{
        String query = "UPDATE user SET money=? WHERE user_name=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, mny);
        stmt.setString(2, usr);
        stmt.execute();
        stmt.close();
    }

    int getMny(
            String usr
    )throws Exception{
        String query = "SELECT money FROM user where user_name=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, usr);
        ResultSet rs = stmt.executeQuery();
        int money = 0;
        while(rs.next()) {
            money = rs.getInt("money");
        }
        stmt.close();
        return money;
    }

    boolean getUsr(
            String usr,
            String pwd
    )throws Exception{
        String query = "SELECT COUNT(*) as flg FROM user where user_name=? and password=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, usr);
        stmt.setString(2, pwd);
        ResultSet rs = stmt.executeQuery();
        int flg = 0;
        boolean f = false;
        while(rs.next()) {
            flg = rs.getInt("flg");
        }
        stmt.close();
        if (flg == 1) f = true;
        return f;
    }
}