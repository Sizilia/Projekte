<%@page import="basic.Services"%>
<%@page import="object.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datenbankverwaltung | Weinhandel SW & DA</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<%
	Services dbService = new Services();
	Art[] artTable = dbService.getArtTable("", false);
	Land[] landTable = dbService.getLandTable("", false);
	Rebsorte[] rebsorteTable = dbService.getRebsorteTable("", false);
	Region[] regionTable = dbService.getRegionTable("", false);
	Typ[] typTable = dbService.getTypTable("", false);
	Weingut[] weingutTable = dbService.getWeingutTable("", false);
%>
</head>
<body>
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
			<h2>Datenbankverwaltung</h2>
		</div>
			<center>
				<p>
					Hier können Weindaten der Datenbank hinzugefügt werden!
				</p>
				<br/>
				<!-- Insert Values to Database -->
				<form method="post" action="dbVerwaltungInsert.jsp">
				<%
					if(request.getParameter("weinname") == null &&
					   request.getParameter("weinjahrgang") == null &&
					   request.getParameter("weinpreis") == null &&
					   request.getParameter("weingut") == null &&
					   request.getParameter("weintyp") == null &&
					   request.getParameter("weinart") == null){
				%>				
				<table>
					<tr>
						<th>Weinname</th>
						<th>Jahrgang</th>
						<th>Preis</th>
					</tr>
					<tr>
						<td><input type="text" name="weinname" value="" /></td>
						<td><input type="text" name="weinjahrgang" value="" /></td>
						<td><input type="text" name="weinpreis" value="" /></td>
					</tr>
				</table>
				<br/>
				<table>
					<tr>
						<th>Weingut</th>
						<th>Typ</th>
						<th>Art</th>
					</tr>
					<tr>
						<td>
							<select name="weingut" style="width: 100px">
							<option></option>
							<% for(int i = 0; i < weingutTable.length; i++){ %>
							<option><%=weingutTable[i].getName() %></option>
							<% } %>
							</select>
						</td>
						<td>
							<select name="weintyp" style="width: 100px">				
							<option></option>
							<% for(int i = 0; i < typTable.length; i++){ %>
							<option><%=typTable[i].getBez() %></option>
							<% } %>
							</select>
						</td>
						<td>
							<select name="weinart" style="width: 100px">				
							<option></option>
							<% for(int i = 0; i < artTable.length; i++){ %>
							<option><%=artTable[i].getBez() %></option>
							<% } %>
							</select>
						</td>
					</tr>
				</table>
				<br/>
				<input type="submit" name="btn_insertDBStatement" value="Datensatz hinzufügen" />
				<% 
					}else{
						String weinname, weinjahrgang, weinpreis, weingut, weintyp, weinart;
						weinname = request.getParameter("weinname");
						weinjahrgang = request.getParameter("weinjahrgang");
						weinpreis = request.getParameter("weinpreis");
						weingut = request.getParameter("weingut");
						weintyp = request.getParameter("weintyp");
						weinart = request.getParameter("weinart");
					}
				%>
				</form>								          	
            </center>            
		<!-- Sidebar -->
		<div id="sidebar">

		</div>
		<!-- SidebarContent -->


	</div>
	<!-- Ende ContentBereich -->

</div>

<div id="footer">
<p>Copyright &copy; 2014 | SW  DA Weinhandel</p>
</div>
</body>
</html>