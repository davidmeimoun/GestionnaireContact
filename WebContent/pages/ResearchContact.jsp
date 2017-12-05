<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page import="domain.*"%>
<%@page import="java.util.List"%>
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
		<script src="./assets/js/jquery.min.js"></script>
		<link rel="stylesheet" href="./assets/css/jquery-ui.css">
		<script src="./assets/js/jquery-ui.min.js"></script>
		<script>
			$(function() {
				 var nameContact = [];
				 
				 var idContact = [];
				<%List<Contact> contact = (List<Contact>) request.getServletContext().getAttribute("ListcontactResearch");%>
                <% for (int i=0;i<contact.size();i++) {%>
                 nameContact[<%= i %>] = "<%= contact.get(i).getFirstName() + " " + contact.get(i).getLastName() %>";
			    <% } %>
                 $("#month").autocomplete({
					source: nameContact
				});
			});
			function mafonction(){
				 var nameContact = [];
				 var idContact = [];
				var name = document.getElementById('month').value;
				if(name == ""){
				alert("Please enter correct value.");	
				}
				else{
					var id;
					<% for (int i=0;
					 i<contact.size();
					 i++) {
						 %>if(name ==  "<%= contact.get(i).getFirstName()  + " " + contact.get(i).getLastName()  %>"){
							 id = "<%= contact.get(i).getId_contact() %>";
						}
						<% 
					}
					 %>
					 if(id == "undefined"){
						 alert("Please choose a existant contact");
					 }
					 else{
						 document.location.href="./updateContact.do?id="+id;
					 }
				}
				
			}
		</script>
		<html:base />
	</head>
	<body onKeyPress="if (event.keyCode == 13) mafonction()" >
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<html:link action="/Home" styleClass="navbar-brand">
					<span class="glyphicon glyphicon-home "></span>
					</html:link>
				</div>
			</div>
		</nav>
		<div class="container">
			<br>
		</div>
		<div  class="row">
			<div class="col-md-6">
				<a class="btn btn-info" href="../AjoutContact.do"><span class="glyphicon glyphicon-eye-open "></span>Add</a>
			</div>
			<div class="col-md-6" >
				<input id="month">
				<button type="button" onclick="mafonction()">Rechercher </button>
			</div>
		</div>
		<div class="row col-md-6 col-md-offset-2 custyle">
			<table class="table table-striped custab table-hover">
				<thead>
					<tr>
						<th>
							<bean:message key="label.contact.id"/>
						</th>
						<th>
							<bean:message key="label.contact.firstName"/>
						</th>
						<th>
							<bean:message key="label.contact.lastName"/>
						</th>
						<th>
							<bean:message key="label.contact.email"/>
						</th>
						<th>
							Entreprise
						</th>
						<th colspan=3 align=center>
							Action
						</th>
					</tr>
				</thead>
				<logic:notEmpty name="ListcontactResearch">
					<logic:iterate id="listContactsId" name ="ListcontactResearch">
						<tr>
							<TD>
								<bean:write name="listContactsId" property="id_contact"/>
							</TD>
							<TD>
								<bean:write name="listContactsId" property="firstName"/>
							</TD>
							<TD>
								<bean:write name="listContactsId" property="lastName"/>
							</TD>
							<TD>
								<bean:write name="listContactsId" property="email"/>
							</TD>
							<TD>
								<bean:write name="listContactsId" property="type"/>
							</TD>
							<TD class="text-center">
								<a class="btn btn-info" href="../ViewAContact.do?id=<bean:write name="listContactsId" property="id_contact"/>">
									<span class="glyphicon glyphicon-eye-open "></span>
									<bean:message key="menu.viewcontact.link"/>
								</a>
							</TD>
							<TD class="text-center">
								<a class="btn btn-warning" href="../updateContact.do?id=<bean:write name="listContactsId" property="id_contact"/>">
									<span class="glyphicon glyphicon-pencil "></span>
									<bean:message key="menu.updatecontact.link"/>
								</a>
							</TD>
							<TD class="text-center">
								<a class="btn btn-danger" href="../DeleteContact.do?id=<bean:write name="listContactsId" property="id_contact"/>">
									<span class="glyphicon glyphicon-trash "></span>
									<bean:message key="menu.deletecontact.link"/>
								</a>
							</TD>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
			</table>
		</div>
	</div>
</body>
</html:html>
