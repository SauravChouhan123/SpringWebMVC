package in.sp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import in.sp.db.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		String city = req.getParameter("city");

		try {
			Connection con = DbConnection.getConnection();
			String insert_sql = "Insert into register(name,email,password,city) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, city);

			int count = ps.executeUpdate();
			if (count > 0) {
				out.println("<h3 style='color:green'> Registration Successful </h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, resp);
			} else {
				out.println("<h3 style='color:red'> User Not Registered due to some error </h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/register.html");
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
