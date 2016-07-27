
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.Date;"%>
<%@ page import="java.text.SimpleDateFormat;"%>
<%@ page import="java.text.DateFormat;"%>
<%@ page import="java.util.ArrayList,java.util.Iterator" %>
<%@ page import="com.kishore.banking.*;" %>
<%@ page import="javax.servlet.http.HttpSession;" %>
<%@ page import ="javax.servlet.http.*;" %>

<%
    String Username = new String("");
    String CustomerID;
    
    
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
  <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
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
 <P><b Style="COLOR:#FF6200"><%=Username%>!</b> You Are In Payment Section</p> 
  <p align='RIGHT'><BUTTON><a href = '/project4/BillPays.jsp' STYLE='TEXT-DECORATION:NONE;COLOR:#FF6200;FONT-SIZE:15px;'>Home</a></BUTTON></p>
 <div id="Container">
       <div id="appPayee">
         <p style='text-align:center'>Schedule Payment</p>
         <FORM NAME="schedulePaymentForm" ACTION="/OnlineBillPayServlet" METHOD="POST" >
           <TABLE cellPadding=4 ALIGN='center'>
               <TR bgcolor='#ECFAEB'>
                   <TD>Select Payee:</TD>
                   <TD>
                      <select size="1" name="PayeeInSchedule" SIZE='15' onchange="onChangeSelect('PayeeInSchedule', 'add', 'payee')">
                       <option>-- Select Payee --</option>
                        <%	
		           CustomerID = Username;
		       	 List <Payee> PayeeLst = new ArrayList<Payee>();
		       	List <Payee> PLst = Payee.getPayeeList(CustomerID);
		        Iterator is = PLst.iterator();
		        while(is.hasNext())
		       	{
		       	Payee d = (Payee)is.next();
		       	 %>
		        <option ><%= d.PayeeName %></option>
                       <% } %>
                     </select>
                   </TD>
               </TR>
               <TR bgcolor='#F1F1FD'>
                   <TD>From Account:</TD>
                   <TD>
                     <select size="1" name="frmAccInSchedule" SIZE='15' onchange="onChangeSelect('frmAccInSchedule', 'add', 'acc')">
                       <option>-- Select Account --</option>
                     </select>
                   </TD>
               </TR>
               <TR bgcolor='#ECFAEB'>
                   <TD>Amount:</TD>
                   <TD>
                       <INPUT TYPE='text' NAME='schedule_amt' Value='' SIZE='15'>
                   </TD>
               </TR>
               <TR bgcolor='#F1F1FD'>
                   <TD>Date:</TD>
                   <TD>
                      <INPUT TYPE='text' NAME='schedule_date' Value='yyyy-mm-dd' SIZE='15'>
                   </TD>
               </TR>
           </TABLE><BR>
           <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='scheduleBTN' VALUE='Schedule' onClick="schedulePayment()"></CENTER><BR>
         </FORM>
       </div>
       <hr />
       <div id="editPayment">
       <br />
         <p style='text-align:center'>Edit Payment</p>
         <FORM NAME="editPaymentForm" ACTION="/OnlineBillPayServlet" METHOD="POST">
           <TABLE cellPadding=4 ALIGN='center'>
               <TR bgcolor='#F1F1FD'>
                   <TD>Select Payment:</TD>
                   <TD>
                     <select size="1" name="PaymentInEdit" SIZE='15' onchange="onChangeSelect('PaymentInEdit', 'edit', 'payment')">
                       <option>-- Select Payment --</option>
                     </select>
                   </TD>
               </TR>
               <TR bgcolor='#ECFAEB'>
                   <TD>Payee:</TD>
                   <TD>
                     <select size="1" name="PayeeInEdit" SIZE='15' onchange="onChangeSelect('PayeeInEdit', 'edit', 'payee')">
                       <option>-- Select Payee --</option>
                        <%	
		       	 CustomerID = Username;
		       	 List <Payee> PayeeList = new ArrayList<Payee>();
		         List <Payee> PList = Payee.getPayeeList(CustomerID);
		       	 Iterator it = PList.iterator();
		         while(it.hasNext())
		         {
		          Payee p = (Payee)it.next();
		          %>
		         <option ><%= p.PayeeName %></option>
                       <% } %>
                     </select>
                   </TD>
               </TR>
               <TR bgcolor='#F1F1FD'>
                   <TD>From Account:</TD>
                   <TD>
                     <select size="1" name="frmAccInEdit" SIZE='15' onchange="onChangeSelect('frmAccInEdit', 'edit', 'acc')">
                       <option>-- Select Account --</option>
                     </select>
                   </TD>
               </TR>
               <TR bgcolor='#ECFAEB'>
                   <TD>Amount:</TD>
                   <TD>
                       <INPUT TYPE='text' NAME='edit_amt' Value='' SIZE='15'>
                   </TD>
               </TR>
               <TR bgcolor='#F1F1FD'>
                   <TD>Date:</TD>
                   <TD>
                      <INPUT TYPE='text' NAME='edit_date' Value='yyyy-mm-dd' SIZE='15'>
                   </TD>
               </TR>
           </TABLE><BR>
           <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='editBTN' VALUE='Edit' onClick="editPayment()"></CENTER><BR>
         </FORM>
       </div>
       <hr />
       <div id="cancelPayment">
       <br />
         <p style='text-align:center'>Cancel Payment</p>
         <FORM NAME="cancelPaymentForm" ACTION="/OnlineBillPayServlet" METHOD="POST">
           <TABLE cellPadding=4 ALIGN='center'>
               <TR bgcolor='#F1F1FD'>
                   <TD>Select Payment:</TD>
                   <TD>
                     <select size="1" name="PaymentInCancel" onchange="onChangeSelect('PaymentInCancel', 'delete', 'payment')">
                       <option>-- Select Payment --</option>
                     </select>
                   </TD>
               </TR>
           </TABLE><BR>
           <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='cancelBTN' VALUE='Cancel' onClick="cancelPayment()"></CENTER><BR>
         </FORM>
       </div>
    </div>
    <SCRIPT LANGUAGE="JavaScript">
             var userName, payeeList, paymentList, AccList, selectedPayment, selectedPayee, selectedAcc;
	   
	         $( document ).ready(function() {
	           // to get payee list
	           $.ajax({
	               url : '/OnlineBillPayServlet',
	               type: 'POST',
	               cache : false,
	               data : {
	                   actionType : "getUserInfo",
	                   payeeOrpayment : "payment"
	               },
	               dataType: "json",
	               success : function(response) {
	                 if(response.responseCode == "Success"){
	                   resetPayeeList(response.payeeList);
	                   resetPaymentList(response.paymentList);
	                   resetAccList(response.accsInfo);
	                 }else{
	   
	                 }
	                 if(response.userName != ""){
	                   userName = response.userName;
	                   sessionStorage.setItem('uname', userName);
	                 }
	           }});
	         });
	   
	         function schedulePayment(){
	           $.ajax({
	               url : '/OnlineBillPayServlet',
	               type: 'POST',
	               cache : false,
	               data : {
	                   actionType : "Schedule",
	                   payeeOrpayment : "payment",
	                   schedulePayment: JSON.stringify({
	                               "Amount": $("INPUT[NAME='schedule_amt']").val(),
	                               "PaymentDate": $("INPUT[NAME='schedule_date']").val(),
	                               "PayeeID": selectedPayee.PayeeID,
	                               "FromAccount": selectedAcc.AccNo,
	                               "Status": "Initiated",
	                               "CustomerID": userName
	                             })
	               },
	               dataType: "json",
	               success : function(response) {
	                   if(response.responseCode == "Success"){
	                     resetPaymentList(response.paymentList);
	                     alert("Added Payment Successfully!");
	                   }else{
	                     alert("Unable to add Payment, try again!");
	                   }
	                     selectedPayee = "";
	                     selectedAcc = "";
	                     $("INPUT[NAME='schedule_amt']").val("");
	                     $("INPUT[NAME='schedule_date']").val("yyyy-mm-dd");
	                     $("select[name='frmAccInSchedule']").val(-1);
	                     $("select[name='PayeeInSchedule']").val(-1);
	             }
	           });
	         }
	   
	         function editPayment(){
	           $.ajax({
	               url : '/OnlineBillPayServlet',
	               type: 'POST',
	               cache : false,
	               data : {
	                   actionType : "Update",
	                   payeeOrpayment : "payment",
	                   editPayment: JSON.stringify({
	                               "PaymentID": selectedPayment.PaymentID,
	                               "Amount": $("INPUT[NAME='edit_amt']").val(),
	                               "PaymentDate": $("INPUT[NAME='edit_date']").val(),
	                               "PayeeID": selectedPayee.PayeeID,
	                               "FromAccount": selectedAcc.AccNo,
	                               "Status": selectedPayment.Status,
	                               "CustomerID": userName
	                             })
	               },
	               dataType: "json",
	               success : function(response) {
	                   if(response.responseCode == "Success"){
	                     resetPaymentList(response.paymentList);
	                     alert("Payment was Edited Successfully!");
	                   }else{
	                     alert("Unable to add Payment, try again!");
	                   }
	   
	                   selectedPayee = "";
	                   selectedPayment = "";
	                   selectedAcc = "";
	                   $("select[name='frmAccInEdit']").val(-1);
	                   $("select[name='PayeeInEdit']").val(-1);
	                   $("select[name='PaymentInEdit']").val(-1);
	                   $("INPUT[NAME='edit_amt']").val("");
	                   $("INPUT[NAME='edit_date']").val("yyyy-mm-dd");
	             }
	           });
	         }
	   
	         function cancelPayment(){
	           $.ajax({
	               url : '/OnlineBillPayServlet',
	               type: 'POST',
	               cache : false,
	               data : {
	                   actionType : "Cancel",
	                   payeeOrpayment : "payment",
	                   cancelPayment: JSON.stringify(selectedPayment)
	               },
	               dataType: "json",
	               success : function(response) {
	                   if(response.responseCode == "Success"){
	                     resetPaymentList(response.paymentList);
	                     alert("Payment was Cancelled Successfully!");
	                   }else{
	                     alert("Unable to add Payment, try again!");
	                   }
	   
	                   selectedPayment = "";
	                   $("select[name='PaymentInCancel']").val(-1);
	             }
	           });
	         }
	   
	         function resetPaymentList (str) {
	           // body...
	           paymentList = JSON.parse(str);
	   
	             var optionsAsString = "<option value='-1'>-- Select Payment --</option>";
	             for(var i = 0; i < paymentList.length; i++) {
	                 optionsAsString += "<option value='" + i + "'>" + paymentList[i].Amount + " on " + paymentList[i].PaymentDate + "</option>";
	             }
	             $("select[name='PaymentInEdit']").find('option').remove().end().append($(optionsAsString));
	             $("select[name='PaymentInCancel']").find('option').remove().end().append($(optionsAsString));
	         }
	   
	         function resetPayeeList (str) {
	           // body...
	           payeeList = JSON.parse(str);
	   
	             var optionsAsString = "<option value='-1'>-- Select Payee --</option>";
	             for(var i = 0; i < payeeList.length; i++) {
	                 optionsAsString += "<option value='" + i + "'>" + payeeList[i].PayeeName + "</option>";
	             }
	             $("select[name='PayeeInSchedule']").find('option').remove().end().append($(optionsAsString));
	             $("select[name='PayeeInEdit']").find('option').remove().end().append($(optionsAsString));
	         }
	   
	         function resetAccList (str) {
	           // body...
	             AccList = JSON.parse(str);
	             for (var i = 0; i < AccList.length; i++) {
	               AccList[i] = JSON.parse(AccList[i]);
	               if(AccList[i].CheckingAccountNumber){
	                 AccList[i]["AccType"] = "Checking Account";
	                 AccList[i]["AccNo"] = AccList[i].CheckingAccountNumber;
	               }else{
	                 AccList[i]["AccType"] = "Savings Account";
	                 AccList[i]["AccNo"] = AccList[i].SavingsAccountNumber;
	               }
	             };
	   
	             var optionsAsString = "<option value='-1'>-- Select Account --</option>";
	             for(var i = 0; i < AccList.length; i++) {
	                 optionsAsString += "<option value='" + i + "'>" + AccList[i].AccType + "</option>";
	             }
	             $("select[name='frmAccInEdit']").find('option').remove().end().append($(optionsAsString));
	             $("select[name='frmAccInSchedule']").find('option').remove().end().append($(optionsAsString));
	         }
	   
	         function onChangeSelect(selectName, act, typ){
	           var temp = $("select[name='"+selectName+"']").find(":selected").val(), temp_payee, temp_acc;
	           if(temp != -1){
	             if(act == "add"){
	               if(typ == "payee"){
	                 selectedPayee = payeeList[temp];
	               }else{
	                 selectedAcc = AccList[temp];
	               }
	             }else if(act == "edit"){
	               if(typ == "payment"){
	                 selectedPayment = paymentList[temp];
	                 for (var i = 0; i < payeeList.length; i++) {
	                   if(payeeList[i].PayeeID == selectedPayment.PayeeID){
	                       temp_payee = i;
	                       selectedPayee = payeeList[i];
	                       break;
	                   }
	                 };
	   
	                 for (var i = 0; i < AccList.length; i++) {
	                   if(AccList[i].AccNo == selectedPayment.FromAccount){
	                       temp_acc = i;
	                       selectedAcc = AccList[i];
	                       break;
	                   }
	                 };
	                 $("select[name='PayeeInEdit'] option:selected").attr("selected",null);
	                 $("select[name='PayeeInEdit'] option[value='"+temp_payee+"']").attr("selected","selected");
	   
	                 $("select[name='frmAccInEdit'] option:selected").attr("selected",null);
	                 $("select[name='frmAccInEdit'] option[value='"+temp_acc+"']").attr("selected","selected");
	                 $("INPUT[NAME='edit_amt']").val(selectedPayment.Amount);
	                 var d = new Date(selectedPayment.PaymentDate);
	                 $("INPUT[NAME='edit_date']").val(d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate() );
	               }else if(typ == "payee"){
	                 selectedPayee = payeeList[temp];
	               }else{
	                 selectedAcc = AccList[temp];
	               }
	             }else{
	               if(typ == "payment"){
	                 selectedPayment = paymentList[temp];
	               }
	             }
	           }
      }
    </SCRIPT>
    </BODY>
</HTML>
<% }  else {%>
            
            <H3 ALIGN='CENTER'>  To Access Your Account Please <a href='/project4/login.html'>CLICK</A> On It!!!</H3>
     <% } %>