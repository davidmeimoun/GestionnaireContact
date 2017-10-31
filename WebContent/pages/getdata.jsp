<%@page import="service.ContactService"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.*"%>
<%
System.out.println("coco");
ContactService cs = new ContactService();

List<Contact> listContact = cs.listContact();// (List<Contact>)request.getAttribute("ListcontactResearch");
//listContact = cs.researchContact();
//Contact c = new Contact();
//Address add = new Address();
//PhoneNumber pn = new PhoneNumber();
//List<PhoneNumber> listpn = new ArrayList<PhoneNumber>();
//c.setAdd(add);
//listpn.add(pn);
//c.setProfiles(listpn);
//c.setFirstName("david");
//c.setLastName("meimoun");

//listContact.add(c);
//System.out.println(listContact.get(0).getEmail());

	         int cnt = 1;
	         String query = (String)request.getParameter("q");
	         
	         for(int i=0; i<listContact.size();i++){
	        	 String temp = listContact.get(i).getFirstName();
	        	 System.out.println(temp);
	        	 if(temp.startsWith(query.toUpperCase())){
	        		
	        	 out.print(temp+"\n");
	        	 if(cnt>=10)
	        	 break;
	        	 cnt++;
	        	 }
	         }
	    	

	    	
	    	

	     
%>