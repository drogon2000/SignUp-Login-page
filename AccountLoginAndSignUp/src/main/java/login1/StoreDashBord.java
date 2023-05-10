package login1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
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
import javax.servlet.http.HttpServlet;
@WebServlet("/abc")
public class StoreDashBord extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String description=req.getParameter("description");
		String attachment=req.getParameter("attachment");
		String addattachment=req.getParameter("addattachment");
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login" ,"root","Patya@2000");
			PreparedStatement ps=con.prepareStatement("insert into dashbord(description,attachment,addattachment)values(?,?,?)");
			
			FileInputStream fin=new FileInputStream("addattachment");
			ps.setString(1, description);
			ps.setString(2, attachment);
			ps.setBlob(3, fin);
			
			
			ps.execute();
			
//			PrintWriter pw=res.getWriter();
//			pw.write("Account created");
//			RequestDispatcher rs=req.getRequestDispatcher("LogIn.html");
//			rs.forward(req, res);
//			res.setContentType("text/html");
			
			}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
