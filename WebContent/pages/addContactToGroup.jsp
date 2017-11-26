<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<link rel='stylesheet' href='css/sty.css' />
<link rel='stylesheet' href='css/signin.css'>
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
	            
	            <div class="collapse navbar-collapse" id="myNavbar">
		            <ul class="nav navbar-nav navbar-right">
			       		 <html:link action="/ManageGroupContact" styleClass="navbar-brand">
			               		<bean:message key="return.research.link" />
			             </html:link>
		       		 </ul>
	       		</div>
	       	</div>
	    </nav>
	    
<div  class="row">

			<div class="col-md-6">

	<!--  Partie tous les contacts qui ne sont pas dans le group -->
	<div class="container">
		<div class="row col-md-6 col-md-offset-2 custyle">
	    			<h4>
				<bean:message key="label.contact.outsideGroup" />
			</h4>

			<table class="table table-striped custab table-hover">
				<thead>
					<tr>
						<th><bean:message key="label.contact.id" /></th>
						<th><bean:message key="label.contact.lastName" /></th>
						<th><bean:message key="label.contact.firstName" /></th>
						<th><bean:message key="label.contact.email" /></th>
						<th colspan=3 align=center>Action</th>

					</tr>
				</thead>

				<logic:notEmpty name="listContactOutsideGroup">
					<logic:iterate id="contact" name="listContactOutsideGroup">

						<tr>
							<TD><bean:write name="contact" property="id_contact" /></TD>
							<TD><bean:write name="contact" property="lastName" /></TD>
							<TD><bean:write name="contact" property="firstName" /></TD>
							<TD><bean:write name="contact" property="email" /></TD>
							  <!--
                                        <TD class="text-center">
                                        <a class="btn btn-info" href="../ViewAGroupContact.do?id=<bean:write name="contact" property="id_contact"/>"><span class="glyphicon glyphicon-eye-open "></span><bean:message key="menu.viewGroupcontact.link"/></a>
                                        </TD>
                                         -->
                                        <TD class="text-center">
                                        <a class="btn btn-warning" href="../AddContactToGroup.do?idGroup=<%out.println(request.getParameter("idGroup")); %>&idContact=<bean:write name="contact" property="id_contact"/>"><span class="glyphicon glyphicon-pencil "></span><bean:message key="main.addcontact.link"/></a>
                                        </TD>
                                       <!--
                                        <TD class="text-center"> 
                                         <a class="btn btn-danger" href="../DeleteGroupContact.do?id=<bean:write name="contact" property="id_contact"/>"><span class="glyphicon glyphicon-trash "></span><bean:message key="menu.deleteGroupcontact.link"/></a>
                                          -->
						</tr>

					</logic:iterate>
				</logic:notEmpty>

			</table>

		</div>
	</div>
	</div>
	<div  class="col-md-6">

	<!--  Partie Contact déjà dans le group -->
	
	<div  class="fixed-div">

		<div class="row col-md-6 col-md-offset-2 custyle">
			    			<h4>
				<bean:message key="label.contact.inGroup" />
			</h4>

			<table  class="table table-striped custab table-hover">
				<thead>
					<tr>
						<th><bean:message key="label.contact.id" /></th>
						<th><bean:message key="label.contact.lastName" /></th>
						<th><bean:message key="label.contact.firstName" /></th>
						<th><bean:message key="label.contact.email" /></th>
						<th colspan=3 align=center>Action</th>

					</tr>
				</thead>

				<logic:notEmpty name="ListContactInGroup">
					<logic:iterate id="contact" name="ListContactInGroup">

						<tr>
							<TD><bean:write name="contact" property="id_contact" /></TD>
							<TD><bean:write name="contact" property="lastName" /></TD>
							<TD><bean:write name="contact" property="firstName" /></TD>
							<TD><bean:write name="contact" property="email" /></TD>
							  <!--
                                        <TD class="text-center">
                                        <a class="btn btn-info" href="../ViewAGroupContact.do?id=<bean:write name="contact" property="id_contact"/>"><span class="glyphicon glyphicon-eye-open "></span><bean:message key="menu.viewGroupcontact.link"/></a>
                                        </TD>
                                       
                                        <TD class="text-center">
                                        <a class="btn btn-warning" href="../AddContactToGroup.do?idGroup=<%out.println(request.getParameter("idGroup")); %>&idContact=<bean:write name="contact" property="id_contact"/>"><span class="glyphicon glyphicon-pencil "></span><bean:message key="menu.addGroupcontact.link"/></a>
                                        </TD>
                                          -->
                                        <TD class="text-center"> 
                                         <a class="btn btn-danger" href="../DeleteContactInGroup.do?idGroup=<%out.println(request.getParameter("idGroup")); %>&idContact=<bean:write name="contact" property="id_contact"/>"><span class="glyphicon glyphicon-trash "></span><bean:message key="menu.deleteGroupcontact.link"/></a>
						</tr>

					</logic:iterate>
				</logic:notEmpty>

			</table>

		</div>
	</div>
	</div>
</div>


</body>

</html:html>