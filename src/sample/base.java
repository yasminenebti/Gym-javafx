package sample;


import java.sql.*;
public class base {
    public Connection cn = null;
    public static Connection connDB()  {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gym", "root", "NUNRMISY");
            String rq="select * FROM Membership";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(rq);
            while(rs.next()){
                System.out.println("connexion success");
            }
            return cn;
        }catch( SQLException e) {
            System.out.println("connection failed");
            e.printStackTrace();
            return null;
        }
    }

}

//System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getString(4)+" | "+rs.getString(5));


