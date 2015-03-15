package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Connect
 */
@WebServlet("/Connect")
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			Class.forName("com.mysql.jdbc.Driver"); // mysql jar icindeki Driver.java class yolu
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.println("Can't load database driver <br/>");
			return; // eger buraya gelirse program kırılacak ve asagidaki kod
					// bloguna girmeyecek
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME", "YOUR_DATABASE_USER_NAME", "YOUR_DATABASE_PASSWORD");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("Can't connect to database <br/>");
			return; // eger buraya gelirse program kırılacak ve asagidaki kod
					// bloguna girmeyecek
		}

		out.println("Connected to database. <br/>");

		try {
			conn.close();
		} catch (SQLException e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
