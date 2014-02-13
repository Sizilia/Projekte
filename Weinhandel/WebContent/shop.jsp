<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN">
<%@page import="java.io.PrintWriter"%>
<%@page import="object.*"%>
<%@page import="basic.Services"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shop | Weinhandel SW & DA</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	Services dbService = new Services();
	Wein[] weinTable = dbService.getWeinTable("", false);
	Art[] artTable = dbService.getArtTable("", false);
	Land[] landTable = dbService.getLandTable("", false);
	Rebsorte[] rebsorteTable = dbService.getRebsorteTable("", false);
	Region[] regionTable = dbService.getRegionTable("", false);
	Typ[] typTable = dbService.getTypTable("", false);
	Weingut[] weingutTable = dbService.getWeingutTable("", false);
	String cFilter = "";
%>
<div class="style3"></div><div class="style_2"><span class="style3"><a><strong></strong></a></span></div>
<div id="wrap">
	<!-- Start "HeaderBereich" -->
	<div id="topbar">
	  <h1 id="sitename"><a>Weinhandlung SW & DA</a></h1>
	  <div id="menus">
	  	<ul id="topmenu">
			<li>
				<h4><a href="index.jsp">Home</a></h4>
			</li>
			<li>
				<h4><a href="shop.jsp">Shop</a></h4>
			</li>
			<li>
				<h4><a href="Verwaltung.jsp">Mitarbeiterlogin</a></h4>
			</li>
		</ul>
	  </div>
	</div>
	<div id="header">
	<!-- Beinhaltet nur das Headerbild -->
	</div>
	<!-- Ende "Headerbereich" -->

	<!--  Beginn Content Bereich -->
	<div id="content">
		<!--  Mainpage -->
		<div id="HeaderContent">
			<h2>Produkte</h2>
				<p>
					Hier können Sie Ihren gewünschten Wein raussuchen und in den Warenkorb verschieben. Zur weiteren Hilfe
					dienen Ihnen die Filter auf der rechten Seite.
				</p>
		</div>
		<div id="ShopMainpage">
			<p>
				<!-- Tabelle Wein -->				
				<table border="1">
					<tr>
						<th>Name</th>
						<th>Jahrgang</th>
						<th>Preis</th>
						<th>Weingut</th>
						<th>Typ</th>
						<th>Art</th>
					</tr>
					<% for(int i = 0; i < weinTable.length; i++){ %>
					<tr>
						<td><%= weinTable[i].getname() %></td>
						<td><%= weinTable[i].getjahrgang() %></td>
						<td><%= weinTable[i].getpreis() %></td>
						<td><%= weinTable[i].getWeingutBez() %></td>
						<td><%= weinTable[i].getWeintypBez() %></td>
						<td><%= weinTable[i].getWeinartBez() %></td>
					</tr>
					<% } %>
				</table>				
			</p>
		</div>
		<!-- Sidebar -->
		<div id="sidebar">

		</div>
		<!-- SidebarContent -->
		<form method="post" action="shop.jsp">
		<div id="sidebarcontents">
			<a href="#">Warenkorb</a>
			<br></br>
			<br></br>
			<br></br>
			<br></br>
			<br></br>
		<% 
			if(
				request.getParameter("art") == null && 
				request.getParameter("land") == null && 
				request.getParameter("region") == null && 
				request.getParameter("typ") == null && 
				request.getParameter("rebsorte") == null 
				&& request.getParameter("weingut") == null){ 
		%>
				<!-- Art Filter -->
				<b>Art:</b>
				<p><select name="art" style="width: 100px">
				<option><%="" %></option><!-- leeren String als Startwert -->
				<% for(int i = 0; i < artTable.length; i++){ %>
				<option><%=artTable[i].getBez() %></option>
				<% } %>
				</select></p>
				<!-- Land Filter -->
				<b>Land:</b>
				<p><select name="land" style="width: 100px">
				<option><%="" %></option><!-- leeren String als Startwert -->
				<% for(int i = 0; i < landTable.length; i++){ %>
				<option><%=landTable[i].getName() %></option>
				<% } %>
				</select></p>
				<!-- Region Filter -->
				<b>Region:</b>
				<p><select name="region" style="width: 100px">
				<option><%="" %></option><!-- leeren String als Startwert -->
				<% for(int i = 0; i < regionTable.length; i++){ %>
				<option><%=regionTable[i].getName() %></option>
				<% } %>
				</select></p>
				<!-- Typ Filter -->
				<b>Typ:</b>
				<p><select name="typ" style="width: 100px">				
				<option><%="" %></option><!-- leeren String als Startwert -->
				<% for(int i = 0; i < typTable.length; i++){ %>
				<option><%=typTable[i].getBez() %></option>
				<% } %>
				</select></p>
				<!-- Rebsorte Filter -->
				<b>Rebsorte:</b>
				<p><select name="rebsorte" style="width: 100px">				
				<option><%="" %></option><!-- leeren String als Startwert -->
				<% for(int i = 0; i < rebsorteTable.length; i++){ %>
				<option><%= rebsorteTable[i].getName() %></option>
				<% } %>
				</select></p>
				<!-- Weingut -->
				<b>Weingut:</b>
				<p><select name="weingut" style="width: 100px">				
				<option><%="" %></option><!-- leeren String als Startwert -->
				<% for(int i = 0; i < weingutTable.length; i++){ %>
				<option><%= weingutTable[i].getName() %></option>
				<% } %>
				</select></p>
				
			<!-- Filter Knopf -->
			<input type="submit" title="btnFilter" value="Filter ausführen"/>
		<%
			}else{
				String art, land, region, typ, rebsorte, weingut;
				art = request.getParameter("art");
				if(!art.equals(""))
					cFilter = dbService.addFilter(cFilter, "art.bez", art );
				land = request.getParameter("land");
				if(!land.equals(""))
					cFilter = dbService.addFilter(cFilter, "LAND.NAME", land);
				region = request.getParameter("region");
				if(!region.equals(""))
					cFilter = dbService.addFilter(cFilter, "REGION.NAME",region);
				typ = request.getParameter("typ");
				if(!typ.equals(""))
					cFilter = dbService.addFilter(cFilter, "TYP.BEZ", typ);
				rebsorte = request.getParameter("rebsorte");
				if(!rebsorte.equals(""))
					cFilter = dbService.addFilter(cFilter, "REBSORTE.NAME",rebsorte);
				weingut = request.getParameter("weingut");
				if(!weingut.equals(""))
					cFilter = dbService.addFilter(cFilter, "WEINGUT.NAME", weingut);
				// Ausgewählte Werte der Comboboxen ausgeben
				out.print("<b>Art</b>:<p>" + art + "</p>");
				out.print("<b>Land:</b><p>" + land + "</p>");
				out.print("<b>Region:</b><p>" + region + "</p>");
				out.print("<b>Typ:</b><p>" + typ + "</p>");
				out.print("<b>Rebsorte:</b><p>" + rebsorte + "</p>");
				out.print("<b>Weingut:</b><p>" + weingut + "</p>");
				weinTable = dbService.getWeinTable(cFilter, false);
			}
		%>
			
			<br/>
		</div>
		</form>
	</div>
	<!-- Ende ContentBereich -->

</div>

<div id="footer">
<p>Copyright &copy; 2014 | SW  DA Weinhandel</p>
</div>
</body>
</html>
