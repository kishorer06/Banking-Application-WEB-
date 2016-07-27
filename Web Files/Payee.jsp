<!--
/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/
-->
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
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
 <P><b Style="COLOR:#FF6200"><%=Username%>!</b> You Are In Payee Section</p>
 <p align='RIGHT'><BUTTON><a href = '/project4/BillPays.jsp' STYLE='TEXT-DECORATION:NONE;COLOR:#FF6200;FONT-SIZE:15px;'>Home</a></BUTTON></p>
 <div id="Container">
       <div id="appPayee">
         <p style='text-align:center'>Add Payee</p>
         <FORM NAME="addPayeeForm" ACTION="/OnlineBillPayServlet" Method="POST">
           <TABLE cellPadding=4 ALIGN='center'>
               <TR bgcolor='#ECFAEB'>
                   <TD>Name:</TD>
                   <TD>
                      <INPUT TYPE='text' NAME='add_name' Value='' SIZE='15' >
                   </TD>
               </TR>
               <TR bgcolor='#F1F1FD'>
                   <TD>Address:</TD>
                   <TD>
                      <INPUT TYPE='text' NAME='add_address' Value='' SIZE='15' >
                   </TD>
               </TR>
               <TR bgcolor='#ECFAEB'>
                   <TD>Account Number:</TD>
                   <TD>
                       <INPUT TYPE='text' NAME='add_accNum' Value='' SIZE='15' >
                   </TD>
               </TR>
               <TR bgcolor='#F1F1FD'>
                   <TD>Phone Number:</TD>
                   <TD>
                      <INPUT TYPE='text' NAME='add_phnNum' Value='' SIZE='15' >
                   </TD>
               </TR>
           </TABLE><BR>
           <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='addBTN' VALUE='Add' onClick="addPayee()"></CENTER><BR>
         </FORM>
       </div>
       <hr />
       <div id="editPayee">
       <br />
         <p style='text-align:center'>Edit Payee</p>
         <FORM NAME="editPayeeForm" ACTION="/OnlineBillPayServlet" Method="POST">
           <TABLE cellPadding=4 ALIGN='center'>
               <TR bgcolor='#F1F1FD'>
                   <TD>Payee List:</TD>
                   <TD>
                     <select size="1" name="payeeListInEdit" onChange="fillEdit()" SIZE='15' required>
                       <option >--Select Payee--</option>
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
               <TR bgcolor='#ECFAEB'>
                   <TD>Name:</TD>
                   <TD>
                      <INPUT TYPE='text' NAME='edit_name' Value='' SIZE='15'>
                   </TD>
               </TR>
               <TR bgcolor='#F1F1FD'>
                   <TD>Address:</TD>
                   <TD>
                      <INPUT TYPE='text' NAME='edit_address' Value='' SIZE='15'>
                   </TD>
               </TR>
               <TR bgcolor='#ECFAEB'>
                   <TD>Account Number:</TD>
                   <TD>
                       <INPUT TYPE='text' NAME='edit_accNum' Value='' SIZE='15'>
                   </TD>
               </TR>
               <TR bgcolor='#F1F1FD'>
                   <TD>Phone Number:</TD>
                   <TD>
                      <INPUT TYPE='text' NAME='edit_phnNum' Value='' SIZE='15'>
                   </TD>
               </TR>
           </TABLE><BR>
           <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='editBTN' VALUE='Edit' onClick="editPayee()"></CENTER><BR>
         </FORM>
       </div>
       <hr />
       <div id="deletePayee">
       <br />
         <p style='text-align:center'>Delete Payee</p>
         <FORM NAME="deletePayeeForm" ACTION="/OnlineBillPayServlet" Method="POST">
           <TABLE cellPadding=4 ALIGN='center'>
               <TR bgcolor='#F1F1FD'>
                   <TD>Payee List:</TD>
                   <TD>
                     <select size="1" name="payeeListInDelete" onchange="selectDelete()" required>
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
           </TABLE><BR>
           <CENTER><INPUT class="btn-primary" TYPE="BUTTON" NAME='deleteBTN' VALUE='Delete' onClick="deletePayee()"></CENTER><BR>
         </FORM>
       </div>
     </div>
    <SCRIPT LANGUAGE="JavaScript">
    var userName, payeeList, selectedItem;
          $( document ).ready(function() {
            // to get payee list
            $.ajax({
                url : '/OnlineBillPayServlet',
                type: 'POST',
                cache : false,
                data : {
                    actionType : "getUserInfo",
                    payeeOrpayment : "payee"
                },
                dataType: "json",
                success : function(response) {
                  if(response.responseCode == "Success"){
                    resetList(response.payeeList);
                  }else{
    
                  }
                  if(response.userName != ""){
                    userName = response.userName;
                    sessionStorage.setItem('uname', userName);
                  }
            }});
      });
      
      function resetList (str) {
              // body...
              payeeList = JSON.parse(str);
      
                var optionsAsString = "<option value='-1'>-- Select Payee --</option>";
                for(var i = 0; i < payeeList.length; i++) {
                    optionsAsString += "<option value='" + i + "'>" + payeeList[i].PayeeName + "</option>";
                }
                $("select[name='payeeListInEdit']").find('option').remove().end().append($(optionsAsString));
                $("select[name='payeeListInDelete']").find('option').remove().end().append($(optionsAsString));
      }
      
       function fillEdit(){
            var index = $("select[name='payeeListInEdit']").find(":selected").val();
            if(index != -1){
              selectedItem = payeeList[index];
                $("INPUT[NAME='edit_name']").val(selectedItem.PayeeName);
                $("INPUT[NAME='edit_accNum']").val(selectedItem.PayerAccountNumber);
                $("INPUT[NAME='edit_address']").val(selectedItem.PayeeAddress);
                $("INPUT[NAME='edit_phnNum']").val(selectedItem.PayeePhoneNumber);
            }
          }

    function addPayee(){
            if(checkInputs("add") == ""){
              $.ajax({
                  url : '/OnlineBillPayServlet',
                  type: 'POST',
                  cache : false,
                  data : {
                      actionType : "Add",
                      payeeOrpayment : "payee",
                      newPayee: JSON.stringify({
                                  "PayeeName": $("INPUT[NAME='add_name']").val(),
                                  "PayeeAddress": $("INPUT[NAME='add_address']").val(),
                                  "PayerAccountNumber": $("INPUT[NAME='add_accNum']").val(),
                                  "PayeePhoneNumber": $("INPUT[NAME='add_phnNum']").val(),
                                  "CustomerID" : userName
                                })
                  },
                  dataType: "json",
                  success : function(response) {
                      if(response.responseCode == "Success"){
                        $("INPUT[NAME='add_name']").val("");
                        $("INPUT[NAME='add_accNum']").val("");
                        $("INPUT[NAME='add_address']").val("");
                        $("INPUT[NAME='add_phnNum']").val("");
                        resetList(response.payeeList);
                        alert("Added Payee Successfully!");
                      }else{
                        alert("Unable to add Payee, try again!");
                      }
                }
              });
            }
      }
       function selectDelete(){
              var index = $("select[name='payeeListInDelete']").find(":selected").val();
              if(index != -1){
                selectedItem = payeeList[index];
              }
      }
        function editPayee(){
	        if(checkInputs("edit") == ""){
	          $.ajax({
	              url : '/OnlineBillPayServlet',
	              type: 'POST',
	              cache : false,
	              data : {
	                  actionType : "Edit",
	                  payeeOrpayment : "payee",
	                  editPayee: JSON.stringify({
	                              "PayeeID": selectedItem.PayeeID,
	                              "PayeeName": $("INPUT[NAME='edit_name']").val(),
	                              "PayerAccountNumber": $("INPUT[NAME='edit_accNum']").val(),
	                              "PayeeAddress": $("INPUT[NAME='edit_address']").val(),
	                              "PayeePhoneNumber": $("INPUT[NAME='edit_phnNum']").val(),
	                              "CustomerID" : userName
	                            })
	              },
	              dataType: "json",
	              success : function(response) {
	                  if(response.responseCode == "Success"){
	                    resetList(response.payeeList);
	                    alert("Updated Payee Information Successfully!");
	                  }else{
	                    alert("Unable to Update Payee Information, try again!");
	                  }
	                  $("INPUT[NAME='edit_name']").val("");
	                  $("INPUT[NAME='edit_accNum']").val("");
	                  $("INPUT[NAME='edit_address']").val("");
	                  $("INPUT[NAME='edit_phnNum']").val("");
	                  selectedItem = "";
	            }
	          });
	        }
      }
      
      function deletePayee(){
              if(checkInputs("delete") == ""){
                $.ajax({
                    url : '/OnlineBillPayServlet',
                    type: 'POST',
                    cache : false,
                    data : {
                        actionType : "Delete",
                        payeeOrpayment : "payee",
                        deletePayee: JSON.stringify(selectedItem)
                    },
                    dataType: "json",
                    success : function(response) {
                        if(response.responseCode == "Success"){
                          resetList(response.payeeList);
                          alert("Deleted Payee Successfully!");
                        }else{
                          alert("Unable to delete Payee, try again!");
                        }
                        selectedItem = "";
                  }
                });
              }
      }
      
      function checkInputs(action){
              var Prompts = "", n, ac, ad, ph, ind;
              if(action == "add"){
                  n = $("INPUT[NAME='add_name']").val();
                  ac =  $("INPUT[NAME='add_accNum']").val();
                  ad =  $("INPUT[NAME='add_address']").val();
                  ph =  $("INPUT[NAME='add_phnNum']").val();
                  if(n == "" || ac == "" || ad == "" || ph == ""){
                    if (n == "")
                       Prompts +="Please enter Payee Name!\n";
                    if (ac == "")
                       Prompts +="Please enter Payee Account Number!\n";
                    if (ad == "")
                       Prompts +="Please enter Payee Address!\n";
                    if (ph == "")
                       Prompts +="Please enter Payee Phone Number!\n";
                    window.alert(Prompts);
                  }
              }else if(action == "edit"){
                  ind = $("select[name='payeeListInEdit']").find(":selected").val();
                  n = $("INPUT[NAME='edit_name']").val();
                  ac =  $("INPUT[NAME='edit_accNum']").val();
                  ad =  $("INPUT[NAME='edit_address']").val();
                  ph =  $("INPUT[NAME='edit_phnNum']").val();
                  if(n == "" || ac == "" || ad == "" || ph == "" || ind == -1){
                    if (n == "")
                       Prompts +="Please enter Payee Name!\n";
                    if (ac == "")
                       Prompts +="Please enter Payee Account Number!\n";
                    if (ad == "")
                       Prompts +="Please enter Payee Address!\n";
                    if (ph == "")
                       Prompts +="Please enter Payee Phone Number!\n";
                    if (ind == -1)
                       Prompts +="Please select Payee!\n";
                    window.alert(Prompts);
                  }
              }else if(action == "delete"){
                  ind = $("select[name='payeeListInDelete']").find(":selected").val();
                  if (ind == -1)
                       Prompts +="Please select Payee!\n";
                    window.alert(Prompts);
              }else{
              }
              return Prompts;
      }
      
    </SCRIPT>
 </BODY>
</HTML>
<% }  else {%>
            
            <H3 ALIGN='CENTER'>  To Access Your Account Please <a href='/project4/login.html'>CLICK</A> On It!!!</H3>
     <% } %>