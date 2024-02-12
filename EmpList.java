

import jakarta.servlet.RequestDispatcher;
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
import java.sql.SQLException;

@WebServlet("/EmpList")
public class EmpList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String query="SELECT ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, ADDRESS, CONTACT FROM EMPLOYEE";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get parameter from html page
		PrintWriter out=response.getWriter();
		//LoadDriver 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			System.out.println(cnf);
		}
		
		try(	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "root");
			PreparedStatement ps=con.prepareStatement(query);){
			ResultSet rs=ps.executeQuery();
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>ID</th>");
			out.println("<th>First Name</th>");
			out.println("<th>Last Name</th>");
			out.println("<th>User Name</th>");
			out.println("<th>Password</th>");
			out.println("<th>Address</th>");
			out.println("<th>Contact</th>");
			out.println("</tr>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td>");
				out.println("<td>"+rs.getString(6)+"</td>");
				out.println("<td>"+rs.getString(7)+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		
			
		}  catch (SQLException se) {
			
			response.setContentType("text/html");
			out.print("<h3 style='color:red'>"+ se.getMessage()+"</h3>");
			RequestDispatcher rd=request.getRequestDispatcher("/emp.jsp");
			rd.include(request, response);
			
			se.printStackTrace();
		}
		catch(Exception e){
			out.print("<h3 style='color:red'>"+ e.getMessage()+"</h3>");
		}
		out.println("<a href='emp.jsp'>Employee</a>");
		
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	

}

}
