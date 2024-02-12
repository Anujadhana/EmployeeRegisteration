

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
import java.sql.SQLException;

@WebServlet("/EmployeeRegisteration")
public class EmployeeRegisteration extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public static final String query="INSERT INTO EMPLOYEE(ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, ADDRESS, CONTACT) VALUES(?,?,?,?,?,?,?)";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get parameter from html page
		PrintWriter out=response.getWriter();
		String myid=request.getParameter("id1");
		String myfirstname=request.getParameter("firstname1");
		String mylastname=request.getParameter("lastname1");
		String myusername=request.getParameter("username1");
		String mypassword=request.getParameter("password1");
		String myaddress=request.getParameter("address1");
		String contact=request.getParameter("contact1");
		
		//LoadDriver 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			System.out.println(cnf);
		}
		
		try(	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "root");
			PreparedStatement ps=con.prepareStatement(query);){
			ps.setString(1, myid);
			ps.setString(2, myfirstname);
			ps.setString(3, mylastname);
			ps.setString(4, myusername);
			ps.setString(5, mypassword);
			ps.setString(6, myaddress);
			ps.setString(7, contact);
			
			int count=ps.executeUpdate();
			if(count>0) {
				
				response.setContentType("text/html");
				out.println("<h3 style='color:red'> Employee Registered Succesfully</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/emp.jsp");
				rd.include(request, response);
				
			}else {
				
				response.setContentType("text/html");
				out.print("<h3 style='color:red'> Employee Not Registered Succesfully</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/emp.jsp");
				rd.include(request, response);
			}
			
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
		
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	

}
}
