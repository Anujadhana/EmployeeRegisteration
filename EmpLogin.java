

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/EmpLogin")
public class EmpLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		String myusername=request.getParameter("username1");
		String mypassword=request.getParameter("pass1");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db2","root","root");
			PreparedStatement ps=con.prepareStatement("select * from employee where username=? and password=?");
			ps.setString(1, myusername);
			ps.setString(2, mypassword);
			
			ResultSet rs=ps.executeQuery();
		    if(rs.next()) {
		    	
		    	HttpSession session=request.getSession();
		    	session.setAttribute("session_name", "name");
		    	RequestDispatcher rd=request.getRequestDispatcher("/empprofile.jsp");
		    	rd.include(request, response);   
		    }else {
		    	
		    	response.setContentType("text/html");
		    	out.println("<h3 style='color:red'> Email id and password did not match</h3>");
		    	RequestDispatcher rd=request.getRequestDispatcher("/emplogin.jsp");
		    	rd.include(request, response);
		    }
		}catch(Exception e) {
			
			response.setContentType("text/html");
	    	out.println("<h3 style='color:red'>"+e.getMessage()+" match</h3>");
	    	RequestDispatcher rd=request.getRequestDispatcher("/emplogin.jsp");
	    	rd.include(request, response);
			e.printStackTrace();
		}
	}

	

}
