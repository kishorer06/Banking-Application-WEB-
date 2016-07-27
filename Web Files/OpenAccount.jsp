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
			<li><a href="/project4/AccountOverview.jsp?UserID=<%=Username%>">AccountOverview</a></li>
			<li><a href="/project4/OpenAccount.jsp?UserID=<%=Username%>" id="onlink">OpenAccount</a></li>
			<li><a href="InquireTransactions.jsp?UserID=<%=Username%>">InquireTransactions</a></li>
			<li><a href="Transfer.jsp?UserID=<%=Username%>">Transfer</a></li>
			<li><a href="BillPays.jsp?UserID=<%=Username%>">BillPays</a></li>
		</ul>
    </div> <!--end of holder-->
 </div> <!--end of navbar-->
  <div id="Container">
       <FORM NAME="OpenAccountPage" ACTION="/OpenAccountServlet" METHOD ="POST">
           <INPUT TYPE='hidden' NAME='UserID' VALUE='<%=request.getParameter("UserID")%>'>
           <TABLE cellPadding=3 ALIGN='center'>
               <TR bgcolor='#ECFAEB'>
                   <TD>Choose account type:</TD>
                   <TD>
                   <select size="1" name="CheckingOrSavings">
                     	<option selected value="Checking">Checking</option>
                     	<option value="Savings">Savings</option>
                   </select>
                   <tr><td>Username:</td><td><INPUT TYPE="TEXT" NAME="UsernameField" SIZE=20></td></tr>
                   <tr><td>AccountNumber:</td><td><INPUT TYPE="TEXT" NAME="AccountNum" SIZE=20></td></tr>
                   <tr><td>Balance:</td><td><INPUT TYPE="TEXT" NAME="Balance" SIZE=20></td></tr>
                   </TD>
               </TR>
             </TABLE><BR>
             <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='submitBNTN' VALUE='Submit' onClick="checkInputs()"></CENTER><BR>
       </FORM>
    </div>
</BODY>
</HTML>
     <SCRIPT LANGUAGE="JavaScript">
      function checkInputs()
      {
      var Prompts = "";
      Username = window.document.OpenAccountPage.UsernameField.value;
      AccountNum = document.OpenAccountPage.AccountNum.value;
      Balance = document.OpenAccountPage.Balance.value;
      if (Username == "" || Balance == "" || AccountNum == "") {
        if (Username == "")
           Prompts +="Please enter your username!\n";
        if (Balance == "")
           Prompts +="Please enter  Balance!\n";
        if (AccountNum == "")
           Prompts +="Please enter your Account Number!\n";
        window.alert(Prompts);
      }else {
        document.OpenAccountPage.submit();
      }
      }
    </SCRIPT>
    </BODY>
</HTML>
<% }  else {%>
            
            <H3 ALIGN='CENTER'>  To Access Your Account Please <a href='/project4/login.html'>CLICK</A> On It!!!</H3>
     <% } %>