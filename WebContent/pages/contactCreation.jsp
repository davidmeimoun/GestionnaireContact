<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="domain.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<script type="text/javascript">

function addTel2(){
	var x = document.getElementById('phone2')
    if (x.style.visibility === 'hidden') {
        x.style.visibility = 'visible';
    }
}

function dem(){
	var firstname = document.getElementById('textBoxFirstName').value;
	var lastname = document.getElementById('textBoxLastName').value;
	var email = document.getElementById('textBoxEmail').value;
	var numSiret = document.getElementById('numSiretTextBox').value;
	
	   if(numSiret <= 0 && document.getElementById('checkBoxEntreprise').checked  )
	   {
	   alert('Une entreprise ne peux pas avoir un num Siret negatif ou �gal � zero');
	   return false;
	   }
	   
	//verification contact n'existe pas
	<%List<Contact> contact = (List<Contact>) request.getServletContext().getAttribute("ListcontactResearch");%>
   <% for (int i=0;i<contact.size();i++) {%>
   var listFn = '<%= contact.get(i).getFirstName() %>';
   var listLn = '<%= contact.get(i).getLastName() %>';
   var listEmail = '<%= contact.get(i).getEmail() %>';
  
   if((listFn == firstname & listLn == lastname) || listEmail == email )
   {
   alert('Ce contact existe d�j�');
   return false;
   }
   <% } %>
   
   //verification numSiret n'existe pas
   <%List<Entreprise> listEntreprise = new ArrayList<Entreprise>();
   for (int i=0;i<contact.size();i++) { %>
   <%
   			if(contact.get(i).getType().equals("Entreprise")){
	  			 listEntreprise.add((Entreprise) contact.get(i));
	  		  }}
   %>
   var numSiretValue 	= document.getElementById('numSiretTextBox').value;
   <% for (int p=0;p<listEntreprise.size();p++) { %>
  
   if((numSiretValue == <%= listEntreprise.get(p).getNumSiret()%>))
   {
   alert('Ce num Siret est d�ja utiliser');
   return false;
   }
   <% } %>
   

   
}
function noDisplay(){
	document.getElementById('phone2').style.visibility = 'hidden';
	document.getElementById('phone3').style.visibility = 'hidden';
	
}

function noDisplay2(){
	document.getElementById('phone2').style.visibility = 'hidden';
	
}

function addTel3(){
	var x = document.getElementById('phone3')
    if (x.style.visibility === 'hidden') {
        x.style.visibility = 'visible';
    }
}
function noDisplay3(){
	document.getElementById('phone3').style.visibility = 'hidden';
	
}

function activeNumSiret(){
	var checkBox = document.getElementById('checkBoxEntreprise');
	var numSiret = document.getElementById('numSiret');
    if (checkBox.checked) {
    	numSiret.style.visibility = 'visible';
    	
    } else{
    	numSiret.style.visibility = 'hidden';
    	document.getElementById('numSiretTextBox').value = 0;
    }
	
	
}
</script>
  
<html:html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><bean:message key="add.contact" /></title>
<link rel='stylesheet' href='assets/css/sty.css' />
<link href="signin.css" rel="assets/stylesheet">
<link href="./assets/css/bootstrap.min.css" rel="stylesheet">

<html:base />

</head>

<body onload="noDisplay()">

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
			
			
					<div  class="row">

			<div class="col-md-4">
