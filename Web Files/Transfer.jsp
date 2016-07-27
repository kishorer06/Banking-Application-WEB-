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
    String CheckingAccNum = "", CheckingBal = "", SavingsAccNum = "", SavingsBal = "";
    
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
			<li><a href="Transfer.jsp?UserID=<%=Username%>" id="onlink">Transfer</a></li>
			<li><a href="BillPays.jsp?UserID=<%=Username%>">BillPays</a></li>
		</ul>
    </div> <!--end of holder-->
 </div> <!--end of navbar-->
<div id="Container">
    <FORM NAME="TransferForm" ACTION="/TransferServlet" METHOD ="POST">
        <INPUT TYPE='hidden' NAME='UserID' VALUE='<%=request.getParameter("UserID")%>'>
        <TABLE cellPadding=4 ALIGN='center'>
            <TR bgcolor='#ECFAEB'>
                <TD>Transfer From:</TD>
                <TD>
                    <select size="1" name="FromAccountType">
                        <option selected value="Checking">Checking</option>
                        <option value="Savings">Savings</option>
                    </select>
                </TD>
            </TR>
            <TR bgcolor='#ECFAEB'>
                <TD>Transfer To:</TD>
                <TD>
                    <select size="1" name="ToAccountType">
                        <option selected value="Savings">Savings</option>
                        <option value="Checking">Checking</option>
                    </select>
                </TD>
            </TR>
            <TR bgcolor='#F1F1FD'>
                <TD>Deposit Amount:</TD>
                <TD>
                   <INPUT TYPE='text' NAME='AmountField' Value='' SIZE='15'>
                </TD>
            </TR>
          </TABLE><BR>
          <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='submitBNTN' VALUE='Transfer' onClick="checkInputs()"></CENTER><BR>
    </FORM>
    </div>
   <SCRIPT LANGUAGE="JavaScript">
      function checkInputs()
      {
      var Prompts = "";
      FromAccountType = window.document.TransferForm.FromAccountType.selectedOptions[0].value;
      ToAccountType = window.document.TransferForm.ToAccountType.selectedOptions[0].value;
      Amount = window.document.TransferForm.AmountField.value;
      if (FromAccountType ==  ToAccountType || Amount == "") {
            if(FromAccountType ==  ToAccountType )
                Prompts +="From Account type should be different from To Account type!\n";
            if(Amount == "")
                Prompts +="Enter Amount!\n";
            window.alert(Prompts);
      }else {
        document.TransferForm.submit();
      }
      }
    </SCRIPT>
</BODY>
</HTML>
<% }  else {%>
            
            <H3 ALIGN='CENTER'>  To Access Your Account Please <a href='/project4/login.html'>CLICK</A> On It!!!</H3>
     <% } %>