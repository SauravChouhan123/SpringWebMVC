<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Page</title>
</head>
<body>
    
    <%@ page import="in.sp.model.User" %>
    <%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.html");
        return;
    }
    %>

    <h2>Welcome, <%= user.getName() %>!</h2>
    <p>Email: <%= user.getEmail() %></p>
    <p>City: <%= user.getCity() %></p>

    
    <a href="logout">Logout</a>

</body>
</html>
