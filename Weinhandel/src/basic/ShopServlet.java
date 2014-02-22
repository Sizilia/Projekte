package basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
     
	private String artFilter, landFilter, regionFilter, typFilter, rebsorteFilter, weingutFilter, cFilter = "";
	
	private Services dbService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        dbService = new Services();    	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		artFilter = request.getParameter("art");
		landFilter = request.getParameter("land");
		regionFilter = request.getParameter("region");
		typFilter = request.getParameter("typ");
		rebsorteFilter = request.getParameter("rebsorte");
		weingutFilter = request.getParameter("weingut");
		
		if(!artFilter.equals(""))
			cFilter = dbService.addFilter(cFilter, "ART.BEZ", artFilter);
		landFilter = request.getParameter("land");
		if(!landFilter.equals(""))
			cFilter = dbService.addFilter(cFilter, "LAND.NAME", landFilter);
		regionFilter = request.getParameter("region");
		if(!regionFilter.equals(""))
			cFilter = dbService.addFilter(cFilter, "REGION.NAME", regionFilter);
		typFilter = request.getParameter("typ");
		if(!typFilter.equals(""))
			cFilter = dbService.addFilter(cFilter, "TYP.BEZ", typFilter);
		rebsorteFilter = request.getParameter("rebsorte");
		if(!rebsorteFilter.equals(""))
			cFilter = dbService.addFilter(cFilter, "REBSORTE.NAME", rebsorteFilter);
		weingutFilter = request.getParameter("weingut");
		if(!weingutFilter.equals(""))
			cFilter = dbService.addFilter(cFilter, "WEINGUT.NAME", weingutFilter);
		
		// Filter auf der JSP - Page ausgeben
		String[] showFilter = {"Art:    " + artFilter, "Land:    " + landFilter, "Region:    " + regionFilter,
							   "Typ:    " + typFilter, "Rebsorte:    " + rebsorteFilter, "Weingut:    " + weingutFilter};
		request.setAttribute("showFilter", showFilter);
		
		// Button Warenkorb hinzufügen
		
		request.setAttribute("cFilter", cFilter);
		request.getRequestDispatcher("/shop.jsp").forward(request, response);		
	}

	
}
