package basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBInsertServlet
 */
@WebServlet("/DBInsertServlet")
public class DBInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Services dbService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBInsertServlet() {
        super();
        dbService = new Services();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name, jahrgang, beschreibung, preis, weingut, typ, art;
		name = request.getParameter("weinname");
		jahrgang = request.getParameter("weinjahrgang");
		beschreibung = request.getParameter("weinbeschreibung");
		preis = request.getParameter("weinpreis");
		weingut = request.getParameter("weingut");
		typ = request.getParameter("typ");
		art = request.getParameter("art");
		
		
	}

}
