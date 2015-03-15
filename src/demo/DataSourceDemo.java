package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DataSourceDemo
 */

/*
 * Connecting to Database Using JNDI
 * (We use JNDI Tomcat 7 JNDI DataSource in the 'deployment descriptor(/web.xml)' and the 'context.xml(TomcatPath/context.xml)' files. )
 * 
 * 
 * */

@WebServlet("/DataSourceDemo")
public class DataSourceDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataSourceDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		try {
			InitialContext initContext = new InitialContext();

			Context env = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/webshop"); 
			
			// Database Path which the name of 'webshop'
			// TR- Adı 'webshop' olan mysql veritabanımızın yolunu DataSource tipindeki ds nesnemize atadık
			
		} catch (NamingException e) {

			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");

		Connection conn = null;

		try {
			conn = ds.getConnection();  // Connected to the database using ds object.
			
										// TR - Veritabanına, daha önce veritabanı url sini atadığımız javax.sql.DataSource
										// tipindeki 'ds' nesnesini kullanarak bağlandık.
			
		} catch (SQLException e) {
			
			e.printStackTrace();   // or redirecting an error page.(for instance: error.jsp, error.html etc.)
									
								   // TR - burada veritabanı bağlantısı sırasında oluşan bir hatayı ekrana basmak yerine
								   // kullanıcıyı bir hata mesajı göreceği bir web sayfasına da yönlendirebilirdik(örn:error.jsp)
			return; 
			
			/*
			 * NOT: try-catch blokları kodun bir kısmında oluşan hataların yakalanıp, programın tamamının kırılmadan çalışması 
			 * için kullanılır, ama burada return kullanarak hata yakalandığı vakit programın kırılmasını sağladık. Çünkü
			 * veritabanına bağlanamadan diğer işlemleri yerine getiremeyiz.
			 * */
			
		}

		// use the connection

		PrintWriter out = response.getWriter();
		out.println("Connected to database. <br/>");

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
