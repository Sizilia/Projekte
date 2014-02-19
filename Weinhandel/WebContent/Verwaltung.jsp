<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Mitarbeiterlogin | Weinhandel SW & DA</title>
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
			<h2>Mitarbeiterlogin</h2>
		</div>
			<center>
				<p>
					Hier haben nur Mitarbeiter der SW&DA Weinhandel GmbH zutritt.
				</p>
				<br/>
				<form method="post" action="LoginServlet">
	            	<table border="1" width="30%" cellpadding="3">
	               		<thead>
	                    	<tr>
	                        	<th colspan="2">Mitarbeiterlogin</th>
	                    	</tr>
	                	</thead>
	                	<tbody>
	                		<tr>
	                			<td>User Name</td>
	                			<td><input type="text" name="username" value=""/></td>
	                		</tr>
	                		<tr>
	                			<td>Password</td>
	                        	<td><input type="password" name="password" value="" /></td>
	                		</tr>
	                		<tr>
	                			<td><input type="submit" value="Login" /></td>
	                		</tr>
	                	</tbody>
	            	</table>
            	</form>
            	<!--  Gibt Fehler aus falls Username und/oder Passwort falsch ist -->
            	<br/>
	            <b style="color: red;"><c:out value="${ErrorMessage}"/></b>
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
