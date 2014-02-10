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
		<div id="mainpage">
			<p>
				<!-- Tabelle Wein -->
				<table border="1">
					<tr>
						<th>Name</th>
						<th>Jahrgang</th>
						<th>Preis</th>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</p>
		</div>
		<!-- Sidebar -->
		<div id="sidebar">

		</div>
		<!-- SidebarContent -->
		<form method="post" action="bestellungen.jsp">
		<div id="sidebarcontents">
			<a href="#">Warenkorb</a>
			<br></br>
			<br></br>
			<br></br>
			<br></br>
			Filter:
			<br></br>
			<!-- Art Filter -->
			<p><select name="art" style="width: 100px">
			<option>Art</option>
			<option></option>
			</select></p>
			<!-- Land Filter -->
			<p><select name="land" style="width: 100px">
			<option>Land</option>
			<option></option>
			</select></p>
			<!-- Region Filter -->
			<p><select name="region" style="width: 100px">
			<option>Region</option>
			<option></option>
			</select></p>
			<!-- Typ Filter -->
			<p><select name="typ" style="width: 100px">
			<option>Typ</option>
			<option></option>
			</select></p>
			<!-- Rebsorte Filter -->
			<p><select name="rebsorte" style="width: 100px">
			<option>Rebsorten</option>
			<option></option>
			</select></p>
			<!-- Weingut -->
			<p><select name="weingut" style="width: 100px">
			<option>Weingut</option>
			<option></option>
			</select></p>

			<br/>
			<!-- Filter Knopf -->
			<input type="submit" title="btnFilter" value="Filter ausführen"></input>
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
