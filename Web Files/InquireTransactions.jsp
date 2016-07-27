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
			<li><a href="InquireTransactions.jsp?UserID=<%=Username%>" id="onlink">InquireTransactions</a></li>
			<li><a href="Transfer.jsp?UserID=<%=Username%>">Transfer</a></li>
			<li><a href="BillPays.jsp?UserID=<%=Username%>">BillPays</a></li>
		</ul>
    </div> <!--end of holder-->
 </div> <!--end of navbar-->
  <div id="Container">
     <FORM NAME="inquiry"  ACTION="/InquireTransactionServlet" METHOD ="POST">
         <TABLE cellPadding=3 ALIGN='center'>
             <TR bgcolor='#F1F1FD'>
                 <TD>Start Date</TD>
                 <TD>
                    <INPUT TYPE='text' NAME='startdate' Value='yyyy-mm-dd' SIZE='15'>
                 </TD>
             </TR>
             <TR bgcolor='#F1F1FD'>
                         <TD>End Date</TD>
                         <TD>
                            <INPUT TYPE='text' NAME='enddate' Value='yyyy-mm-dd' SIZE='15'>
                         </TD>
             </TR>
           </TABLE><BR>
           <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='submitBNTN' VALUE='submit' onClick="checkInputs()"></CENTER><BR>
     </FORM>
     </div>
    <SCRIPT LANGUAGE="JavaScript">
       function checkInputs()
       {
       var Prompts = "";
       startdate = window.document.inquiry.startdate.value;
       enddate = document.inquiry.enddate.value;
       if (startdate == "yyyy-mm-dd" || enddate == "yyyy-mm-dd") {
         if (startdate == "yyyy-mm-dd")
            Prompts +="Please enter Start Date!\n";
         if (enddate == "yyyy-mm-dd")
            Prompts +="Please enter End Date!\n";
            window.alert(Prompts);
       }else {
         document.inquiry.submit();
       }
       }
    </SCRIPT>
</BODY>
</HTML>
<% }  else {%>
            
            <H3 ALIGN='CENTER'>  To Access Your Account Please <a href='/project4/login.html'>CLICK</A> On It!!!</H3>
     <% } %>
