/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.io.*;
import javax.servlet.*;  //package for GenericServlet
import javax.servlet.http.*;  //package for HttpServlet
import java.util.*;
import com.kishore.banking.*;
import javax.servlet.http.HttpSession;
import com.google.gson.*;

public class BillPayServlet extends HttpServlet {
   private String Username;
   private String actionType = "", payeeOrpayment = "";
   private PrintWriter output;
   private HttpServletRequest requ;
   private String CheckingAccountNum;
   private String SavingsAccountNum;
   private JsonObject postResult = new JsonObject();
   private Gson gson = new Gson();

   //a method called automatically to initialize the servlet
   public void init( ServletConfig config )
      throws ServletException
   {
      super.init( config );
      Username = new String("");
      postResult.addProperty("gf", "fg");
   }


   public void doGet ( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
      doPost(req, res);
   }


   public void doPost ( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
      output = res.getWriter();
      requ = req;
      HttpSession ses  =   req.getSession();
      Username = (String) ses.getAttribute("uname");
      actionType = req.getParameter( "actionType" );
      payeeOrpayment = req.getParameter( "payeeOrpayment" );
      System.out.println("BillPayServlet => POST => actionType: " + actionType+ ", payeeOrpayment: " + payeeOrpayment);
      if("".equals(actionType) || "".equals(payeeOrpayment)){
		  System.err.println("Missing mandatory params.");
		  postResult.addProperty("responseCode", "Server Error!");
	  }else{
         System.out.println("heloo !!!");
         if(payeeOrpayment.equals("payee")){
            postResult = payeeDuty();
         }

         if(payeeOrpayment.equals("payment")){
            postResult = paymentDuty();
         }

         // res.write(postResult);
      }
      output.println(postResult);
      output.flush();
   }

   public JsonObject payeeDuty(){
      JsonObject returnObject = new JsonObject();

      if (actionType.equals("Add")) {
         Payee p_add = gson.fromJson(requ.getParameter("newPayee"), Payee.class);
         System.out.println("About To Add Payee!!"+p_add);
         if(p_add.addPayee(Username)){
                //get payee list
               String payeeList = getPList();
               returnObject.addProperty("payeeList", payeeList);
               returnObject.addProperty("responseCode", "Success");
         }else{
               returnObject.addProperty("responseCode", "Failure");
         }
      }else if(actionType.equals("Edit")){
            Payee p_edit = gson.fromJson(requ.getParameter("editPayee"), Payee.class);
            if(p_edit.updatePayee()){
                //get payee list
               String payeeList = getPList();
               returnObject.addProperty("UpdatedPayee", gson.toJson(p_edit));
               returnObject.addProperty("payeeList", payeeList);
               returnObject.addProperty("responseCode", "Success");
            }else{
                  returnObject.addProperty("responseCode", "Failure");
            }
      }else if(actionType.equals("Delete")){
         Payee p_delete = gson.fromJson(requ.getParameter("deletePayee"), Payee.class);
         if(p_delete.deletePayee()){
               //get payee list
               String payeeList = getPList();
               returnObject.addProperty("payeeList", payeeList);
               returnObject.addProperty("responseCode", "Success");
          }else{
               returnObject.addProperty("responseCode", "Failure");
          }
      }else if(actionType.equals("getUserInfo")){
         String payeeList = getPList();
         if(!payeeList.equals("null") && !payeeList.equals("")){
            returnObject.addProperty("payeeList", payeeList);
            returnObject.addProperty("userName", Username);
            returnObject.addProperty("responseCode", "Success");
         }else if(payeeList.equals("")){
            returnObject.addProperty("userName", Username);
            returnObject.addProperty("payeeList", "[]");
            returnObject.addProperty("responseCode", "Success");
         }else{
            returnObject.addProperty("userName", Username);
            returnObject.addProperty("responseCode", "Failure");
         }
      }
        return returnObject;
   }

