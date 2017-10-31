<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>


<html:html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><bean:message key="success" /></title>
		<link rel='stylesheet' href='sty.css'/>
		<link href="signin.css" rel="stylesheet">
		<link href="./assets/css/bootstrap.min.css" rel="stylesheet">
		<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	  	<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
	
	</head>
	
	<body>
	
		 <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
	                     <html:link action="/Home" styleClass="navbar-brand"> 
				 			 <span class="glyphicon glyphicon-home "> </span>
				  		</html:link>
                    </div>
                </div>
        </nav>
		
		<div class="container">
		
			<BR><BR><BR><BR><BR><BR>
			
			<center> <img  src="images/reussi.jpg" width="300px" height="250px"></center>
			
		</div>
	    		
	</body>
	
</html:html>