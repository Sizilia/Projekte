<%@page import="basic.Services"%>
<%@page import="object.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	Art[] artTable = dbService.getArtTable("");
	request.setAttribute("artTable", artTable);
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
			<div id="dbInsertJSP">	
				<table>
					<tr>
						<th>Weinname</th>
						<th>Jahrgang</th>
						<th>Preis</th>
						<th>Beschreibung</th>
					</tr>
					<tr>
						<td><input type="text" name="weinname" value="" /></td>
						<td><input type="text" name="weinjahrgang" value="" /></td>
						<td><input type="text" name="weinpreis" value="" /></td>
						<td><input type="text" name="weinbeschreibung" value="" /></td>
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
						<!-- Weingut Filter -->
						<td>
							<select style="width: 120px">
								<option></option>
								<c:forEach items="${weingutTable}" var="weingut">
									<option>${weingut.name}</option>
								</c:forEach>
							</select>
						</td>
						<!-- Typ Filter -->
						<td>
							<select>				
							<option></option>
							<c:forEach items="${typTable}" var="typ">
								<option>${typ.bez}</option>
							</c:forEach>
							</select>
						</td>
						<!-- Art Filter -->
						<td>
							<select>				
							<option></option>
							<c:forEach items="${artTable}" var="art">
								<option>${art.bez}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<br/>
				<input type="submit" value="Datensatz hinzufügen" />
			</div>
		</form>								          	
	</center>
	</div>
	<!-- Ende ContentBereich -->
</div>

<div id="footer">
<p>Copyright &copy; 2014 | SW  DA Weinhandel</p>
</div>
</body>
</html>
