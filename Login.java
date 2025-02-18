package in.sp.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.sp.db.DbConnection;
import in.sp.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginForm")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("pass");

		try {

			Connection con = DbConnection.getConnection();
			String select_sql = "SELECT * FROM register WHERE email=? AND password=?";
			PreparedStatement ps = con.prepareStatement(select_sql);

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				User user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setCity(rs.getString("city"));

				HttpSession session = req.getSession();
				session.setAttribute("user", user);

				RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
					rd.forward(req, resp);
			} else {
				req.setAttribute("errorMessage", "Invalid email or password!");
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
					rd.forward(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
