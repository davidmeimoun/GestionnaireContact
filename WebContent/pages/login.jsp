<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>

<html:html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><bean:message key="main.page.title" /></title>
	<link rel='stylesheet' href='sty.css'/>
	<link href="signin.css" rel="stylesheet">
	<link href="./assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
  	<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
<html:base />

</head>
	
	<body background="./images/new-york.jpg" bgcolor="white">
	
		<nav class="navbar navbar-inverse navbar-fixed-top">
			
			<div class="container">
			  
				<div class="navbar-header">
					  <a class="navbar-brand" href="login.jsp">Accueil</a>
					  
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span> 
					</button>
				</div>
					
				
				<div class="collapse navbar-collapse" id="myNavbar">
				
				
				 <html:form styleClass="navbar-form navbar-right" action="/LoginConnex">
				 
				 <html:errors />
				 
					<div class="form-group">
						<bean:message key="label.main.userName" />
						<html:text property="userName" size="20" value="David" styleClass="form-control"  />
					</div>
					
					<div class="form-group">
					  <bean:message key="label.main.password"/>
						<html:password property="password" size="20" value="David" styleClass="form-control"  />
					</div>
				
					
					<html:submit styleClass="btn btn-success" property="connexion"><bean:message key="button.submit"/></html:submit>
			
					<html:reset styleClass="btn btn-primary"><bean:message key="button.reset"/></html:reset>
					
				</html:form>
				
				  
				</div>
			</div>
			
		</nav>
		
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
		
	</body>
	
</html:html>