<!--
/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/
-->
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.kishore.banking.*;" %>
<%@ page import="javax.servlet.http.HttpSession;" %>

<%
    String Username = new String("");
    
     HttpSession ses  =   request.getSession(false);
     Username = (String) ses.getAttribute("uname");
     System.out.println(Username);
  
    if(Username!=null){ 
    
%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<TITLE>Sign Up</TITLE>
<link rel='stylesheet' type='text/css' href='/Project4/sample.css'>
</HEAD>
<BODY BGCOLOR=" #b3b3b3">
<p align='RIGHT'><BUTTON><a href = '/OnlineLogoutServlet' STYLE='TEXT-DECORATION:NONE;COLOR:#FF6200;FONT-SIZE:20px;'>Logout</a></BUTTON></p>
<div id="navbar">
    <div id="holder">
		<ul>
			<li><a href="AccountOverview.jsp?UserID=<%=Username%>">AccountOverview</a></li>
			<li><a href="OpenAccount.jsp?UserID=<%=Username%>">OpenAccount</a></li>
			<li><a href="InquireTransactions.jsp?UserID=<%=Username%>">InquireTransactions</a></li>
			<li><a href="Transfer.jsp?UserID=<%=Username%>">Transfer</a></li>
			<li><a href="BillPays.jsp?UserID=<%=Username%>" id="onlink">BillPays</a></li>
		</ul>
    </div> <!--end of holder-->
 </div> <!--end of navbar-->
 &nbsp;&nbsp;
 <p ALIGN="CENTER">Please Select either "Payee" or "Payment"</p>
 <P ALIGN="CENTER">
 <A HREF="/project4/Payee.jsp"><BUTTON>Payee</BUTTON></A>
 <A HREF="/project4/Payment.jsp"><BUTTON>Payment</BUTTON></A>
</BODY>
</HTML>
<% }  else {%>
            
            <H3 ALIGN='CENTER'>  To Access Your Account Please <a href='/project4/login.html'>CLICK</A> On It!!!</H3>
     <% } %>