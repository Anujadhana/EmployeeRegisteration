<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h1>Employee Registeration</h1>
<form action="EmployeeRegisteration" method="get">
<table>
<tr><td>ID</td><td><input type="text" name="id1"/></td></tr> 
<tr><td>First Name</td><td><input type="text" name="firstname1"/></td></tr>
<tr><td>Last Name</td><td><input type="text" name="lastname1"/></td></tr>
<tr><td>UserName</td><td><input type="text" name="username1"/></td></tr>
<tr><td>Password</td><td><input type="password" name="password1"/></td></tr>
<tr><td>Address</td><td><input type="text" name="address1"/></td></tr>
<tr><td>Contact</td><td><input type="text"  name="contact1"/></td></tr>
</table>
<input type="submit" value="Submit"/>
<a href='emplogin.jsp'> Employee Login</a>
<a href='EmpList'>Employee List</a>
</form>

</div>

</body>
</html>