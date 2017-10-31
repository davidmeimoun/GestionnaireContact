<%@page import="domain.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<%
    Contact contact = (Contact) request.getAttribute("contact");
%>

<html:html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><bean:message key="success" /></title>
		<link rel='stylesheet' href='css/sty.css' />
		<link href="css/signin.css" rel="stylesheet">
		<link href="./assets/css/bootstrap.min.css" rel="stylesheet">
		<link href="../../assets/css/ie10-viewport-bug-workaround.css"rel="stylesheet">
		<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
		<script src="./assets/js/bootstrap.min.js"></script>
	</head>
	
	<body>

	    <nav class="navbar navbar-inverse navbar-fixed-top">
	        <div class="container">
	            <div class="navbar-header">
	            		
	                <html:link action="/Home" styleClass="navbar-brand">
	               		 <span class="glyphicon glyphicon-home "> </span>
	                </html:link>
	                
	            </div>
	            
	            <div class="collapse navbar-collapse" id="myNavbar">
		            <ul class="nav navbar-nav navbar-right">
			       		 <html:link action="/ResearchC" styleClass="navbar-brand">
			               		<bean:message key="return.research.link" />
			             </html:link>
		       		 </ul>
	       		</div>
	       	</div>
	    </nav>
	    
	    
		 <div class="container">
			<BR><BR><BR>
			<h4><bean:message key="label.here" /> <%
		                        out.println(contact.getFirstName());
			out.println(contact.getLastName());
		                    %><bean:message key="label.informations" /></h4>
			<BR><BR><BR>
			<html:errors />
		       
		        <TABLE align='center'>
		            <TR >
		                <TD class = h>ID</TD>
		                <TD class = t>
		                    <%
		                        out.println(contact.getId_contact());
		                    %>
		                </TD>
		            <TR>
		                <TD class = h>First Name</TD>
		                <TD class = t>
		                    <%
		                        out.println(contact.getFirstName());
		                    %>
		                </TD>
		            <TR>
		                <TD class = h>Last Name</TD>
		                <TD class = t>
		                    <%
		                        out.println(contact.getLastName());
		                    %>
		                </TD>
		            <TR>
		                <TD class = h>Email</TD>
		                <TD class = t>
		                    <%
		                        out.println(contact.getEmail());
		                    %>
		                </TD>
		            <TR>
                        <TD class = h>Phone Number</TD>
                        <TD class = t>
                            <%
		                    if(!contact.getProfiles().isEmpty()){
	                            PhoneNumber[] tabPn = contact.getProfiles().toArray(new PhoneNumber[contact.getProfiles().size()]);
	                            out.println(tabPn[0].getPhoneNumber());
			                    }
                            %>
                        </TD>
                    <TR>
                        <TD class = h>Phone Kind</TD>
                        <TD class = t>
                            <%
		                    if(!contact.getProfiles().isEmpty()){
	                            PhoneNumber[] tabPn2 = contact.getProfiles().toArray(new PhoneNumber[contact.getProfiles().size()]);
	                            out.println(tabPn2[0].getPhoneKind());
			                    }
                            %>
                        </TD>
                    <TR>
		                <TD class = h>Street</TD>
		                <TD class = t>
		                    <%
		                        out.println(contact.getAddress().getStreet());
		                    %>
		                </TD>
		            <TR>
		                <TD class = h>City</TD>
		                <TD class = t>
		                    <%
		                        out.println(contact.getAddress().getCity());
		                    %>
		                </TD>
		            <TR>
		                <TD class = h>ZIP</TD>
		                <TD class = t>
		                    <%
		                        out.println(contact.getAddress().getZip());
		                    %>
		                </TD>
		            <TR>
		                <TD class = h>Country</TD>
		                <TD class = t>
		                    <%
		                        out.println(contact.getAddress().getCountry());
		                    %>
		                </TD>
		        </TABLE>
	       
	     </div>
       
	</body>
	
</html:html>