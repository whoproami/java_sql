package exp4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class exp4 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (validate(username, password)) {
            out.print("Hello, " + username + "!");
        } else {
            out.print("Sorry, incorrect username or password! Please try again.");
            out.print("<br><a href='login.html'>Login Again</a>");
        }

        out.close();
    }

    private boolean validate(String username, String password){
        boolean status = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee", "root",
                    "");
            System.out.println(con);

            // Change the query according to your table structure
            String query = "SELECT * FROM logindetails WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            status = rs.next();
            System.out.println(status);

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}