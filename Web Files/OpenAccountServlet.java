import java.io.*;
import javax.servlet.*;  //package for GenericServlet
import javax.servlet.http.*;  //package for HttpServlet
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.kishore.banking.*;
import com.kishore.banking.CheckingAccount.*;
import com.kishore.banking.SavingsAccount.*;

public class OpenAccountServlet extends HttpServlet {
   private String accountType, amount, AccountNum;
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

      accountType = req.getParameter( "CheckingOrSavings" );
      amount = req.getParameter( "Balance" );
     //String Name = req.getparameter("UsernameField");
		String uname = (String) ses.getAttribute("uname");
		String Name = uname;
		AccountNum = req.getParameter("AccountNum");


      System.out.println("value......................"+accountType  + uname);

		if (accountType.trim().equalsIgnoreCase("Checking"))
		{
			//CheckingAccount CA = new CheckingAccount(uname);
			CheckingAccountNum = AccountNum;
			CheckingAccount CA = new CheckingAccount(CheckingAccountNum, Name, uname, Float.parseFloat(amount));

			if(CA.openAcct())
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				System.out.println(dateFormat.format(cal.getTime()));
				Transaction TA = new Transaction(cal.getTime(), Float.parseFloat(amount),"deposit","",CheckingAccountNum,uname);
				TA.recordTransaction();

				openAccHTML("Checking Account succedded");
			} else {
				openAccHTML("Checking Account Failed");
			}
		}else if (accountType.trim().equalsIgnoreCase("Savings"))
		{
			//SavingsAccount SA = new SavingsAccount(uname);
			SavingsAccountNum = AccountNum;
			SavingsAccount SA = new SavingsAccount(SavingsAccountNum, Name,uname, Float.parseFloat(amount));
			if (SA.openAcct())
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				Transaction TA = new Transaction(cal.getTime(), Float.parseFloat(amount),"deposit","",SavingsAccountNum,uname);
				TA.recordTransaction();
				openAccHTML("Savings Account succedded");
			} else {
				openAccHTML("Savings Account Failed");
			}
		}

   }

   	public void openAccHTML(String Displaytext){
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
		    Buf.append("</BODY>");
		Buf.append("</HTML>");
		output.println(Buf.toString());
   	}


   //this "cleanup" method is called when a servlet is terminated by the server
   public void destroy() {
       output.close();
   }
}