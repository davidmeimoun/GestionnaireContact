<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>

<html:html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><bean:message key="main.page.title" /></title>
<link href='css/sty.css' rel='stylesheet' />
<link href="css/signin.css" rel="stylesheet">
<link href="./assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../../assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

</head>


<body style="text-align: center;">

    
         <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
	                     <html:link action="/Home" styleClass="navbar-brand"> 
				 			 <span class="glyphicon glyphicon-home "> </span>
				  		</html:link>
                    </div>
                </div>
        </nav>
	<div class="jumbotron">

		<div class="container">

			<BR>
			<BR>
			<BR>
			<h1>
				<bean:message key="main.page.menu" />
			</h1>

			<BR>
			<BR>
			<BR>

		</div>
	</div>

	<div class="container">

		<div class="row">

			<div class="col-md-6">
				<h2>
					<bean:message key="contact" />
				</h2>
				<a role=button class="btn btn-info" href="Contact.do"><bean:message
						key="label.click" /></a>
			</div>

			<div class="col-md-6">
				<h2>
					<bean:message key="group.contact" />
				</h2>
				<a role=button class="btn btn-info" href="ManageGroupContact.do"><bean:message
						key="label.click" /></a>
			</div>
		</div>
	</div>

</body>

</html:html>