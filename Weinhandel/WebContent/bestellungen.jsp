<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Bestellung | Weinhandel SW & DA</title>

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
				<h4><a href="ueberuns.jsp">Über uns</a></h4>
			</li>
			<li>
				<h4><a href="bestellungen.jsp">Bestellungen</a></h4>
			</li>
			<li>
				<h4><a href="mitarbeiterlogin.jsp">Mitarbeiterlogin</a></h4>
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
		<div id="mainpage">
			<h2>Produkte</h2>
			<p>
				Hier können Sie Ihren gewünschten Wein raussuchen und in den Warenkorb verschieben. Zur weiteren Hilfe
				dienen Ihnen die Filter auf der rechten Seite.
			</p>
		</div>
		<!-- Sidebar -->
		<div id="sidebar">
		
		</div>
		<!-- SidebarContent -->
		<div id="sidebarcontents"> 
			<a href="#">Warenkorb</a>
			<br></br>
			<br></br>
			<br></br>
			<br></br>
			Filter:
			<br></br>
			<%-- Art Filter --%>
			<p><select name="art"><option>Art</option></select></p>
			<!-- Land Filter -->
			<p><select name="land"><option>Land</option></select></p>
			<!-- Region Filter -->
			<p><select name="region"><option>Region</option></select></p>
			<!-- Typ Filter -->
			<p><select name="typ"><option>Typ</option></select></p>
			<!-- Rebsorte Filter -->
			<p><select name="rebsorte"><option>Rebsorte</option></select></p>
			<!-- Wein Filter -->
			<p><select name="wein"><option>Wein</option></select></p>
			<!-- Weingut -->
			<p><select name="weingut"><option>Weingut</option></select></p>
			<!-- Wein Rebsorte -->
			<p><select name="weinrebsorte"><option>Wein_Rebsorte</option></select></p>
		</div>
	
	</div>
	<!-- Ende ContentBereich -->	

</div>

<div id="footer">
<p>Copyright &copy; 2014 | SW  DA Weinhandel</p>
</div>
</body>
</html>
