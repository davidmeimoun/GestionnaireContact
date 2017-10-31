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
            
            <div class="container">
            <br>
                  <a class="btn btn-info" href="../AjoutContact.do"><span class="glyphicon glyphicon-eye-open "></span>Add</a>
				
            </div>
            
          

      
                <div class="row col-md-6 col-md-offset-2 custyle">
            
                    
                            <table class="table table-striped custab table-hover">
                                <thead>
                                    <tr>
                                    
                                        <th><bean:message key="label.contact.id"/></th>
                                        <th><bean:message key="label.contact.firstName"/></th>
                                        <th><bean:message key="label.contact.lastName"/> </th>
                                        <th><bean:message key="label.contact.email"/></th>
                                        <th colspan=3 align=center>Action</th>
                                        
                                    </tr>
                                </thead>
                                    
                                <logic:notEmpty name="ListcontactResearch">
                                <logic:iterate id="listContactsId" name ="ListcontactResearch">
                                
                                    <tr>
                                        <TD><bean:write name="listContactsId" property="id_contact"/> </TD>
                                        <TD><bean:write name="listContactsId" property="firstName"/> </TD>
                                        <TD><bean:write name="listContactsId" property="lastName"/></TD>
                                        <TD><bean:write name="listContactsId" property="email"/></TD>
                                   
                                        <TD class="text-center">
                                        <a class="btn btn-info" href="../ViewAContact.do?id=<bean:write name="listContactsId" property="id_contact"/>"><span class="glyphicon glyphicon-eye-open "></span><bean:message key="menu.viewcontact.link"/></a>
                                        </TD>
                                        
                                        <TD class="text-center">
                                        <a class="btn btn-warning" href="../updateContact.do?id=<bean:write name="listContactsId" property="id_contact"/>"><span class="glyphicon glyphicon-pencil "></span><bean:message key="menu.updatecontact.link"/></a>
                                        </TD>
                                        
                                        <TD class="text-center"> 
                                         <a class="btn btn-danger" href="../DeleteContact.do?id=<bean:write name="listContactsId" property="id_contact"/>"><span class="glyphicon glyphicon-trash "></span><bean:message key="menu.deletecontact.link"/></a>
                                        </TD>
                                    </tr>
                                
                                </logic:iterate>
                                </logic:notEmpty>
                                
                            </table>
                            
                        </div>
                    </div>
            
    
    </body>
    
</html:html>