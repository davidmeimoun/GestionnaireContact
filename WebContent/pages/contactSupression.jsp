<%@page import="domain.Contact"%>
<%@page import="java.util.List"%>
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
		<title><bean:message key="delete.contact" /></title>
		<link rel='stylesheet' href='sty.css'/>
		<link href="signin.css" rel="stylesheet">
		<link href="./assets/css/bootstrap.min.css" rel="stylesheet">
		<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
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
	<%
	List<Contact> contact = (List<Contact>) request.getAttribute("ListcontactResearch");

%>
	<div class="jumbotron">
	
		<div class="container" >
	
			<BR><BR><bean:message key="label.choose.delete.contact" /> <BR><BR><BR>
	
			<html:form styleClass="form-signin" action="/DeleteContact">
		
				<html:errors />
	<center>
				<TABLE BORDER>
					<BR>
					<TR>
						<TH>ID</TH>
						<TD>
							<%
								//out.println(contact.get(0.))());
							%>
						</TD>
					<TR>
						<TH>Name</TH>
						<TD>
							<%
							//	out.println(contact.getFirstName());
							%>
						</TD>
					<TR>
						<TH>Prenom</TH>
						<TD>
							<%
							//	out.println(contact.getLastName());
							%>
						</TD>
					<TR>
						<TH>Email</TH>
						<TD>
							<%
							//	out.println(contact.getEmail());
							%>
						</TD>
					<TR>
						<TH>Phone Number</TH>
						<TD>
							<%
							//	out.println(contact.getProfiles().get(0).getPhoneNumber());
							%>
						</TD>
					<TR>
						<TH>Phone Kind</TH>
						<TD>
							<%
							//	out.println(contact.getProfiles().get(0).getPhoneKind());
							%>
						</TD>
					<TR>
						<TH>Street</TH>
						<TD>
							<%
							//	out.println(contact.getAdd().getStreet());
							%>
						</TD>
					<TR>
						<TH>City</TH>
						<TD>
							<%
						//		out.println(contact.getAdd().getCity());
							%>
						</TD>
					<TR>
						<TH>ZIP</TH>
						<TD>
							<%
							//	out.println(contact.getAdd().getZip());
							%>
						</TD>
					<TR>
						<TH>Country</TH>
						<TD>
							<%
							//	out.println(contact.getAdd().getCountry());
							%>
						</TD>
				</TABLE>
			</center>
			
				<center><html:submit styleClass="btn btn-danger"> <bean:message key="label.button.delete" /> </html:submit></center>
					
			</html:form>
		
		</div>
		
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	    <script src="./assets/js/bootstrap.min.js"></script>
	    
	</div> 
	
	</body>
	
</html:html>
