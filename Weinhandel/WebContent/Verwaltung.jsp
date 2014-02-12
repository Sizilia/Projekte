<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN">
<%@page import="basic.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Mitarbeiterlogin | Weinhandel SW & DA</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<%
	Services dbService = new Services();
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
			<h2>Mitarbeiterlogin</h2>
		</div>
			<center>
				<p>
					Hier haben nur Mitarbeiter der SW&DA Weinhandel GmbH zutritt.
				</p>
				<br/>
				<% if(request.getParameter("uname") == null && request.getParameter("pass")== null){ %>
            	<table border="1" width="30%" cellpadding="3">
               		<thead>
                    	<tr>
                        	<th colspan="2">Mitarbeiterlogin</th>
                    	</tr>
                	</thead>
                	<tbody>
                	<form method="get" action="Verwaltung.jsp">
                    	<tr>
                        	<td>User Name</td>
                        	<td><input type="text" name="uname" value="" /></td>
                  	 	</tr>
                    	<tr>
                       	 	<td>Password</td>
                        	<td><input type="password" name="pass" value="" /></td>
                    	</tr>
                    		<td><input type="submit" value="Login" /></td>
                   		</tr>
                   	</form>
                	</tbody>
            	</table>
            	<% 
            		}else{ 
            			String userName, userPassword;
            			userName = request.getParameter("uname");
            			userPassword = request.getParameter("pass");
            			if(dbService.checkPW(userName, userPassword)){
            				response.sendRedirect("dbVerwaltung.jsp");
            			}else{
            				out.println("Username oder Passwort sind falsch!");
            			}
            		}
				%>
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
