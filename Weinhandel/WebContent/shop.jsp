<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="object.*"%>
<%@page import="basic.Services"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shop | Weinhandel SW & DA</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<%
	Services dbService = new Services();
	Art[] artTable = dbService.getArtTable("");
	request.setAttribute("artTable", artTable);
	Land[] landTable = dbService.getLandTable("");
	request.setAttribute("landTable", landTable);
	Rebsorte[] rebsorteTable = dbService.getRebsorteTable("");
	request.setAttribute("rebsorteTable", rebsorteTable);
	Region[] regionTable = dbService.getRegionTable("");
	request.setAttribute("regionTable", regionTable);
	Typ[] typTable = dbService.getTypTable("");
	request.setAttribute("typTable", typTable);
	Weingut[] weingutTable = dbService.getWeingutTable("");
	request.setAttribute("weingutTable", weingutTable);
%>
</head>
<body>
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
	
	<form method="post" action="ShopServlet">
	
	<!--  Beginn Content Bereich -->
		<div id="content">
			<!--  Mainpage -->
			<div id="HeaderContent">
				<h2>Produkte</h2>
					<p>
						Hier können Sie Ihren gewünschten Wein raussuchen und in den Warenkorb verschieben. Zur weiteren Hilfe
						dienen Ihnen die Filter auf der rechten Seite. Um den Wein in den Warenkorb zu verschieben, muss das Auswahlfeld
						angehakt sein und der Knopf "Hinzufügen" gedrückt werden.
					</p>
			</div>
			
			<!-- SidebarContent -->
			<div id="sidebarcontents">
				<a href="#">Warenkorb</a>
				<br></br>
				<br></br>
				<br></br>
					<!-- Art Filter -->
					<b>Art:</b>
					<p><select name="art" style="width: 100px">
					<option><%="" %></option><!-- leeren String als Startwert -->
					<c:forEach items="${artTable}" var="art">
					<option>${art.bez}</option>
					</c:forEach>
					</select></p>
					<!-- Land Filter -->
					<b>Land:</b>
					<p><select name="land" style="width: 100px">
					<option><%="" %></option><!-- leeren String als Startwert -->
					<c:forEach items="${landTable}" var="land">
					<option>${land.name}</option>
					</c:forEach>
					</select></p>
					<!-- Region Filter -->
					<b>Region:</b>
					<p><select name="region" style="width: 100px">
					<option><%="" %></option><!-- leeren String als Startwert -->
					<c:forEach items="${regionTable}" var="region">
					<option>${region.name}</option>
					</c:forEach>
					</select></p>
					<!-- Typ Filter -->
					<b>Typ:</b>
					<p><select name="typ" style="width: 100px">				
					<option><%="" %></option><!-- leeren String als Startwert -->
					<c:forEach items="${typTable}" var="typ">
					<option>${typ.bez}</option>
					</c:forEach>
					</select></p>
					<!-- Rebsorte Filter -->
					<b>Rebsorte:</b>
					<p><select name="rebsorte" style="width: 100px">				
					<option><%="" %></option><!-- leeren String als Startwert -->
					<c:forEach items="${rebsorteTable}" var="rebsorte">
					<option>${rebsorte.name}</option>
					</c:forEach>
					</select></p>
					<!-- Weingut -->
					<b>Weingut:</b>
					<p><select name="weingut" style="width: 100px">				
					<option><%="" %></option><!-- leeren String als Startwert -->
					<c:forEach items="${weingutTable}" var="weingut">
					<option>${weingut.name}</option>
					</c:forEach>
					</select></p>
					<c:forEach items="${showFilter}" var="showFilter">
						<p><c:out value="${showFilter}"/></p>
					</c:forEach>
					<br/>
				<!-- Filter Knopf -->
				<input type="submit" title="btnFilter" value="Filter ausführen"/>
				
				<c:out value="${chkbox}"/>			
			</div>
			
		</div>
		<div id="ShopMainpage">
			<p>
			<c:set value="${cFilter}" var="filledCFilter"/>
			<%
				String cFilter = (String)pageContext.getAttribute("filledCFilter");
				if(cFilter == null){
					cFilter = "";
				}
				Wein[] weinTable = dbService.getWeinTable(cFilter);
				request.setAttribute("weinTable", weinTable);
			%>
			
			<!-- Tabelle Wein -->				
					<table border="1">
						<tr>
							<th>Name</th>
							<th>Jahrgang</th>
							<th>Preis</th>
							<th>Weingut</th>
							<th>Typ</th>
							<th>Art</th>
							<th>j/n</th>
						</tr>
					<c:forEach items="${weinTable}" var="wein">
						<tr>
							<td title="${wein.name}">${wein.name}</td>
							<td title="${wein.jahrgang}">${wein.jahrgang}</td>
							<td title="${wein.preis}">${wein.preis}</td>
							<td title="${wein.weingutBez}">${wein.weingutBez}</td>
							<td title="${wein.weintypBez}">${wein.weintypBez}</td>
							<td title="${wein.weinartBez}">${wein.weinartBez}</td>
							<td title="Anklicken um zum Warenkorb hinzuzufügen"><input type="checkbox" name="cBoxWarenkorb"/> </td>
						</tr>
					</c:forEach>
				</table>
				
			</p>
		</div>
	<!-- Ende ContentBereich -->
	</form>	
	
	
</div>

<div id="footer">
<p>Copyright &copy; 2014 | SW  DA Weinhandel</p>
</div>
</body>
</html>
