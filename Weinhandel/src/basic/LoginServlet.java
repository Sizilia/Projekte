package basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Services dbServices; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        dbServices = new Services();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String errorMessage = "";
		
		try {
			if(dbServices.checkPW(userName, passWord)){
				response.sendRedirect("dbVerwaltung.jsp");
			}else{
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Passwort ist nicht korrekt!");
			}
		} catch (Exception e) {
			errorMessage += e.getMessage();
			request.setAttribute("ErrorMessage", errorMessage);
			request.getRequestDispatcher("/Verwaltung.jsp").forward(request, response);
		}
	}

}