Entreprise <input id="checkBoxEntreprise" type="checkbox" onclick="activeNumSiret()">
			</div>

			<div id="numSiret" class="col-md-4" style="visibility: hidden;">
			<!-- rajout du num siret de l'entreprise -->
			<bean:message key="label.contact.numSiret" />
				<br>
				<span style="color: red"><html:errors
						property="num siret chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="numSiret" /></span>
				<br>
				<html:text styleId="numSiretTextBox" property="numSiret" size="20" maxlength="10" value="0"
					styleClass="form-control" />
			</div>
			
		</div>
		
			
			
				<BR>
				<BR>


				<bean:message key="label.contact.firstName" />
				<br>
				<span style="color: red"><html:errors
						property="first name chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="first name" /></span>
				<br>
				<html:text styleId="textBoxFirstName" property="firstName" size="20" maxlength="20"
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
				<html:text styleId="textBoxLastName" property="lastName" size="20" maxlength="20"
					styleClass="form-control" />
				<BR>
				<BR>

				<bean:message key="label.contact.email" />
				<br>
				<span style="color: red"><html:errors property="emailFormat" /></span>
				<br>
				<span style="color: red"><html:errors property="email" /></span>
				<br>
				<html:text styleId="textBoxEmail" property="email" size="30" maxlength="30"
					styleClass="form-control" />
				<BR>
				<BR>

		
		
		
				
				<BR>
				<BR>
				<div id="TextBoxContainer">
				</div>
				<div id="TextBoxPhoneKind">
				</div>
								<div id="Other">
				</div> 
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
					
					<br>
					<br>
							
		<div id="phone1" class="row">

			<div class="col-md-4">
				<bean:message key="label.contact.phoneNumber" />
				<br>
				<br>
				<span style="color: red"><html:errors property="phone number" /></span>
				<br>
				<html:text property="phoneNumber1" size="20" 
					maxlength="20" styleClass="form-control" />
			</div>

			<div class="col-md-4">

				<bean:message key="label.contact.phoneKind" />
				<br>
				<span style="color: red"><html:errors property="phone kind" /></span>
				<br>
				 <html:select  property="phoneKind1"  size="3" styleClass="form-control">
              		<html:option value="Home"><bean:message key="label.contact.phoneKind.Home" /></html:option>
					<html:option value="Work"><bean:message key="label.contact.phoneKind.Work" /></html:option>
				    <html:option value="Mobile"><bean:message key="label.contact.phoneKind.Mobile" /></html:option>
            </html:select>
			</div>
			
			<div class="col-md-4">
			<br>
			<br>	
			<br>
			<input type="button" onclick="addTel2()" value="+" />
			</div>
		</div>
		
		<br>
		
		
		<div id="phone2" class="row">

			<div class="col-md-4">
				<bean:message key="label.contact.phoneNumber" />
				<br>
				<br>
				<span style="color: red"><html:errors property="phone number" /></span>
				<br>
				<html:text property="phoneNumber2" size="20" 
					maxlength="20" styleClass="form-control" />
			</div>

			<div class="col-md-4">

				<bean:message key="label.contact.phoneKind" />
				<br>
				<span style="color: red"><html:errors property="phone kind" /></span>
				<br>
				 <html:select  property="phoneKind2"  size="3" styleClass="form-control">
              		<html:option value="Home"><bean:message key="label.contact.phoneKind.Home" /></html:option>
					<html:option value="Work"><bean:message key="label.contact.phoneKind.Work" /></html:option>
				    <html:option value="Mobile"><bean:message key="label.contact.phoneKind.Mobile" /></html:option>
            </html:select>
			</div>
			
			<div class="col-md-4">
						<br>
			<br>	
			
		<input type="button" onclick="addTel3()" value="+" />
		<input type="button" onclick="noDisplay2()" value="-" />
			</div>
		</div>
		
		<br>
		
		
		<div id="phone3" class="row">

			<div class="col-md-4">
				<bean:message key="label.contact.phoneNumber" />
				<br>
				<br>
				<span style="color: red"><html:errors property="phone number" /></span>
				<br>
				<html:text property="phoneNumber3" size="20" 
					maxlength="20" styleClass="form-control" />
			</div>

			<div class="col-md-4">

				<bean:message key="label.contact.phoneKind" />
				<br>
				<span style="color: red"><html:errors property="phone kind" /></span>
				<br>
				 <html:select  property="phoneKind3"  size="3" styleClass="form-control">
              		<html:option value="Home"><bean:message key="label.contact.phoneKind.Home" /></html:option>
					<html:option value="Work"><bean:message key="label.contact.phoneKind.Work" /></html:option>
				    <html:option value="Mobile"><bean:message key="label.contact.phoneKind.Mobile" /></html:option>
            </html:select>
			</div>
			
			<div class="col-md-4">
						<br>
			<br>	
			<br>
		<input type="button" onclick="noDisplay3()" value="-" />
			</div>
		</div>
		</div>
		
		

	</div>
	<br>
	<center>
		<html:submit styleClass="btn btn-lg btn-primary" onclick="return dem()">
			<bean:message key="label.button.create" />
		</html:submit>
	</center>
	<BR>
	<BR>

	</html:form>
</html:html>
