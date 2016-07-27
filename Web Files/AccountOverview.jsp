<!--
/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/
-->
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.kishore.banking.*;" %>
<%@ page import="javax.servlet.http.HttpSession;" %>

<%
    String Username = new String("");
    String CheckingAccNum = "", CheckingBal = "", SavingsAccNum = "", SavingsBal = "";
    
    HttpSession ses  =   request.getSession(false);
    Username = (String) ses.getAttribute("uname");
    System.out.println(Username);
 
   if(Username!=null){ 
   
%>
<TITLE>Sign Up</TITLE>
<meta http-equiv="pragma" content="no-cache">
<link rel='stylesheet' type='text/css' href='/Project4/sample.css'>
</HEAD>
<BODY BGCOLOR=" #b3b3b3">
<p align='RIGHT'><BUTTON><a href = '/OnlineLogoutServlet' STYLE='TEXT-DECORATION:NONE;COLOR:#FF6200;FONT-SIZE:20px;'>Logout</a></BUTTON></p>
<div id="navbar">
    <div id="holder">
		<ul>
			<li><a href="/project4/AccountOverview.jsp?UserID=<%=Username%>" id="onlink">AccountOverview</a></li>
			<li><a href="/project4/OpenAccount.jsp?UserID=<%=Username%>">OpenAccount</a></li>
			<li><a href="InquireTransactions.jsp?UserID=<%=Username%>">InquireTransactions</a></li>
			<li><a href="Transfer.jsp?UserID=<%=Username%>">Transfer</a></li>
			<li><a href="BillPays.jsp?UserID=<%=Username%>">BillPays</a></li>
		</ul>
    </div> <!--end of holder-->
 </div> <!--end of navbar-->
 <div id='Container'>
                 <p style='text-align:center'>Welcome <%= Username %></p>
                 <%
                         CheckingAccount CA = new CheckingAccount(Username);
                         SavingsAccount SA = new SavingsAccount(Username);
                         if(CA.CheckingAccountExists(Username) || SA.SavingsAccountExists(Username)){
                 %>
                 <TABLE cellPadding=4 ALIGN='center'>
                 <% 
                     if(CA.CheckingAccountExists(Username)){
                         CA = CA.getAccountInfo(Username);
                         CheckingAccNum = CA.getCANum();
                         CheckingBal = Float.toString(CA.getBal());
                     
                 %>
                     <TR>
                         <TD>Checking Account Number:</TD>
                         <TD>
                             <%= CheckingAccNum %>
                         </TD>
                     </TR>
                     <TR>
                         <TD>Balance:</TD>
                         <TD>
                            <%= CheckingBal %>
                         </TD>
                     </TR>
                 <% } %>
                 <% if(SA.SavingsAccountExists(Username)){
                     SA = SA.getAccountInfo(Username);
                     SavingsAccNum = SA.getSANum();
                     SavingsBal = Float.toString(SA.getBal());
                 %>
                     <TR>
                         <TD>Savings Account Number:</TD>
                         <TD>
                            <%= SavingsAccNum %>
                         </TD>
                     </TR>
                     <TR>
                         <TD>Balance:</TD>
                         <TD>
                            <%= SavingsBal %>
                         </TD>
                     </TR>
                 <% } %>
                 </TABLE><BR>
                 <% } else { %>
                     <p style='text-align:center'>You Do not Have Account Yet. Please click on "OpenAccount" If you wish to have.</p>
                 <% } %>
            </div>           
</BODY>
</HTML>
<% }  else {%>
            
            <H3 ALIGN='CENTER'>  To Access Your Account Please <a href='/project4/login.html'>CLICK</A> On It!!!</H3>
     <% } %>
	    		