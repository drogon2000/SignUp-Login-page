package login1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/savelogin")
public class StoreData extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=req.getParameter("name");
		String address=req.getParameter("address");
		String email=req.getParameter("email");
		String dob=req.getParameter("dob");
		String username=req.getParameter("username");
		String pass=req.getParameter("pass");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login" ,"root","Patya@2000");
			PreparedStatement ps=con.prepareStatement("insert into userdata(name,address,email,DateOfBirth,username,password)values(?,?,?,?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, email);
			ps.setString(4, dob);
			ps.setString(5, username);
			ps.setString(6, pass);
			
			ps.execute();
			
			PrintWriter pw=res.getWriter();
			pw.write("Account created");
			RequestDispatcher rs=req.getRequestDispatcher("LogIn.html");
			rs.forward(req, res);
			res.setContentType("text/html");
			}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
