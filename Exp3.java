package jdbcodbc;

import java.sql.*;

public class Exp3 {
    public static void main(String[] args)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root",""); 
    	System.out.println("Connected to the Database");
    	con.setAutoCommit(false);
    	String sql="select * from Employee";
    	Statement stmt = con.createStatement();
    	ResultSet rs = stmt.executeQuery(sql);
	    System.out.println("Before Insert");
	    while(rs.next()) {
	    	System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3));
	    }
	    System.out.println();
        String insert = "insert into Employee values(?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, "Anshu");
        ps.setInt(2, 449);
        ps.setString(3, "Mech");
        ps.executeUpdate();
        ps.setString(1, "Anshu kumar");
        ps.setInt(2, 420);
        ps.setString(3, "ECE");
        ps.executeUpdate();
        rs = stmt.executeQuery(sql);
        System.out.println("After Insert");
        while (rs.next()) {
        	System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3));
        }
        System.out.println();
        con.rollback();
        System.out.println("After Rollback");
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
        	System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3));
        }
        System.out.println();
        ps.setString(1, "Mohan");
        ps.setInt(2, 41);
        ps.setString(3, "ECE");
        ps.executeUpdate();
        ps.setString(1, "sohan");
        ps.setInt(2, 12);
        ps.setString(3, "cse");  
        ps.executeUpdate();
        con.commit();
        System.out.println("After Commit");
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
        	System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3));
            }
        System.out.println();
        con.close();
    }
}
