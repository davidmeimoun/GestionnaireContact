<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <link rel='stylesheet' href='css/sty.css' />
        <link rel='stylesheet' href='css/signin.css'>
        <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
        
    <html:base />
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


	<div class="jumbotron">

		<div class="container">

			<BR>
			<BR>
			<h4>
				<bean:message key="label.complete" />
			</h4>
			<BR>
			<BR>
			<BR>

			<html:form styleClass="form-signin" action="/AddGroupContact2"> 


			  	<bean:message key="label.groupContact.groupName" />
				<br>
				<span style="color: red"><html:errors
						property="group name chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="group name" /></span>
				<br>
				<html:text property="groupName" size="20" maxlength="20"
					styleClass="form-control" />
		</div>
			</div>
	<center>
		<html:submit styleClass="btn btn-lg btn-primary">
			<bean:message key="label.button.create" />
		</html:submit>
	</center>
	<BR>
	<BR>

	</html:form>
</html:html>