   public JsonObject paymentDuty(){
        JsonObject returnObject = new JsonObject();
        System.out.println("actionType: " + actionType);
        if (actionType.equals("Schedule")) {
           System.out.println("In Schedule"+requ.getParameter("schedulePayment"));
           Payment pa_add = gson.fromJson(requ.getParameter("schedulePayment"), Payment.class);

           System.out.println("Paymnt: "+ pa_add.getFromAccount());
           if(pa_add.schedulePayment()){
                  //get payment list
                 String paymentList = getPaList();
                 returnObject.addProperty("paymentList", paymentList);
                 returnObject.addProperty("responseCode", "Success");
           }else{
                 returnObject.addProperty("responseCode", "Failure");
           }
        }else if(actionType.equals("Update")){
              System.out.println("In Update");
              Payment pa_edit = gson.fromJson(requ.getParameter("editPayment"), Payment.class);
              if(pa_edit.updatePayment()){
                  //get payment list
                 String paymentList = getPaList();
                 returnObject.addProperty("paymentList", paymentList);
                 returnObject.addProperty("responseCode", "Success");
              }else{
                    returnObject.addProperty("responseCode", "Failure");
              }
        }else if(actionType.equals("Cancel")){
           Payment pa_delete = gson.fromJson(requ.getParameter("cancelPayment"), Payment.class);
           if(pa_delete.cancelPayment()){
                 //get payment list
                 String paymentList = getPaList();
                 returnObject.addProperty("paymentList", paymentList);
                 returnObject.addProperty("responseCode", "Success");
            }else{
                 returnObject.addProperty("responseCode", "Failure");
            }
        }else if(actionType.equals("getUserInfo")){
           String payeeList = getPList();
           String paymentList = getPaList();
           String accsInfo = getAccList();

           if(!payeeList.equals("null")){
              returnObject.addProperty("payeeList", payeeList);
              returnObject.addProperty("userName", Username);
              returnObject.addProperty("responseCode", "Success");
           }else{
              returnObject.addProperty("userName", Username);
              returnObject.addProperty("responseCode", "Failure");
           }

           if(!paymentList.equals("null")){
              returnObject.addProperty("paymentList", paymentList);
              returnObject.addProperty("userName", Username);
              returnObject.addProperty("responseCode", "Success");
           }

           if(!accsInfo.equals("null")){
              returnObject.addProperty("accsInfo", accsInfo);
              returnObject.addProperty("userName", Username);
              returnObject.addProperty("responseCode", "Success");
           }
        }else{

        }
          return returnObject;
   }

   public String getPList(){
      Payee p = new Payee();
      List<Payee> pList = p.getPayeeList(Username);
      String payees = "null";
      if(pList.size() != 0){
         payees = new Gson().toJson(pList);
      }else if(pList.size() == 0){
         payees = "[]";
      }else{

      }
      return payees;
   }

   public String getPaList(){
      Payment p = new Payment();
      List<Payment> pList = p.getPaymentList(Username);
      String payments = "null";
      if(pList.size() != 0){
         payments = new Gson().toJson(pList);
      }else if(pList.size() == 0){
         payments = "[]";
      }else{

      }
      return payments;
   }

   public String getAccList(){
      String temp = "null";
      List<String> accArr = new ArrayList<String>();
      CheckingAccount CA = new CheckingAccount();
      if(CA.CheckingAccountExists(Username)){
         CA = CA.getAccountInfo(Username);
         temp = gson.toJson(CA);
         accArr.add(temp);
      }

      SavingsAccount SA = new SavingsAccount();
      if(SA.SavingsAccountExists(Username)){
         SA = SA.getAccountInfo(Username);
         temp = gson.toJson(SA);
         accArr.add(temp);
      }

      if(accArr.size() != 0){
         temp = gson.toJson(accArr);
      }else if(accArr.size() == 0){
         temp = "[]";
      }else{

      }
      return temp;
   }
   //this "cleanup" method is called when a servlet is terminated by the server
   public void destroy() {
       output.close();
   }
}