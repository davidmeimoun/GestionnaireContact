<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
		<%@page import="domain.*"%>
<%@page import="java.util.List"%>
<%@page import="service.ContactService"%>
<%@page import="domain.util.ApplicationContextUtils" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<script type="text/javascript">

function demarage(){
	var bool = <%= request.getServletContext().getAttribute("versionChanged")%>
	if(bool){
		alert("The contact has been update, please click on OK for refresh the page");
		document.location.href="./updateContact.do?id="+ document.getElementById('idContactV').value;
		<% request.getServletContext().setAttribute("versionChanged",false);%>
	}
	
	var x = document.getElementById('numSiretTextBox');
	var checkbox = document.getElementById('checkBoxEntreprise');
	if(x.value == 0){
		checkbox.checked = false;
		document.getElementById('divNumSiret').style.visibility = 'hidden';
		
	} else{
		checkbox.checked = true;
	}
	var phone2 = document.getElementById('phone2');
	var phone3 = document.getElementById('phone3');
	
	if(document.getElementById('pn2').value == ""){
		phone2.style.visibility = 'hidden';
	}
	
	if(document.getElementById('pn3').value == ""){
		phone3.style.visibility = 'hidden';
	}
	
}


	
	


function addTel2(){
	var x = document.getElementById('phone2')
    if (x.style.visibility === 'hidden') {
        x.style.visibility = 'visible';
    }
}

function noDisplay(){
	document.getElementById('phone2').style.visibility = 'hidden';
	document.getElementById('phone3').style.visibility = 'hidden';
	
}

function noDisplay2(){
	document.getElementById('phone2').style.visibility = 'hidden';
	document.getElementById('pn2').value = "";
	document.getElementById('pk2').value = "";
	
}

function addTel3(){
	var x = document.getElementById('phone3')
    if (x.style.visibility === 'hidden') {
        x.style.visibility = 'visible';
    }
}
function noDisplay3(){
	document.getElementById('phone3').style.visibility = 'hidden';
	document.getElementById('pn3').value = "";
	document.getElementById('pk3').value = "";
	
}

function activeNumSiret(){
	var checkBox = document.getElementById('checkBoxEntreprise');
	var numSiret = document.getElementById('divNumSiret');
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
<link rel='stylesheet' href='assets/css/sty.css' />
<link href="signin.css" rel="assets/stylesheet">
<link href="./assets/css/bootstrap.min.css" rel="stylesheet">

<html:base />
</head>

<body onload="demarage()">

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

	<div class="jumbotron">

		<div class="container">

			<BR> <BR>

			<html:form action="/UpdateContactJ">

				<html:hidden name="updateC" property="id" styleId="idContactV"/>
				<html:hidden name="updateC" property="version" styleId="versionV" />
						<div  class="row">

			<div class="col-md-4">
Entreprise <input id="checkBoxEntreprise" type="checkbox" onclick="activeNumSiret()">
			</div>

			<div id="divNumSiret" class="col-md-4" >
			<!-- rajout du num siret de l'entreprise -->
			<bean:message key="label.contact.numSiret" />
				<br>
				<span style="color: red"><html:errors
						property="num siret chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="numSiret" /></span>
				<br>
				<html:text styleId="numSiretTextBox"  name="updateC" property="numSiret" size="20" maxlength="20" 
					styleClass="form-control" />
			</div>
			
		</div>
				
				
				<bean:message key="label.contact.firstName" />

				<span style="color: red"><html:errors
						property="first name chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="first name" /></span>
				<br>
				<br>

				<html:text name="updateC" property="firstName" size="20"
					maxlength="20" styleClass="form-control" />
					<br>
				<bean:message key="label.contact.lastName" />
				<br>
				<span style="color: red"><html:errors
						property="last name chiffre" /></span>

				<br>
				<span style="color: red"><html:errors property="last name" /></span>
				<br>
				<html:text name="updateC" property="lastName" size="20"
					maxlength="20" styleClass="form-control" />
				<BR>
				<br>

				<bean:message key="label.contact.email" />
				<br>
				<span style="color: red"><html:errors property="emailFormat" /></span>
				<br>
				<span style="color: red"><html:errors property="email" /></span>
				<br>
				<html:text name="updateC" property="email" size="50" maxlength="50"
					styleClass="form-control" />
				<BR>
				<br>



				<bean:message key="label.contact.street" />
				<br>
				<span style="color: red"><html:errors property="street" /></span>
				<br>
				<html:text name="updateC" property="street" size="50" maxlength="50"
					styleClass="form-control" />
				<BR>
				<br>

				<bean:message key="label.contact.city" />
				<br>
				<span style="color: red"><html:errors property="city chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="city" /></span>
				<br>
				<html:text name="updateC" property="city" size="20" maxlength="20"
					styleClass="form-control" />
				<BR>
				<br>

				<bean:message key="label.contact.zip" />
				<br>
				<span style="color: red"><html:errors property="zip chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="zip" /></span>
				<br>
				<html:text name="updateC" property="zip" size="20" maxlength="20"
					styleClass="form-control" />
				<BR>
				<br>

				<bean:message key="label.contact.country" />
				<br>
				<span style="color: red"><html:errors
						property="country chiffre" /></span>
				<br>
				<span style="color: red"><html:errors property="country" /></span>
				<br>
				<html:text name="updateC" property="country" size="20"
					maxlength="20" styleClass="form-control" />
					
					<br>
					<br>
					
					
				
		
		<!-- here -->
				<div id="phone1" class="row">

			<div class="col-md-4">
				<bean:message key="label.contact.phoneNumber" />
				<br>
				<br>
				<span style="color: red"><html:errors property="phone number" /></span>
				<br>
				<html:text styleId="pn1" name="updateC" property="phoneNumber1" size="20" 
					maxlength="20" styleClass="form-control" />
			</div>

			<div class="col-md-4">

				<bean:message key="label.contact.phoneKind" />
				<br>
				<span style="color: red"><html:errors property="phone kind" /></span>
				<br>
				 <html:select styleId="pk1" name="updateC" property="phoneKind1"  size="3" styleClass="form-control">
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
				<html:text styleId="pn2" name="updateC" property="phoneNumber2" size="20" 
					maxlength="20" styleClass="form-control" />
			</div>

			<div class="col-md-4">

				<bean:message key="label.contact.phoneKind" />
				<br>
				<span style="color: red"><html:errors property="phone kind" /></span>
				<br>
				 <html:select styleId="pk2" name="updateC"  property="phoneKind2"  size="3" styleClass="form-control">
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
				<html:text styleId="pn3" name="updateC" property="phoneNumber3" size="20" 
					maxlength="20" styleClass="form-control" />
			</div>

			<div class="col-md-4">

				<bean:message key="label.contact.phoneKind" />
				<br>
				<span style="color: red"><html:errors property="phone kind" /></span>
				<br>
				 <html:select styleId="pk3" name="updateC" property="phoneKind3"  size="3" styleClass="form-control">
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
		<!-- here 2 -->
		
		

		</div>

	</div>
	<center>
		<html:submit styleClass="btn btn-lg btn-warning" onclick="return isVersionCHanged()"  >
			<bean:message key="label.button.update" />
		</html:submit>
	</center>
	<BR>
	<BR>

	</html:form>

</body>

</html:html>