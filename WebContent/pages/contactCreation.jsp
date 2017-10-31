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
<title><bean:message key="add.contact" /></title>
<link rel='stylesheet' href='sty.css' />
<link href="signin.css" rel="stylesheet">
<link href="./assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../../assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

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

			<html:form styleClass="form-signin" action="/AddContact">
			
			<!-- rajout du num siret de l'entreprise -->
			<bean:message key="label.contact.numSiret" />
				<br>
				<span style="color: red"><html:errors
						property="num siret chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="numSiret" /></span>
				<br>
				<html:text property="numSiret" size="20" maxlength="20" value="0"
					styleClass="form-control" />
				<BR>
				<BR>


				<bean:message key="label.contact.firstName" />
				<br>
				<span style="color: red"><html:errors
						property="first name chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="first name" /></span>
				<br>
				<html:text property="firstName" size="20" maxlength="20"
					styleClass="form-control" />
				<BR>
				<BR>

				<bean:message key="label.contact.lastName" />
				<br>
				<span style="color: red"><html:errors
						property="last name chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="last name" /></span>
				<br>
				<html:text property="lastName" size="20" maxlength="20"
					styleClass="form-control" />
				<BR>
				<BR>

				<bean:message key="label.contact.email" />
				<br>
				<span style="color: red"><html:errors property="emailFormat" /></span>
				<br>
				<span style="color: red"><html:errors property="email" /></span>
				<br>
				<html:text property="email" size="30" maxlength="30"
					styleClass="form-control" />
				<BR>
				<BR>

				<bean:message key="label.contact.phoneNumber" />
				<br>
				<span style="color: red"><html:errors property="phone kind" /></span>
				<br>
				<html:text property="phoneNumber" size="20" value="+33 "
					maxlength="20" styleClass="form-control" />
				<BR>
				<BR>

				<bean:message key="label.contact.phoneKind" />
				<br>
				<span style="color: red"><html:errors property="phone kind" /></span>
				<br>
				 <html:select  property="phoneKind"  size="3" styleClass="form-control">
              		<html:option value="Home"><bean:message key="label.contact.phoneKind.Home" /></html:option>
					<html:option value="Work"><bean:message key="label.contact.phoneKind.Work" /></html:option>
				    <html:option value="Mobile"><bean:message key="label.contact.phoneKind.Mobile" /></html:option>
            </html:select>
			<!-- <html:text property="phoneKind" size="20" value="+33 1 "
					maxlength="20" styleClass="form-control" /> -->	
				<BR>
				<BR>

				<bean:message key="label.contact.street" />
				<br>
				<span style="color: red"><html:errors property="street" /></span>
				<br>
				<html:text property="street" size="50" maxlength="50"
					styleClass="form-control" />
				<BR>
				<BR>

				<bean:message key="label.contact.city" />
				<br>
				<span style="color: red"><html:errors property="city chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="city" /></span>
				<br>
				<html:text property="city" size="20" maxlength="20"
					styleClass="form-control" />
				<BR>
				<BR>

				<bean:message key="label.contact.zip" />
				<br>
				<span style="color: red"><html:errors property="zip chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="zip" /></span>
				<br>
				<html:text property="zip" size="20" maxlength="20"
					styleClass="form-control" />
				<BR>
				<BR>

				<bean:message key="label.contact.country" />
				<br>
				<span style="color: red"><html:errors
						property="country chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="country" /></span>
				<br>
				<html:text property="country" size="20" maxlength="20"
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
