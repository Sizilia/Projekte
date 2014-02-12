<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datenbankverwaltung | Weinhandel SW & DA</title>
<link href="style.css" rel="stylesheet" type="text/css" />
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
					Wählen Sie bitte aus, welche Operation Sie durchführen möchten!
				</p>
				<br/>				
				<h3><a href="dbVerwaltungInsert.jsp">Wein zur Datenbank hinzufügen</a></h3>
				<br/>
				<h3><a href="dbVerwaltungUpdate.jsp">Weindaten aus der Datenbank bearbeiten</a></h3>
				<br/>
				<h3><a href="dbVerwaltungDelete.jsp">Weindaten aus der Datenbank rauslöschen</a></h3>            	
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