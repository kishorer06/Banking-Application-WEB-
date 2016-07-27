/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.io.*;
import javax.servlet.*;  //package for GenericServlet
import javax.servlet.http.*;  //package for HttpServlet
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.kishore.banking.*;
import com.kishore.banking.CheckingAccount;
import com.kishore.banking.SavingsAccount;


public class TransferServlet extends HttpServlet {
   private String fromAccountType, toAccountType, amount;
   private PrintWriter output;
   private String CheckingAccountNum;
   private String SavingsAccountNum;

   //a method called automatically to initialize the servlet
   public void init( ServletConfig config )
      throws ServletException
   {
      super.init( config );
   }


   public void doGet ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {	doPost(req, res);
   }


   public void doPost ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {

      output = res.getWriter();


      res.setContentType( "text/html" );
		HttpSession ses  =   req.getSession();

      fromAccountType = req.getParameter( "FromAccountType" );
      toAccountType = req.getParameter( "ToAccountType" );
      amount = req.getParameter( "AmountField" );
		String uname = (String) ses.getAttribute("uname");


      System.out.println("value......................"+fromAccountType+ "uname:" +uname);

		if (fromAccountType.trim().equalsIgnoreCase("Checking") && toAccountType.trim().equalsIgnoreCase("Savings"))
		{
			CheckingAccount CA = new CheckingAccount(uname);
			CA = CA.getAccountInfo(uname);
			CheckingAccountNum = CA.getCANum();
			SavingsAccount SA = new SavingsAccount(uname);
			SA = SA.getAccountInfo(uname);
			SavingsAccountNum = SA.getSANum();
			if(CA.Withdraw(CheckingAccountNum, Float.parseFloat(amount)) && SA.deposit(SavingsAccountNum, Float.parseFloat(amount)))
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				Transaction TA = new Transaction(cal.getTime(),Float.parseFloat(amount),"Transfered", CheckingAccountNum,SavingsAccountNum, uname);
				TA.recordTransaction();
				System.out.println("Savings Account Number: "+SavingsAccountNum);
				transferHTML("Transfer From Checking Succeeded");
			} else {
				transferHTML("Transfer Failed");
			}
		}else if (fromAccountType.trim().equalsIgnoreCase("Savings") && (toAccountType.trim().equalsIgnoreCase("Checking") && (CheckingAccountNum!=""))){
			CheckingAccount CA = new CheckingAccount(uname);
			CA = CA.getAccountInfo(uname);
			CheckingAccountNum = CA.getCANum();
			SavingsAccount SA = new SavingsAccount(uname);
			SA = SA.getAccountInfo(uname);
			SavingsAccountNum = SA.getSANum();

			if (SA.Withdraw(SavingsAccountNum, Float.parseFloat(amount))&& CA.deposit(CheckingAccountNum, Float.parseFloat(amount))){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				Transaction TA = new Transaction(cal.getTime(), Float.parseFloat(amount),"Transfered",SavingsAccountNum,CheckingAccountNum, uname);
				TA.recordTransaction();
				transferHTML("Transfer From Savings Succeeded");
			} else {
				transferHTML("Transfer from savings Failed");
			}
		}

   }

    public void transferHTML(String Displaytext){
		StringBuffer Buf = new StringBuffer();
		Buf.append("<HTML>");
		Buf.append("<HEAD>");
		  Buf.append("<link rel='stylesheet' type='text/css' href='/project4/sample.css'>");
		  Buf.append("<link href='https://fonts.googleapis.com/css?family=Lato:400,300,700,900' rel='stylesheet' type='text/css'>");
		Buf.append("</HEAD>");
		Buf.append("<BODY BGCOLOR='#b3b3b3'>");
		    Buf.append("<div id='Container'>");
			Buf.append("<CENTER><p>" + Displaytext + "</p><BR></CENTER>");
			Buf.append("<p Align='CENTER'><A HREF='/project4/AccountOverview.jsp' STYLE='TEXT-DECORATION:NONE;'><button>Back</button></A></CENTER>");
			Buf.append("</div>");
		    Buf.append("</BODY>");
		Buf.append("</HTML>");
		output.println(Buf.toString());
   	}


   //this "cleanup" method is called when a servlet is terminated by the server
   public void destroy() {
       output.close();
   }
}