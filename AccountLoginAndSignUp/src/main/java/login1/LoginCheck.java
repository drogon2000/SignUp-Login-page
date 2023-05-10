package login1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/cklog")
public class LoginCheck extends HttpServlet {
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String username=req.getParameter("username");
	String pass=req.getParameter("pass");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login" ,"root","Patya@2000");
		PreparedStatement ps=con.prepareStatement("select * from userdata where username=? and password=?");
		
		
		ps.setString(1, username);
		ps.setString(2, pass);
		
	    ResultSet rs=ps.executeQuery();	
	   
	    if( rs.next())
		{
			RequestDispatcher rd=req.getRequestDispatcher("Dashbord.html");
			rd.forward(req, resp);
		}
		else 
		{
			PrintWriter pr=resp.getWriter();
			pr.write("wrong credentials");
			
			RequestDispatcher rr=req.getRequestDispatcher("index.jsp");
			rr.include(req, resp);
			resp.setContentType("text/html");
		}
//		PrintWriter pw=res.getWriter();
//		pw.write("Account created");
//		RequestDispatcher rs=req.getRequestDispatcher("LogIn.html");
//		rs.forward(req, res);
//		res.setContentType("text/html");
		}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
}
}
